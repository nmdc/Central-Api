package no.nmdc.solr.pojo;

import java.util.ArrayList;
import java.util.List;

public class Facet {
    private String value;
    private String matches;
    private List<Facet> children = new ArrayList<Facet>();
    
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
    public List<Facet> getChildren() {
        return children;
    }
    public void setChildren(List<Facet> children) {
        this.children = children;
    }
}
