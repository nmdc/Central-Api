package no.nmdc.solr.pojo;

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
    
    public String getValue() {
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
}
