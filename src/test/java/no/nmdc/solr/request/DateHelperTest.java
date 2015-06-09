package no.nmdc.solr.request;

import org.junit.Test;

public class DateHelperTest {
    
    @Test
    public void createSolrDateQuerySyntaxTest() throws Exception {
        DateHelper d = new DateHelper();
        String syntax = d.createSolrDateQuerySyntax("1901-01-02T20:00:00Z", "2013-01-02T20:00:00Z");
        
        System.out.println(syntax);
        
        String syntax2 = d.createSolrDateQuerySyntax("1901-01-02T20:00:00Z", "");
        
        System.out.println( syntax2 );
        
        String syntax3 = d.createSolrDateQuerySyntax("1901-01-02T20:00:00Z", null);
        
        System.out.println( syntax3 );
        
    }
}
