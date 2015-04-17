package no.nmdc.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import no.nmdc.solr.pojo.Facet;
import no.nmdc.solr.pojo.Facets;
import no.nmdc.solr.pojo.FieldInfoComparator;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.LukeRequest;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.LukeResponse;
import org.apache.solr.client.solrj.response.LukeResponse.FieldInfo;
import org.apache.solr.client.solrj.response.QueryResponse;

public class MetadataApiImpl implements MetadataApi {
    
    private String querySolr = "http://dev1.nmdc.no:8983/solr/nmdc/select?q=*%3A*&wt=json&indent=true&_=1428996346942";
    private String listFields = "http://dev1.nmdc.no:8983/solr/nmdc/admin/luke";
    
    private String facetedCount0  = "/solr/nmdc/select?facet=true&facet.mincount=1&rows=100&start=0&facet.field=Entry_ID&q=*.*&wt=javabin&version=2 HTTP/1.1";
    private String facetedCount1 = "http://dev1.nmdc.no:8983/solr/nmdc1/select?";
    private String queryString1 = "q=*:*&wt=json&indent=true&facet=true&facet.query=*:*&facet.field=";
    
    private String serverUrl = "http://dev1.nmdc.no:8983/solr/nmdc1/";
    
    private String facetedSearch = "q=*:*&rows=0&facet=true&facet.field=Parameter";
    
    public String getFieldFaceting() throws Exception {
        
        Facets facets = getFields();
        for ( Facet f : facets.getFacets() ) {
            SolrClient solrClient = new HttpSolrClient(serverUrl);
    
            SolrQuery solrQuery = new SolrQuery();
            solrQuery.setQuery("*:*");
            solrQuery.setFacet(true);
            solrQuery.setRows(new Integer(0));
            solrQuery.setFacetMinCount(1);
            solrQuery.addFacetField(f.getName());
 
            QueryResponse queryResponse = solrClient.query(solrQuery);
            List<FacetField> fields = queryResponse.getFacetFields();
            FacetField theField = fields.get(0);
            
            System.out.println("name:"+f.getName()+" matches:"+theField.getValueCount());
            f.setMatches( "" + theField.getValueCount() );
            for ( Count c : theField.getValues() ) {
                System.out.println("\t" + c.getName());
                System.out.println("\t" + c.getCount());
                String value = f.getValue();
            }

        }
        
        return "";
    }
    
    protected Facets getFields() throws Exception {
        SolrClient solrClient = new HttpSolrClient(serverUrl);

        LukeRequest lukeRequest = new LukeRequest();
        lukeRequest.setNumTerms(1);
        LukeResponse lukeResponse = lukeRequest.process(solrClient);

        List<FieldInfo> sorted = new ArrayList<FieldInfo>(lukeResponse.getFieldInfo().values());
        Collections.sort(sorted, new FieldInfoComparator());
        Facets facets = new Facets();
        for (FieldInfo infoEntry : sorted) {
            Facet facet = new Facet();
            facet.setName(infoEntry.getName());
            facets.addFacet(facet);
            System.out.println("name: " + infoEntry.getName());
            System.out.println("docs: " + infoEntry.getDocs());
        }
        return facets;
        
    }
    public String search( String criteria ) {return "";}
    public String getMetadataDetail( String doi ) {return "";}
}
