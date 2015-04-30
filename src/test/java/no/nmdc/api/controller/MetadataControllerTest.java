package no.nmdc.api.controller;

import static org.junit.Assert.assertEquals;
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
    
    @Test
    public void searchTest() throws Exception {

        SearchResults resFail = controller.search( "Parameter:OCEAN%20TEMPERATURE", null );
        SearchResults resOk1 = controller.search( "Parameter:SALINITY/DENSITY", null );
        SearchResults resOk2 = controller.search( "Parameter:\"OCEAN TEMPERATURE\"", null ); //"Parameter:%22OCEAN%20TEMPERATURE%22"
        SearchResults resOk3 = controller.search( "Parameter:\"SALINITY/DENSITY\"", null );  //%22SALINITY/DENSITY%22
        
        Integer offset = new Integer(5);
        SearchResults resOffset = controller.search( "Parameter:\"SALINITY/DENSITY\"", offset );  
        System.out.println("resOffset:"+resOffset.getMatches() );
        assertEquals(resOffset.getMatches(), new Integer(1));
        
        System.out.println(resFail.getMatches());
        System.out.println(resOk1.getMatches());
        System.out.println(resOk2.getMatches());
        System.out.println(resOk3.getMatches());
    }
}
