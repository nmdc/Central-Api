package no.nmdc.api;

import no.nmdc.api.domain.FacetName;
import no.nmdc.api.domain.FacetValue;
import no.nmdc.api.domain.Facets;
import no.nmdc.solr.request.SolrRequests;

import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
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
            
            System.out.println("name:"+f.getName()+" matches:"+theField.getValueCount());
            f.setMatches( "" + theField.getValueCount() );
            for ( Count c : theField.getValues() ) {
                FacetValue facetChild = new FacetValue();
                facetChild.setValue(c.getName());
                facetChild.setMatches(""+c.getCount());
                f.addChild(facetChild);
                System.out.println("\t" + c.getName());
                System.out.println("\t" + c.getCount());
            }

        }
        return facets;
    }
    
    public String search( String criteria ) {return "";}
    public String getMetadataDetail( String doi ) {return "";}
}
