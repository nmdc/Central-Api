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
    private NmdcSolrServer solr;
    
    @Autowired
    private CreateSearchRequest searchReq;
    
    @Test
    public void search() throws Exception {
        
        SearchParameters query = new SearchParameters();
        query.setQuery("Provider:Havforskningsinstituttet");
        SolrDocumentList docs = searchReq.search( query );         
        SolrDocument doc = docs.get(0);
        
        System.out.println("doc:"+doc);
        String entryId = (String)doc.getFieldValue("Entry_ID");
        assertTrue( entryId.length() > 0 );
        assertNotNull( doc.getFieldValue("Start_Date") );
    }
    
    @Test
    public void searchTwoFacets() throws Exception {

        SearchParameters query = new SearchParameters();
        query.setQuery("Provider:Institute of Marine Research, Bergen, Norway OR Parameter:\"EARTH SCIENCE>BIOSPHERE>AQUATIC ECOSYSTEMS>BENTHIC HABITAT\"");
        SolrDocumentList docs = searchReq.search( query );         
        SolrDocument doc = docs.get(0);
        
        System.out.println("doc:"+doc);
        String entryId = (String)doc.getFieldValue("Entry_ID");
        assertTrue( entryId.length() > 0 );
        assertNotNull( doc.getFieldValue("Start_Date") );
    }    
    
    @Test
    public void searchNorwegianLetter() throws Exception {

        SearchParameters r = new SearchParameters();
        r.setQuery("sognesjøen");
        
        SolrDocumentList docs = searchReq.search(r);
        System.out.println("size docs:"+docs.size());
        SolrDocument doc = docs.get(0);
        
        System.out.println("doc:"+doc);
    }    
    
    @Test
    public void searchBeginDateIntersects() throws Exception {

        SearchParameters r = new SearchParameters();
        r.setBeginDate("1901-01-02T20:00:00Z");
        
        SolrDocumentList docs = searchReq.search(r);
        System.out.println("size docs:"+docs.size());
    }
    
    @Test
    public void searchBeginDateIntersects2() throws Exception {

        SearchParameters r = new SearchParameters();
        r.setBeginDate("2006-01-02T20:00:00Z");
        
        SolrDocumentList docs = searchReq.search(r);
        System.out.println("size docs:"+docs.size());
    }    
    
    @Test
    public void searchBeginDateIntersects3() throws Exception {

        SearchParameters r = new SearchParameters();
        r.setBeginDate("1999-01-02T20:00:00Z");
        
        SolrDocumentList docs = searchReq.search(r);
        System.out.println("size docs:"+docs.size());
    }        
    
    @Test
    public void searchBeginDateAndEndDateIntersects() throws Exception {

        SearchParameters r = new SearchParameters();
        r.setBeginDate("1998-01-02T20:00:00Z");
        r.setEndDate("1999-01-02T20:00:00Z");
        
        SolrDocumentList docs = searchReq.search(r);
        System.out.println("size docs:"+docs.size());
    }      
    
    @Test
    public void searchBeginDateAndEndDateIntersects2() throws Exception {

        SearchParameters r = new SearchParameters();
        r.setBeginDate("2006-01-02T20:00:00Z");
        r.setEndDate("2007-01-02T20:00:00Z");

        SolrDocumentList docs = searchReq.search(r);
        System.out.println("size docs:"+docs.size());
    }     
    
    @Test
    public void searchEndDateIntersects() throws Exception {

        SearchParameters r = new SearchParameters();
        r.setEndDate("1999-01-02T20:00:00Z");
        
        SolrDocumentList docs = searchReq.search(r);
        System.out.println("size docs:"+docs.size());
    }        
    
    @Test
    public void searchEndDateIntersects2() throws Exception {

        SearchParameters r = new SearchParameters();
        r.setEndDate("2006-01-02T20:00:00Z");
        
        SolrDocumentList docs = searchReq.search(r);
        System.out.println("size docs:"+docs.size());
    } 
    
    @Test
    public void searchBeginDateAndEndDateIsWithin() throws Exception {

        SearchParameters r = new SearchParameters();
        r.setDateSearchMode(SearchParameters.DATE_IS_WITHIN_RECORD_RANGE);
        r.setBeginDate("2003-01-02T20:00:00Z");
        r.setEndDate("2004-01-02T20:00:00Z");
        
        SolrDocumentList docs = searchReq.search(r);
        System.out.println("size docs:"+docs.size());
    }     
    
    @Test
    public void searchBeginDateAndEndDateIsWithin2() throws Exception {

        SearchParameters r = new SearchParameters();
        r.setDateSearchMode(SearchParameters.DATE_IS_WITHIN_RECORD_RANGE);
        r.setBeginDate("1937-01-02T20:00:00Z");
        r.setEndDate("1938-01-02T20:00:00Z");
        
        SolrDocumentList docs = searchReq.search(r);
        System.out.println("size docs:"+docs.size());
    }  
    
    @Test
    public void searchBeginDateAndEndDateIsWithin3() throws Exception {

        SearchParameters r = new SearchParameters();
        r.setDateSearchMode(SearchParameters.DATE_IS_WITHIN_RECORD_RANGE);
        r.setEndDate("1999-01-02T20:00:00Z");
        
        SolrDocumentList docs = searchReq.search(r);
        System.out.println("size docs:"+docs.size());
    }  
    
    @Test
    public void searchMultipleProviders() throws Exception {
        String search = "(Provider:\"NO/NPI\" OR Provider:\"Norwegian Institute for Water Research\" OR Provider:\"NERSC | Nansen Environmental and Remote Sensing Center\")";
        SearchParameters r = new SearchParameters();
        r.setQuery(search);
        
        SolrDocumentList docs = searchReq.search(r);
        System.out.println("size docs:"+docs.size());
    }
    
    @Test
    public void searchIsWithin2DatesAND_ORSomethingElse() throws Exception {

        SearchParameters r = new SearchParameters();
        
        r.setDateSearchMode(SearchParameters.DATE_IS_WITHIN_RECORD_RANGE);
        r.setBeginDate("1969-12-31T23:00:00Z");
        r.setEndDate("2015-08-17T22:00:00Z");
        r.setQuery("CTD");
        
        SolrDocumentList docs = searchReq.search(r);
        System.out.println("size docs:"+docs.getNumFound());
    }  
}
