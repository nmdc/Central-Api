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
public class CreateSearchRequest {
    
    private final Integer DEFAULT_ROWS = 10;
    
    private final Integer FACET_LIMIT = 10000;
    
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
        solrQuery.setFacetLimit(FACET_LIMIT);
    
        QueryResponse queryResponse = solr.query(solrQuery);
        List<FacetField> fields = queryResponse.getFacetFields();
        if ( !fields.isEmpty()) {
            return fields.get(0);
        } else{ 
            return new FacetField("");
        }
    }    

    public SolrDocumentList search( SearchParameters request ) throws Exception {

        SolrQuery solrQuery = new SolrQuery();
        String query = request.getQuery();
        solrQuery.setStart( request.getOffset());
        solrQuery.setRows( DEFAULT_ROWS );
        solrQuery.setFilterQueries( request.getBbox() );
        if ( !"".equals(request.getBeginDate() ) || !"".equals(request.getEndDate()) ) {
            if ( !query.equals("") ) {
                query += " AND ";
            }
            if ( request.getDateSearchMode().equals(SearchParameters.DATE_INTERSECTS_RECORD_RANGE) ) {
                query += dateHelper.getStartAndStopDateIntersectsRange( request );
            } else {
//                query += dateHelper.getStartAndStopDateIsWithinRange( request );
                query += dateHelper.getRecordRangeIsWithinStartAndStopDate( request );
            }
        }
        if ( "".equals(query) ) {
            query = "*:*";    
        }

        solrQuery.setQuery( query );
        QueryResponse queryResponse = solr.query(solrQuery);
        return queryResponse.getResults();
    }
}
