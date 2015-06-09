package no.nmdc.solr.request;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateHelper {
    
    public String createSolrDateQuerySyntax(String fromDate, String toDate) throws ParseException {
    
        SimpleDateFormat dateFormatUTC =    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        dateFormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
    
        Date start = dateFormatUTC.parse(fromDate);
        
        String range = "";
        if ( toDate == null || toDate.equals("")  ) {
            range = "[" + dateFormatUTC.format(start) + " *]";    
        } else { 
            Date end = dateFormatUTC.parse(toDate);
            range = "[" + dateFormatUTC.format(start) + " TO " + dateFormatUTC.format(end) + "]";
        }
        
        return range;
    }
}
