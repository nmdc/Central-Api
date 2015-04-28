package no.nmdc.solr.request;

import javax.annotation.PostConstruct;

import org.apache.commons.configuration.Configuration;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NmdcSolrServer {
    
    private SolrClient solrClient = null;
    
    private String solrUrl = null;
    
    @Autowired
    private Configuration configuration;
    
    @PostConstruct
    public void setup() {
        
    }
    
    private String getSolrUrl() {
        if (solrUrl == null )
            solrUrl = configuration.getString("solr.url");
        return solrUrl;
    }
    
    public void setSolrUrl(String solrUrl ) {
        this.solrUrl = solrUrl;
    }
    
    public SolrClient getSolrClient() {
        return new HttpSolrClient(getSolrUrl());
    }
    
    public QueryResponse query( SolrQuery solrQuery) throws SolrServerException {
        solrClient = new HttpSolrClient(getSolrUrl());
        return solrClient.query(solrQuery);
    }
}
