package no.nmdc.api.facets.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "facetValue")
public class FacetValue {
    @XmlElement
    private String value;
    @XmlElement
    private String matches;

    private List<FacetValue> childFacets = new ArrayList<FacetValue>(2);
    
    public String getValue() {
        if ( value == null )
            return "";
        else 
            return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getMatches() {
        return matches;
    }
    public void setMatches(String matches) {
        this.matches = matches;
    }
    public List<FacetValue> getChildFacets() {
        return childFacets;
    }
    public void setChildFacets(List<FacetValue> childFacets) {
        this.childFacets = childFacets;
    }
    public void addChildFacet(FacetValue childFacet) {
        this.childFacets.add(childFacet);
    }
    
    @Override
    public String toString() {
        String childToString = "";
        for ( FacetValue value : childFacets ) {
            childToString += value.toString();
        }
        return "value:"+value+" matches:"+matches+" child:["+childToString+"]";
    }
}
