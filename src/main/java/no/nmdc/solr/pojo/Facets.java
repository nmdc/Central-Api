package no.nmdc.solr.pojo;

import java.util.ArrayList;
import java.util.List;

public class Facets {
    private List<FacetName> facets = new ArrayList<FacetName>();

    public List<FacetName> getFacets() {
        return facets;
    }

    public void setFacets(List<FacetName> facets) {
        this.facets = facets;
    }
    
    public void addFacet(FacetName facet) {
        facets.add(facet);
    }
}
