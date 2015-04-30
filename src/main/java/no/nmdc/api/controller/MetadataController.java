package no.nmdc.api.controller;

import no.nmdc.api.MetadataApi;
import no.nmdc.api.facets.domain.Facets;
import no.nmdc.api.search.domain.SearchResults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MetadataController {
    
    @Autowired
    private MetadataApi impl;
    
    public void setMetadataApi( MetadataApi api ) {
        this.impl = api;
    }
    
    @RequestMapping("/getFacets")
    public @ResponseBody Facets getFacets() throws Exception {
        
        return impl.getFacets(); 
    }
    
    @RequestMapping("/search")
    public @ResponseBody SearchResults  search(@RequestParam("q") String query, @RequestParam(value = "offset", required = false) Integer offset) throws Exception {

        Integer start = null;
        if ( offset != null && !offset.equals("") ) {
            start = new Integer(offset);
        }
        SearchResults result = impl.search(query, start);
        return result; 
    }
}
