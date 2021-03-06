package no.nmdc.api.search.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SearchResults {
    
    @XmlElement
    private Integer matches;
    
    @XmlElement
    private Long numFound;

    @XmlElement
    private List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();

    public Integer getMatches() {
        return matches;
    }

    public void setMatches(Integer matches) {
        this.matches = matches;
    }
    
    public Long getNumFound() {
        return numFound;
    }

    public void setNumFound(Long numFound) {
        this.numFound = numFound;
    }
    
    public List<Map<String, Object>> getResults() {
        return results;
    }

    public void setResults(List<Map<String, Object>> results) {
        this.results = results;
    }
    
    public void addResult( Map<String, Object> record ) {
        this.results.add( record );
    }

}
