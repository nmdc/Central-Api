package no.nmdc.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import no.nmdc.solr.pojo.FieldInfoComparator;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.LukeRequest;
import org.apache.solr.client.solrj.response.LukeResponse;
import org.apache.solr.client.solrj.response.LukeResponse.FieldInfo;

public class MetadataApiImpl implements MetadataApi {
    
    private String querySolr = "http://dev1.nmdc.no:8983/solr/nmdc/select?q=*%3A*&wt=json&indent=true&_=1428996346942";
    private String listFields = "http://dev1.nmdc.no:8983/solr/nmdc/admin/luke";
    
    private String facetedSearch = "&facet=true&facet.field=";
    
    private String facetedCount = "http://dev1.nmdc.no:8983/solr/nmdc1/select?q=*:*&wt=json&indent=true&facet=true&facet.query=*:*&facet.field=Parameter&_=1429170011253";
    
    public String getFacets() throws SolrServerException, IOException {
        
        SolrClient solrClient = new HttpSolrClient("http://dev1.nmdc.no:8983/solr/nmdc/");

        LukeRequest lukeRequest = new LukeRequest();
        lukeRequest.setNumTerms(1);
        LukeResponse lukeResponse = lukeRequest.process(solrClient);

        List<FieldInfo> sorted = new ArrayList<FieldInfo>(lukeResponse.getFieldInfo().values());
        Collections.sort(sorted, new FieldInfoComparator());
        for (FieldInfo infoEntry : sorted) {
            System.out.println("name: " + infoEntry.getName());
            System.out.println("docs: " + infoEntry.getDocs());
        }
        
        return "";
    }
    
    protected String getFields() throws Exception {
        SolrClient solrClient = new HttpSolrClient("http://dev1.nmdc.no:8983/solr/nmdc/");

        LukeRequest lukeRequest = new LukeRequest();
        lukeRequest.setNumTerms(1);
        LukeResponse lukeResponse = lukeRequest.process(solrClient);

        List<FieldInfo> sorted = new ArrayList<FieldInfo>(lukeResponse.getFieldInfo().values());
        Collections.sort(sorted, new FieldInfoComparator());
        for (FieldInfo infoEntry : sorted) {
            System.out.println("name: " + infoEntry.getName());
            System.out.println("docs: " + infoEntry.getDocs());
        }
        return "";
        
    }
    public String search( String criteria ) {return "";}
    public String getMetadataDetail( String doi ) {return "";}
}
