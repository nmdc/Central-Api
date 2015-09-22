package no.nmdc.api.controller;

import static org.junit.Assert.assertTrue;
import no.nmdc.api.search.domain.SearchResults;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-test-context.xml"})
public class MetadataControllerTest {
    
    @Autowired
    private MetadataController controller;
    
    private String query = "";
    private Integer offset = null;
    private String boundingBox = "";
    private String beginDate = "";
    private String endDate = "";
    private String dateSearchMode = "";
    
    @Test
    public void searchTest() throws Exception {
        
        SearchResults resFail = controller.search( "Parameter:OCEAN%20TEMPERATURE", offset, beginDate, endDate, boundingBox, dateSearchMode );
        SearchResults resOk1 = controller.search( "Parameter:*SALINITY/DENSITY*", offset, beginDate, endDate, boundingBox, dateSearchMode );
        SearchResults resOk2 = controller.search( "Parameter:*WATER TEMPERATURE*", offset, beginDate, endDate, boundingBox, dateSearchMode ); 
        
        assertTrue( resFail.getMatches() == 0 );
        assertTrue( resOk1.getMatches() > 0 );
        assertTrue( resOk2.getMatches() > 0 );
    }
    
    @Test
    public void offsetTest() throws Exception {

        Integer offset = new Integer(5);
        SearchResults resOffset = controller.search( "Parameter:*SALINITY/DENSITY*", offset, beginDate, endDate, boundingBox, dateSearchMode );  
        assertTrue( resOffset.getMatches() > 0 );
    }
    
    @Test
    public void bboxTest() throws Exception {

        String bbox = "location_rpt:\"Intersects(POLYGON((6.0 55.0, 10.0 55.0, 10.0 70.0, 6.0 70.0, 6.0 55.0)))\"";
        
        SearchResults resbbox = controller.search( query, offset, beginDate, endDate, bbox, dateSearchMode );  
        assertTrue( resbbox.getMatches() > 0);
    }
    
    @Test
    public void getAllDocumentsTest() throws Exception {
        
        SearchResults resbbox = controller.search( query, offset, beginDate, endDate, boundingBox, dateSearchMode );
        assertTrue( resbbox.getMatches() > 0);
    }
}
