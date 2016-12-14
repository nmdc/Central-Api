package no.nmdc.api;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import no.nmdc.api.facets.domain.FacetName;
import no.nmdc.api.facets.domain.FacetValue;
import no.nmdc.api.facets.domain.Facets;
import no.nmdc.api.search.domain.SearchParameters;
import no.nmdc.api.search.domain.SearchResults;
import no.nmdc.solr.request.CreateSearchRequest;
import no.nmdc.solr.request.FacetRequest;

import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @see no.nmdc.api.MetadataApi
 * 
 * @author endrem
 *
 */
@Component
public class MetadataApiImpl implements MetadataApi {
    
    private static final Logger logger = LoggerFactory.getLogger(MetadataApiImpl.class);
    
    private CreateSearchRequest queryRequest;
    
    private FacetRequest lukeRequest;
    
    private final static String DATA_URL = "Data_URL";
    
    @Autowired
    public void setQueryRequest(CreateSearchRequest queryRequest) {
        this.queryRequest = queryRequest;
    }
    
    @Autowired
    public void setFacetRequest(FacetRequest lukeRequest) {
        this.lukeRequest = lukeRequest;
    }
    
    /** {@inheritDoc} */
    public Facets getFacets() throws Exception {
        Facets facets = lukeRequest.getFacetsFromSolr();
        for ( FacetName f : facets.getFacets() ) {
            FacetField theField = queryRequest.facetedQuery( f );
                
            f.setMatches( "" + theField.getValueCount() );
            for ( Count c : theField.getValues() ) {
                FacetValue facetChild = new FacetValue();
                facetChild.setValue(c.getName());
                facetChild.setMatches(""+c.getCount());
                f.addChild(facetChild);
            }
        }
        
        new PathHierarchyFacetHelper().createFacetHierarchy(facets);
        return facets;
    }
    
    /** {@inheritDoc} */
    public SearchResults search( SearchParameters query ) throws Exception {
        SolrDocumentList solrDocs = queryRequest.search( query);
        
        logger.info("query.toString:"+query.toString());
        
        SearchResults results = new SearchResults();
        for ( SolrDocument adoc : solrDocs ) {
            Collection<String> names = adoc.getFieldNames();
            Map<String, Object> record = new HashMap<String, Object>();
            for ( String name : names) {
                if ( name.equals( DATA_URL )) {
                    Collection<Object> urls = (Collection<Object>)adoc.getFieldValues( name );
                    Collection<String> urlsEncoded = new ArrayList<String>(urls.size());
                    for (Object aUrl : urls ) {
                        String aUrlString = (String) aUrl;
                        String encodedUrl = URLEncoder.encode(aUrlString, "UTF-8");
                        urlsEncoded.add( encodedUrl );
                    }
                    record.put(name, urlsEncoded.toString() );
                } else {
                    String values = adoc.getFieldValue( name ).toString();
                    record.put( name, values );
                }
            }
            results.addResult( record );
        }
        results.setMatches(solrDocs.size());
        results.setNumFound(solrDocs.getNumFound());
        
        logger.info("results:"+results.getResults());
        return results;
    }
    
    /** {@inheritDoc} */
    public SearchResults getMetadataDetail( String doi ) throws Exception {
        SearchParameters p = new SearchParameters();
        p.setQuery("Entry_ID:\"" + doi + "\"");
        return search(p);
    }
}
