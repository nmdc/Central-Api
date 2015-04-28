package no.nmdc.api.controller;

import java.util.Arrays;

import no.nmdc.api.MetadataApiImpl;
import no.nmdc.api.domain.SearchResults;
import no.nmdc.solr.request.FacetRequest;
import no.nmdc.solr.request.FacetWhitelist;
import no.nmdc.solr.request.NmdcSolrServer;
import no.nmdc.solr.request.QueryRequest;

import org.junit.Before;
import org.junit.Test;


public class MetadataControllerTest {
    
    private MetadataController controller = new MetadataController();

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
        controller.setMetadataApi(impl);
    }
    
    @Test
    public void getAllFacets() throws Exception {
        controller.getFacets();
    }
    
    @Test
    public void testGetTables() throws Exception {

        SearchResults resFail = controller.search( "Parameter:OCEAN%20TEMPERATURE" );
        SearchResults resOk1 = controller.search( "Parameter:SALINITY/DENSITY" );
        SearchResults resOk2 = controller.search( "Parameter:%22OCEAN%20TEMPERATURE%22" );
        SearchResults resOk3 = controller.search( "Parameter:%22SALINITY/DENSITY%22" );
        
        System.out.println(resFail.getMatches());
        System.out.println(resOk1.getMatches());
        System.out.println(resOk2.getMatches());
        System.out.println(resOk3.getMatches());

    }

}
