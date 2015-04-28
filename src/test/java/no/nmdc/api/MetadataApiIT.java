package no.nmdc.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import no.nmdc.api.domain.SearchResults;
import no.nmdc.api.domain.facets.FacetName;
import no.nmdc.api.domain.facets.FacetValue;
import no.nmdc.api.domain.facets.Facets;
import no.nmdc.solr.request.FacetRequest;
import no.nmdc.solr.request.FacetWhitelist;
import no.nmdc.solr.request.QueryRequest;
import no.nmdc.solr.request.NmdcSolrServer;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

public class MetadataApiIT {
    
    private MetadataApiImpl impl = new MetadataApiImpl();
    
    private NmdcSolrServer solr = new NmdcSolrServer();
    QueryRequest query = new QueryRequest();
    FacetRequest luke = new FacetRequest();
    
    @Before
    public void setUp() {
        solr.setSolrUrl("http://dev1.nmdc.no:8983/solr/nmdc");
        query.setSolr(solr);
        luke.setSolr(solr);
        luke.setFacetWhitelist( new FacetWhitelist( Arrays.asList("Provider")) );
        impl.setQueryRequest(query);
        impl.setFacetRequest(luke);
    }
    
    
    @Test
    public void getFacetsIntegrationTest() throws Exception {
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
    public void getFieldsIntegrationTest() throws Exception {
        Facets facets = impl.getFacets();

    }
    
    @Test
    public void searchForOneFacetIntegrationTest() throws Exception {
//        SearchResults r = impl.search("OCEAN TEMPERATURE", "Parameter");
        impl.search("Provider:Havforskningsinstituttet");
        SearchResults r = new SearchResults();
        System.out.println("r:"+r);
        assertNotNull(r);
    }
}
