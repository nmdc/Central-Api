package no.nmdc.api.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

public class MetadataControllerIT {
     
    @Test
    public void searchIT() throws Exception {
        WebConversation wc = new WebConversation();
        WebResponse response = wc.getResponse("http://localhost:8080/Central-Api/getFacets"); 
        assertEquals(200, response.getResponseCode());
        assertEquals("application/json", response.getContentType());



    }
}
