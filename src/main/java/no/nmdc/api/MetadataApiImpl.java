package no.nmdc.api;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import no.nmdc.api.facets.domain.FacetName;
import no.nmdc.api.facets.domain.FacetValue;
import no.nmdc.api.facets.domain.Facets;
import no.nmdc.api.search.domain.SearchResults;
import no.nmdc.solr.request.FacetRequest;
import no.nmdc.solr.request.QueryRequest;

import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MetadataApiImpl implements MetadataApi {
    
    private QueryRequest queryRequest;
    
    private FacetRequest lukeRequest;
    
    @Autowired
    public void setQueryRequest(QueryRequest queryRequest) {
        this.queryRequest = queryRequest;
    }
    
    @Autowired
    public void setFacetRequest(FacetRequest lukeRequest) {
        this.lukeRequest = lukeRequest;
    }
    
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
        return facets;
    }
    
    public SearchResults search( String query, Integer offset ) throws Exception {
        SolrDocumentList solrDocs = queryRequest.search(query, offset);
        
        SearchResults results = new SearchResults();
        for ( SolrDocument adoc : solrDocs ) {
            Collection<String> names = adoc.getFieldNames();
            Map<String, Object> record = new HashMap<String, Object>();
            for ( String name : names) {
                record.put( name, adoc.getFieldValue( name ) );
            }
            results.addResult( record );
        }
        results.setMatches(solrDocs.size());
        return results;
    }
    
    public String getMetadataDetail( String doi ) {return "";}
}
