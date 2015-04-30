package no.nmdc.solr.request;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class FacetWhitelistTest {
    
    @Test
    public void isInWhitelistTest() {
        FacetWhitelist whitelist = new FacetWhitelist( Arrays.asList("foo", "bar"));
        
        assertTrue( whitelist.isInWhitelist("foo") );
        assertFalse( whitelist.isInWhitelist("foobar") );
    }
}
