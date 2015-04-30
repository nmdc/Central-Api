package no.nmdc.solr.request;

import java.util.List;

import no.nmdc.api.facets.domain.FacetName;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueryRequest {
    
    private final Integer DEFAULT_ROWS = 10;
    
    private NmdcSolrServer solr;
    
    @Autowired
    public void setSolr(NmdcSolrServer solr) {
        this.solr = solr;
    }

    public FacetField facetedQuery( FacetName facetName) throws Exception {
        
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");
        solrQuery.setFacet(true);
        solrQuery.setRows(new Integer(0));
        solrQuery.setFacetMinCount(1);
        solrQuery.addFacetField(facetName.getName());
    
        QueryResponse queryResponse = solr.query(solrQuery);
        List<FacetField> fields = queryResponse.getFacetFields();
        if (fields.size() > 0) {
            FacetField theField = fields.get(0);
            return theField;
        } else return new FacetField("");
    }    
    
    public SolrDocumentList search(String query, Integer offset) throws Exception {
        
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery( query );
        if ( offset != null ) {
            solrQuery.setStart(offset);
            solrQuery.setRows( DEFAULT_ROWS );
        }

        QueryResponse queryResponse = solr.query(solrQuery);
        System.out.println("queryResponse:"+queryResponse.toString());
        if (queryResponse.getFacetFields() != null)
            System.out.println("FacetedField:"+queryResponse.getFacetFields().toString());
        System.out.println("Result:"+queryResponse.getResults().toString());
        List<SolrDocument> docs = queryResponse.getResults(); //there is no facet count returned (?)
        
        SolrDocumentList docList = queryResponse.getResults();
        return docList;
    }

}
