package no.nmdc.api.controller;

import no.nmdc.api.MetadataApiImpl;
import no.nmdc.api.domain.Facets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MetadataController {
    
    @Autowired
    private MetadataApiImpl impl;
    
    @RequestMapping("/getFacets")
    public @ResponseBody Facets getFacets() throws Exception {
        System.out.println("getFacets");
        return impl.getFacets(); 
    }
}
