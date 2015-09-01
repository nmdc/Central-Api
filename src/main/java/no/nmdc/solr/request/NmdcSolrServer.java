package no.nmdc.solr.request;

import java.io.IOException;

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
        solrClient = new HttpSolrClient(getSolrUrl());
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
        return solrClient;
    }
    
    public QueryResponse query( SolrQuery solrQuery) throws IOException, SolrServerException {
        return solrClient.query(solrQuery);
    }
}
