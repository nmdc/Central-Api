package no.nmdc.api.search.domain;

import java.util.Date;

/**
 * Object with fields made searchable to the solr index.
 * 
 * @author endrem
 *
 */
public class SearchParameters {

    private String query;
    private Integer offset;
    private Date beginDate;
    private Date endDate;
    private String bbox;
    
    public SearchParameters() {}
    
    public SearchParameters( String query, Integer offset, Date beginDate, Date endDate, String bbox) {
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
    public Date getBeginDate() {
        return beginDate;
    }
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public String getBbox() {
        return bbox;
    }
    public void setBbox(String bbox) {
        this.bbox = bbox;
    }

}
