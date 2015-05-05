package no.nmdc.api.search.domain;


/**
 * Object with fields made searchable to the solr index.
 * 
 * @author endrem
 *
 */
public class SearchParameters {

    private String query;
    private Integer offset;
    private String beginDate;
    private String endDate;
    private String bbox;
    
    public SearchParameters() {}
    
    public SearchParameters( String query, Integer offset, String beginDate, String endDate, String bbox) {
        this.query = query;
        this.offset = offset;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.bbox = bbox;    
    }
    
    public String getQuery() {
        return query;
    }
    public void setQuery(String query) {
        this.query = query;
    }
    public Integer getOffset() {
        return offset;
    }
    public void setOffset(Integer offset) {
        this.offset = offset;
    }
    public String getBeginDate() {
        return beginDate;
    }
    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getBbox() {
        return bbox;
    }
    public void setBbox(String bbox) {
        this.bbox = bbox;
    }

}
