package no.nmdc.solr.request;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import no.nmdc.api.domain.FacetName;
import no.nmdc.api.domain.Facets;
import no.nmdc.solr.domain.FieldInfoComparator;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.LukeRequest;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.LukeResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.LukeResponse.FieldInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SolrRequests {
    
    private String serverUrl = "http://dev1.nmdc.no:8983/solr/nmdc1/";
    
    private FacetWhitelist facetWhitelist;
    
    @Autowired
    public void setFacetWhitelist( FacetWhitelist whitelist ) {
        this.facetWhitelist = whitelist;
    }
    
    public Facets getFacetsFromSolr() throws Exception {
        List<FieldInfo> fieldInfos = getFieldInfo();
        Facets facets = new Facets();
        for (FieldInfo infoEntry : fieldInfos) {
            if ( facetWhitelist.isInWhitelist(infoEntry.getName()) ) {
                FacetName facet = new FacetName();
                facet.setName(infoEntry.getName());
                facets.addFacet(facet);
                System.out.println("name: " + infoEntry.getName());
                System.out.println("docs: " + infoEntry.getDocs());
            }
        }
        return facets;
    }
    
    protected List<FieldInfo> getFieldInfo() throws Exception {
        SolrClient solrClient = new HttpSolrClient(serverUrl);

        LukeRequest lukeRequest = new LukeRequest();
        lukeRequest.setNumTerms(1);
        LukeResponse lukeResponse = lukeRequest.process(solrClient);

        List<FieldInfo> sorted = new ArrayList<FieldInfo>(lukeResponse.getFieldInfo().values());
        Collections.sort(sorted, new FieldInfoComparator());
        return sorted;
    }
    
    public FacetField facetedQuery( FacetName facetName) throws Exception {
        SolrClient solrClient = new HttpSolrClient(serverUrl);
        
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");
        solrQuery.setFacet(true);
        solrQuery.setRows(new Integer(0));
        solrQuery.setFacetMinCount(1);
        solrQuery.addFacetField(facetName.getName());
    
        QueryResponse queryResponse = solrClient.query(solrQuery);
        List<FacetField> fields = queryResponse.getFacetFields();
        FacetField theField = fields.get(0);
        return theField;
    }
}
