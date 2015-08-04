package no.nmdc.api.search.domain;


/**
 * Object with fields made searchable to the solr index.
 * SearchParameters is null safe
 * 
 * @author endrem
 *
 */
public class SearchParameters {

    private final static Integer DEFAULT_OFFSET = 0;
    public final static String DATE_INTERSECTS_RECORD_RANGE = "intersects"; // other option "isWithin"
    public final static String DATE_IS_WITHIN_RECORD_RANGE = "isWithin";
    
    private String query = "";
    private Integer offset = DEFAULT_OFFSET;
    private String beginDate = "";
    private String endDate = "";
    private String bbox = "";
    private String dateSearchMode = DATE_INTERSECTS_RECORD_RANGE;

    public SearchParameters() {}
    
    public SearchParameters( String query, Integer offset, String beginDate, String endDate, String bbox) {
        setQuery(query);
        setOffset(offset);
        setBeginDate(beginDate);
        setEndDate(endDate);
        setBbox(bbox);    
    }
    
    public SearchParameters( String query, Integer offset, String beginDate, String endDate, String bbox, String dateSearchMode) {
        this( query, offset, beginDate, endDate, bbox); 
        setDateSearchMode(dateSearchMode);
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
    public String getDateSearchMode() {
        return dateSearchMode;
    }

    public void setDateSearchMode(String dateSearchMode) {
        if ( dateSearchMode != null ) {
            if (dateSearchMode.equals(DATE_INTERSECTS_RECORD_RANGE))
                this.dateSearchMode = DATE_INTERSECTS_RECORD_RANGE;
            else if (dateSearchMode.equals(DATE_IS_WITHIN_RECORD_RANGE))
                this.dateSearchMode = DATE_IS_WITHIN_RECORD_RANGE;
        }
    }
}
