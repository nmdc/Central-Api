package no.nmdc.api.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SearchResults {
    
    @XmlElement
    private Integer matches;
    
    @XmlElement
    private List<SearchResult> results = new ArrayList<SearchResult>(9);

    public Integer getMatches() {
        return matches;
    }

    public void setMatches(Integer matches) {
        this.matches = matches;
    }

    public List<SearchResult> getResults() {
        return results;
    }

    public void setResults(List<SearchResult> results) {
        this.results = results;
    }
    public void addResult( SearchResult result ) {
        this.results.add( result );
    }

}
