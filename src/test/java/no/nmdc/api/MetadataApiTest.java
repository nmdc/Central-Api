package no.nmdc.api;

import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class MetadataApiTest {

    private MetadataApiImpl impl = new MetadataApiImpl();
    
    @Test
    public void getFacetsTest() throws Exception {
        String json = impl.getFacets();
        assertTrue(json.contains("\"provider\":{"));
    }
    
    @Test
    public void getFieldsTest() throws Exception {
        String fieldXml = impl.getFields();
        
    }
}
