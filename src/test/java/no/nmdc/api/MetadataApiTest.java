package no.nmdc.api;

import static org.junit.Assert.assertTrue;
import no.nmdc.solr.pojo.Facets;

import org.junit.Test;


public class MetadataApiTest {

    private MetadataApiImpl impl = new MetadataApiImpl();
    
    @Test
    public void getFacetsTest() throws Exception {
        String json = impl.getFieldFaceting();
        assertTrue(json.contains("\"provider\":{"));
    }
    
    @Test
    public void getFieldsTest() throws Exception {
        Facets facets = impl.getFields();
        
    }
}
