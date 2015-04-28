package no.nmdc.api.controller;

import no.nmdc.api.MetadataApi;
import no.nmdc.api.domain.SearchResults;
import no.nmdc.api.domain.facets.Facets;

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
    public @ResponseBody SearchResults  search(@RequestParam("q") String query) throws Exception {

        SearchResults result = impl.search(query);
        return result; 
    }
}
