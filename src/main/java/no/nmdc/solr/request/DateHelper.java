package no.nmdc.solr.request;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateHelper {
    
    public String createSolrDateQuerySyntax(String fromDate, String toDate) throws ParseException {
    
        SimpleDateFormat genericDateFormat =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
        SimpleDateFormat dateFormatUTC =    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        dateFormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));
    
        Date start = genericDateFormat.parse(fromDate);
        
        String range = "";
        if ( toDate.equals("") ) {
            range = "[" + dateFormatUTC.format(start) + " *]";    
        } else { 
            Date end = genericDateFormat.parse(toDate);
            range = "[" + dateFormatUTC.format(start) + " TO " + dateFormatUTC.format(end) + "]";
        }
        return range;
    }
}
