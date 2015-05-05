package no.nmdc.api.controller;

import no.nmdc.api.MetadataApi;
import no.nmdc.api.facets.domain.Facets;
import no.nmdc.api.search.domain.SearchParameters;
import no.nmdc.api.search.domain.SearchResults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Accepts requests to the Metadata API REST interface
 * 
 * @author endrem
 *
 */
@Controller
public class MetadataController {
    
    @Autowired
    private MetadataApi metadataApi;
    
    @RequestMapping("/getFacets")
    public @ResponseBody Facets getFacets() throws Exception {
        
        return metadataApi.getFacets(); 
    }
    
    @RequestMapping("/search")
    public @ResponseBody SearchResults  search(@RequestParam("q") String query, 
            @RequestParam(value = "offset", required = false) Integer offset,
            @RequestParam(value = "beginDate", required = false) String beginDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "bbox", required = false) String bbox) throws Exception {

        System.out.println("query:"+query);
        System.out.println("beginDate:"+beginDate);
        System.out.println("endDate:"+endDate);
        SearchParameters req = new SearchParameters( query, offset, beginDate, endDate, bbox );
        SearchResults result = metadataApi.search( req );
        return result; 
    }
}
