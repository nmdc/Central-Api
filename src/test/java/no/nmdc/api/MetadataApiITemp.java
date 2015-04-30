package no.nmdc.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import no.nmdc.api.facets.domain.FacetName;
import no.nmdc.api.facets.domain.FacetValue;
import no.nmdc.api.facets.domain.Facets;
import no.nmdc.api.search.domain.SearchResults;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-test-context.xml"})
public class MetadataApiITemp {
    
    @Autowired
    private MetadataApiImpl impl;
    
    
    @Test
    public void getFacetsIntegrationTest() throws Exception {
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
    public void getFieldsIntegrationTest() throws Exception {
        Facets facets = impl.getFacets();

    }
    
    @Test
    public void searchForOneFacetIntegrationTest() throws Exception {
//        SearchResults r = impl.search("OCEAN TEMPERATURE", "Parameter");
        impl.search("Provider:Havforskningsinstituttet", null);
        SearchResults r = new SearchResults();
        System.out.println("r:"+r);
        assertNotNull(r);
    }
    
    @Test
    public void marshallFacetsTest() throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Facets.class);
        
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        StringWriter writer = new StringWriter();
        marshaller.marshal(((Object)impl.getFacets()), writer);
        
        String facetXml = writer.getBuffer().toString();
        assertTrue(facetXml.contains("<name>Provider</name>"));
        assertTrue(facetXml.contains("<value>Havforskningsinstituttet</value>"));
    }
}
