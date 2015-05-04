package no.nmdc.api;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.Map;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import no.nmdc.api.search.domain.SearchParameters;
import no.nmdc.api.search.domain.SearchResults;
import no.nmdc.solr.request.FacetRequest;
import no.nmdc.solr.request.SearchRequest;

@RunWith(MockitoJUnitRunner.class)
public class MetadataApiTest {
    
    private MetadataApiImpl impl = new MetadataApiImpl();
    
    @Mock
    SearchRequest queryRequest;
    
    @Mock
    FacetRequest lukeRequest;
    
    SearchParameters searchParam;
    
    @Before
    public void setup() {
        String query = "";
        Integer offset = null;
        String boundingBox = null;
        Date beginDate = null;
        Date endDate = null;
        
        searchParam = new SearchParameters(query, offset, beginDate, endDate, boundingBox);
    }
    
    @Test
    public void searchTest() throws Exception {
        impl.setQueryRequest(queryRequest);
        
        Mockito.doReturn(searchResult()).when(queryRequest).search(searchParam);
        
        SearchResults results = impl.search(searchParam);
        assertEquals(results.getMatches(), new Integer(2));
        
        Map<String, Object> result1 = results.getResults().get(0);
        assertEquals(result1.get("field1"), "value1");
    }
    
    private SolrDocumentList searchResult() {
        SolrDocumentList docList = new SolrDocumentList();
        SolrDocument doc1 = new SolrDocument();
        doc1.setField("field1", "value1");
        
        SolrDocument doc2 = new SolrDocument();
        doc1.setField("field2", "value2");
        
        docList.add(doc1);
        docList.add(doc2);
        return docList;
    }
}
