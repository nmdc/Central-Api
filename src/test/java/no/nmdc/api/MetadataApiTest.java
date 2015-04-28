package no.nmdc.api;

import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import no.nmdc.solr.request.FacetRequest;
import no.nmdc.solr.request.QueryRequest;

@RunWith(MockitoJUnitRunner.class)
public class MetadataApiTest {
    
    private MetadataApiImpl impl = new MetadataApiImpl();
    
    @Mock
    QueryRequest queryRequest;
    
    @Mock
    FacetRequest lukeRequest;
    
    @Test
    public void searchTest() throws Exception {
        impl.setFacetRequest(lukeRequest);
        impl.setQueryRequest(queryRequest);
        
        queryRequest.search("");
        Mockito.doReturn(new SolrDocumentList()).when(queryRequest).search("");
        
        
        
        impl.search("");
    }
}
