package no.nmdc.solr.pojo;


public class FacetValue {
    private String value;
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
