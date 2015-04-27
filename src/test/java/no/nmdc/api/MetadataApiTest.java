package no.nmdc.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import no.nmdc.api.domain.SearchResults;
import no.nmdc.api.domain.facets.FacetName;
import no.nmdc.api.domain.facets.FacetValue;
import no.nmdc.api.domain.facets.Facets;
import no.nmdc.solr.request.FacetWhitelist;
import no.nmdc.solr.request.SolrRequests;

import org.junit.Before;
import org.junit.Test;


public class MetadataApiTest {
    
    private MetadataApiImpl impl = new MetadataApiImpl();

    @Before
    public void setUp() {
        SolrRequests solrRequest = new SolrRequests();
        List<String> aWhitelist = Arrays.asList("Parameter");
        FacetWhitelist facetWhitelist = new FacetWhitelist();
        facetWhitelist.setFacetWhitelist(aWhitelist);
        solrRequest.setFacetWhitelist(facetWhitelist);
        impl.setSolrRequest(solrRequest);
    }
    
    
    @Test
    public void getFacetsTest() throws Exception {
        Facets facets = impl.getFacets();
        assertTrue( facets.getFacets().size() > 0 );
        for ( FacetName f : facets.getFacets() ) {
            System.out.println( "name:" + f.getName() );
            System.out.println( "matches:" + f.getMatches() );
            System.out.println( "children:" + f.getChildren() );
            for ( FacetValue child : f.getChildren() ) {
                System.out.println( "\t value:" + child.getValue() );
                System.out.println( "\t matches:" + child.getMatches() );
            }
        }
    }
    
    @Test
    public void getFieldsTest() throws Exception {
        Facets facets = impl.getFacets();
        
    }
    
    @Test
    public void searchForOneFacet() throws Exception {
//        SearchResults r = impl.search("OCEAN TEMPERATURE", "Parameter");
        impl.search("Havforskningsinstituttet", "Provider");
        SearchResults r = new SearchResults();
        System.out.println("r:"+r);
        assertNotNull(r);
    }
}
