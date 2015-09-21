package no.nmdc.solr.request;

import no.nmdc.api.search.domain.SearchParameters;

import org.junit.Test;

public class DateHelperTest {
    
    @Test
    public void createSolrDateQuerySyntaxTest() throws Exception {
        DateHelper d = new DateHelper();
        SearchParameters p = new SearchParameters();
        
        p.setBeginDate( "1901-01-02T20:00:00Z");
        p.setEndDate( "2013-01-02T20:00:00Z" );
        String syntax = d.getStartAndStopDateIntersectsRange(p);
        System.out.println(syntax);
        
        
        p.setEndDate( "" );
        String syntax2 = d.getStartAndStopDateIntersectsRange(p);
        System.out.println( syntax2 );

        p.setBeginDate( "" );
        p.setEndDate( "1936-01-02T20:00:00Z" );
        String syntax3 = d.getStartAndStopDateIntersectsRange(p);
        System.out.println( syntax3 );
    }
  
}
