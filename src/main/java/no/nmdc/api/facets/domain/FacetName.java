package no.nmdc.api.facets.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "facetName")
public class FacetName {
    @XmlElement
    private String name;
    @XmlElement
    private String matches;
    @XmlElement
    private List<FacetValue> children = new ArrayList<FacetValue>();
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMatches() {
        return matches;
    }
    public void setMatches(String matches) {
        this.matches = matches;
    }
    public List<FacetValue> getChildren() {
        return children;
    }
    public void setChildren(List<FacetValue> children) {
        this.children = children;
    }
    
    public void addChild(FacetValue child) {
        this.children.add(child);
    }
    
    @Override
    public String toString() {
        return "name:"+name+", matches:"+matches;
    }
}
