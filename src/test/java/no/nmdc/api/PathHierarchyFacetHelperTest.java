package no.nmdc.api;

import no.nmdc.api.facets.domain.FacetName;
import no.nmdc.api.facets.domain.FacetValue;
import no.nmdc.api.facets.domain.Facets;

import org.junit.Test;

public class PathHierarchyFacetHelperTest {
    
    private PathHierarchyFacetHelper pathHierarchyFacetHelper = new PathHierarchyFacetHelper();

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
        
        pathHierarchyFacetHelper.createFacetHierarchy(facets);
    }
}
