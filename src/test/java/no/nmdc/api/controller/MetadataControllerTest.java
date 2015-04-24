package no.nmdc.api.controller;

import java.util.Arrays;
import java.util.List;

import no.nmdc.api.MetadataApiImpl;
import no.nmdc.solr.request.FacetWhitelist;
import no.nmdc.solr.request.SolrRequests;

import org.junit.Before;
import org.junit.Test;

public class MetadataControllerTest {
    private MetadataController controller = new MetadataController();

    @Before
    public void setUp() {
        SolrRequests solrRequest = new SolrRequests();
        List<String> aWhitelist = Arrays.asList("Parameter");
        FacetWhitelist facetWhitelist = new FacetWhitelist();
        facetWhitelist.setFacetWhitelist(aWhitelist);
        solrRequest.setFacetWhitelist(facetWhitelist);
        MetadataApiImpl impl = new MetadataApiImpl();
        impl.setSolrRequest(solrRequest);
        controller.setMetadataApi(impl);
    }
    
    @Test
    public void getAllFacets() throws Exception {
        controller.getFacets();
    }

}
