package no.nmdc.solr.request;

import no.nmdc.api.search.domain.SearchParameters;

public class DateHelper {
    
    public static final String FIRST_RECORD = "1500-01-02T20:00:00Z";
    
    /**
     * querying for endDate; but no beginDate means from the beginning to stopDate
     * querying for beginDate; but no endDate means from the beginDate  to today
     * @param query
     * @param request
     * @return
     */
    public String getStartAndStopDateIntersectsRange( SearchParameters request ) {
        String dateQuery = "";
        String endDate = request.getEndDate();
        if ( request.getEndDate().equals("") ) {
            endDate = "NOW";
        }
        String beginDate = request.getBeginDate();
            if ( request.getBeginDate().equals("") ) {
                beginDate = FIRST_RECORD;
            }
        dateQuery = "( (Start_Date:[* TO "+ endDate +"] AND Stop_Date:["+beginDate+" TO *])"+
            " OR (Start_Date:[* TO "+ endDate +"] AND !Stop_Date:[* TO *])" +
            " OR (!Start_Date:[* TO *] AND Stop_Date:["+beginDate+" TO *]) )";

        return dateQuery;
    }
    
    /**
     * querying for endDate; but no beginDate means from the beginning to stopDate
     * querying for beginDate; but no endDate means from the beginDate  to today
     * @param query
     * @param request
     * @return
     */
    public String getStartAndStopDateIsWithinRange( SearchParameters request ) {
        String dateQuery = "";
        String endDate = request.getEndDate();
        if ( request.getEndDate().equals("") ) {
            endDate = "NOW";
        }
        String beginDate = request.getBeginDate();
        if ( request.getBeginDate().equals("") ) {
            beginDate = FIRST_RECORD;
        }
        dateQuery = "( (Start_Date:[* TO "+ beginDate +"] AND Stop_Date:["+beginDate+" TO *] AND Start_Date:[* TO " + endDate +"] AND Stop_Date:[" + endDate + " TO *])" +
                " OR (Start_Date:[* TO "+ beginDate +"] AND !Stop_Date:[* TO *] AND Start_Date:[* TO " + endDate +"])"+  
                " OR (!Start_Date:[* TO *] AND Stop_Date:["+beginDate+" TO *] AND Stop_Date[" + endDate + " TO *]) )";  
        
        return dateQuery;
    }
}
