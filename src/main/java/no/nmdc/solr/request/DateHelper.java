package no.nmdc.solr.request;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateHelper {
    
    private final String FIRST_RECORD = "1500-01-02T20:00:00Z";
    
    public String createSolrDateQuerySyntax(String fromDate, String toDate) throws ParseException {

        SimpleDateFormat dateFormatUTC = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        dateFormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date start = null;
        if ( !fromDate.equals("") ) {
            start = dateFormatUTC.parse(fromDate);
        }
        Date end = null;
        if ( !toDate.equals("") ) {
            end = dateFormatUTC.parse(toDate);
        }
        
        String range = "";
        if ( toDate.equals("") && !fromDate.equals("") ) {
            range = "[" + dateFormatUTC.format(start) + " *]";    
        } else if ( fromDate.equals("") && !toDate.equals("") ) {
            range = "[" + FIRST_RECORD + " TO " + dateFormatUTC.format(end) + "]";
        } else if ( !fromDate.equals("") && !toDate.equals("") ){ 
            range = "[" + dateFormatUTC.format(start) + " TO " + dateFormatUTC.format(end) + "]";
        }
        return range;
    }
}
