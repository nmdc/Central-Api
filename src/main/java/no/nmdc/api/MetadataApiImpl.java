package no.nmdc.api;

import java.util.Collection;

import no.nmdc.api.domain.SearchResult;
import no.nmdc.api.domain.SearchResults;
import no.nmdc.api.domain.facets.FacetName;
import no.nmdc.api.domain.facets.FacetValue;
import no.nmdc.api.domain.facets.Facets;
import no.nmdc.solr.request.SolrRequests;

import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MetadataApiImpl implements MetadataApi {
    
    private SolrRequests solrRequest;
    
    @Autowired
    public void setSolrRequest(SolrRequests solrRequest) {
        this.solrRequest = solrRequest;
    }
    
    public Facets getFacets() throws Exception {
        Facets facets = solrRequest.getFacetsFromSolr();
        for ( FacetName f : facets.getFacets() ) {
            FacetField theField = solrRequest.facetedQuery( f );
            
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
    
    public SearchResults search(  String query, String facets ) throws Exception {
        SolrDocumentList solrDocs = solrRequest.search(query, facets);
        
        SearchResults results = new SearchResults();
        for ( SolrDocument adoc : solrDocs ) {
            Collection<String> names = adoc.getFieldNames();
            SearchResult result = new SearchResult();
            for ( String name : names) {
                result.putField(name, adoc.getFieldValue( name ));
            }
            results.addResult( result );
        }
        results.setMatches(solrDocs.size());
        return results;
    }
    
    public String getMetadataDetail( String doi ) {return "";}
}
