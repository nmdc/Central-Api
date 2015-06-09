package no.nmdc.solr.request;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import no.nmdc.api.search.domain.SearchParameters;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-test-context.xml")
public class SolrRequestsIT {
    
    @Autowired
    private NmdcSolrServer solr = new NmdcSolrServer();
    
    @Autowired
    private SearchRequest query = new SearchRequest();
    
    @Test
    public void search() throws Exception {
        
        SolrDocumentList docs = query.search("Provider:Havforskningsinstituttet", null);         
        SolrDocument doc = docs.get(0);
        
        System.out.println("doc:"+doc);
        String entryId = (String)doc.getFieldValue("Entry_ID");
        assertTrue( entryId.contains("imr") );
        assertNotNull( doc.getFieldValue("Start_Date") );
    }
    
    @Test
    public void searchTwoFacets() throws Exception {

        SolrDocumentList docs = query.search("Provider:Havforskningsinstituttet AND Parameter:SALINITY/DENSITY", null);         
        SolrDocument doc = docs.get(0);
        
        System.out.println("doc:"+doc);
        String entryId = (String)doc.getFieldValue("Entry_ID");
        assertTrue( entryId.contains("imr") );
        assertNotNull( doc.getFieldValue("Start_Date") );
    }    
    
    @Test
    public void searchNorwegianLetter() throws Exception {

        SearchParameters r = new SearchParameters();
        r.setQuery("sognesjøen");
        
        SolrDocumentList docs = query.search(r);
        System.out.println("size docs:"+docs.size());
        SolrDocument doc = docs.get(0);
        
        System.out.println("doc:"+doc);

    }    
    
    @Test
    public void searchDate() throws Exception {

        SearchParameters r = new SearchParameters();
        r.setQuery("*");
        r.setBeginDate("1901-01-02T20:00:00Z");
        
        SolrDocumentList docs = query.search(r);
        System.out.println("size docs:"+docs.size());
        SolrDocument doc = docs.get(0);
        
        System.out.println("doc size:"+docs.size());
        System.out.println("doc:"+doc);

    }  
}
