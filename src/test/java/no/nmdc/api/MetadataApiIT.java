package no.nmdc.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import no.nmdc.api.facets.domain.FacetName;
import no.nmdc.api.facets.domain.FacetValue;
import no.nmdc.api.facets.domain.Facets;
import no.nmdc.api.search.domain.SearchParameters;
import no.nmdc.api.search.domain.SearchResults;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-test-context.xml"})
public class MetadataApiIT {
    
    @Autowired
    private MetadataApiImpl impl;
    
    SearchParameters searchParam;
    
    @Before
    public void setup() {
        String query = "Provider:Havforskningsinstituttet";
        Integer offset = null;
        String boundingBox = "";
        String beginDate = "";
        String endDate = "";
        
        searchParam = new SearchParameters(query, offset, beginDate, endDate, boundingBox);
    }
    
    @Test
    public void getFacetsIT() throws Exception {
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
    public void getParameterHierachyIT() throws Exception {
        Facets facets = new Facets();
        FacetName name = new FacetName();
        name.setName("parameter");
        name.setMatches("10");
        FacetValue value1 = new FacetValue();
        value1.setValue("EARTH SCIENCE > OCEANS > OCEAN TEMPERATURE > WATER TEMPERATURE");
        value1.setMatches("10");

        FacetValue value2 = new FacetValue();
        value2.setValue("EARTH SCIENCE > OCEANS > SALINITY/DENSITY > SALINITY");
        value2.setMatches("5");
        
        name.addChild(value1);
        name.addChild(value2);
        facets.addFacet(name);
        
        impl.createFacetHierarchy(facets);
    }

    @Test
    public void searchForOneFacetIT() throws Exception {

        SearchResults results = impl.search(searchParam);
        System.out.println("results:"+results.getMatches());
        assertNotNull(results);
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
    
    @Test
    public void getMetadataDetailsTest() throws Exception {
        SearchResults results = impl.getMetadataDetail("ii_1420");
        System.out.println("*********** resultat:"+results.getResults());
        SearchResults results2 = impl.search(new SearchParameters("Entry_ID:\"ii_1420\"", 10, "", "", ""));
        System.out.println("*********** resultat:"+results2.getResults());
    }
}
