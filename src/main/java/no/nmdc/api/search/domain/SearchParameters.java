package no.nmdc.api.search.domain;


/**
 * Object with fields made searchable to the solr index.
 * SearchParameters is null safe
 * 
 * @author endrem
 *
 */
public class SearchParameters {

    private final Integer DEFAULT_OFFSET = 0; 
    
    private String query = "";
    private Integer offset = DEFAULT_OFFSET;
    private String beginDate = "";
    private String endDate = "";
    private String bbox = "";
    
    public SearchParameters() {}
    
    public SearchParameters( String query, Integer offset, String beginDate, String endDate, String bbox) {
        setQuery(query);
        setOffset(offset);
        setBeginDate(beginDate);
        setEndDate(endDate);
        setBbox(bbox);    
    }
    
    public String getQuery() {
        return query;
    }
    public void setQuery(String query) {
        if ( query == null) {
            this.query = "";    
        } else {
            this.query = query;    
        }
    }
    public Integer getOffset() {
        return offset;
    }
    public void setOffset(Integer offset) {
        if ( offset == null) {
            this.offset = DEFAULT_OFFSET;    
        } else {
            this.offset = offset;  
        }        
    }
    public String getBeginDate() {
        return beginDate;
    }
    public void setBeginDate(String beginDate) {
        if ( beginDate == null) {
            this.beginDate = "";    
        } else {
            this.beginDate = beginDate;
        }        
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        if ( endDate == null) {
            this.endDate = "";    
        } else {
            this.endDate = endDate;
        }           
    }
    public String getBbox() {
        return bbox;
    }
    public void setBbox(String bbox) {
        if ( bbox == null) {
            this.bbox = "";    
        } else {
            this.bbox = bbox;
        }          
    }

}
