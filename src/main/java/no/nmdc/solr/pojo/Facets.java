package no.nmdc.solr.pojo;

import java.util.ArrayList;
import java.util.List;

public class Facets {
    private List<Facet> facets = new ArrayList<Facet>();

    public List<Facet> getFacets() {
        return facets;
    }

    public void setFacets(List<Facet> facets) {
        this.facets = facets;
    }
    
    public void addFacet(Facet facet) {
        facets.add(facet);
    }
}
