package no.nmdc.api;

import static org.junit.Assert.assertTrue;
import no.nmdc.solr.pojo.FacetName;
import no.nmdc.solr.pojo.FacetValue;
import no.nmdc.solr.pojo.Facets;

import org.junit.Test;


public class MetadataApiTest {

    private MetadataApiImpl impl = new MetadataApiImpl();
    
    @Test
    public void getFacetsTest() throws Exception {
        Facets facets = impl.getFacets();
        assertTrue( facets.getFacets().size() > 0 );
        for ( FacetName f : facets.getFacets() ) {
            System.out.println( "name:" + f.getName() );
            System.out.println( "matches:" + f.getMatches() );
            System.out.println( "children:" + f.getChildren() );
            for ( FacetValue child : f.getChildren() ) {
                System.out.println( "\t value:" + child.getValue() );
                System.out.println( "\t matches:" + child.getMatches() );
            }
        }
    }
    
    @Test
    public void getFieldsTest() throws Exception {
        Facets facets = impl.getFields();
        
    }
}
