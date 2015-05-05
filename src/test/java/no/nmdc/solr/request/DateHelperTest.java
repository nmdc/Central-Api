package no.nmdc.solr.request;

import org.junit.Test;

public class DateHelperTest {
    
    @Test
    public void createSolrDateQuerySyntaxTest() throws Exception {
        DateHelper d = new DateHelper();
        String syntax = d.createSolrDateQuerySyntax("1901-01-03T00:00:00+0400", "2013-01-03T23:59:59+0400");
        
        System.out.println(syntax);
        
        String syntax2 = d.createSolrDateQuerySyntax("1901-01-03T00:00:00+0400", "");
        
        System.out.println( syntax2 );
        
    }
}
