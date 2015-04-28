package no.nmdc.solr.request;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Before;
import org.junit.Test;

public class SolrRequestsTest {

    private NmdcSolrServer solr = new NmdcSolrServer();
    QueryRequest query = new QueryRequest();
    
    @Before
    public void setUp() {
        solr.setSolrUrl("http://dev1.nmdc.no:8983/solr/nmdc");
        query.setSolr(solr);
    }
    
    @Test
    public void search() throws Exception {

        SolrDocumentList docs = query.search("Provider:Havforskningsinstituttet");         
        SolrDocument doc = docs.get(0);
        
        System.out.println("doc:"+doc);
        String entryId = (String)doc.getFieldValue("Entry_ID");
        assertTrue( entryId.contains("imr") );
        assertNotNull( doc.getFieldValue("Start_Date") );
    }
    
    @Test
    public void searchTwoFacets() throws Exception {

        SolrDocumentList docs = query.search("Provider:Havforskningsinstituttet AND Parameter:SALINITY/DENSITY");         
        SolrDocument doc = docs.get(0);
        
        System.out.println("doc:"+doc);
        String entryId = (String)doc.getFieldValue("Entry_ID");
        assertTrue( entryId.contains("imr") );
        assertNotNull( doc.getFieldValue("Start_Date") );
    }    
}
