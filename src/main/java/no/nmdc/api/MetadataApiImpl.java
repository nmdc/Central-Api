package no.nmdc.api;

public class MetadataApiImpl implements MetadataApi {
    
    private String querySolr = "http://dev1.nmdc.no:8983/solr/nmdc/select?q=*%3A*&wt=json&indent=true&_=1428996346942";
    
    public String getFacets() {return "";}
    public String search( String criteria ) {return "";}
    public String getMetadataDetail( String doi ) {return "";}
}
