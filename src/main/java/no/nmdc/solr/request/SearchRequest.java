package no.nmdc.solr.request;

import java.util.List;

import no.nmdc.api.facets.domain.FacetName;
import no.nmdc.api.search.domain.SearchParameters;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Query solr server using the solrj API
 * Dateformat is ISO 8601 (UTC date) yyyy-mm-ddThh:mm:ss.mmmZ 
 * 
 * @author endrem
 *
 */
@Component
public class SearchRequest {
    
    private final Integer DEFAULT_ROWS = 10;
    
    private NmdcSolrServer solr;
    
    private DateHelper dateHelper = new DateHelper();
    
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

    public SolrDocumentList search( SearchParameters request ) throws Exception {

        SolrQuery solrQuery = new SolrQuery();
        String query = request.getQuery();
        if ( request.getQuery() == null || request.getQuery().equals("") ) {
            query = "*:*";    
        }
        solrQuery.setStart( request.getOffset());
        solrQuery.setRows( DEFAULT_ROWS );
        solrQuery.setFilterQueries( request.getBbox() );
        if ( !request.getBeginDate().equals("") || !request.getEndDate().equals("") ) { 
            query += " AND Start_Date:" + dateHelper.createSolrDateQuerySyntax(request.getBeginDate(), request.getEndDate());
        }

        solrQuery.setQuery( query );
        QueryResponse queryResponse = solr.query(solrQuery);
        return queryResponse.getResults();
    }
}
