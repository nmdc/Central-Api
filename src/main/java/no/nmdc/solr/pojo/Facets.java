package no.nmdc.solr.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="response")
public class Facets {
    @XmlElement
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
