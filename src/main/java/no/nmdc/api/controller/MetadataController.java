package no.nmdc.api.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import no.nmdc.api.MetadataApi;
import no.nmdc.api.db.LandingPageHash;
import no.nmdc.api.facets.domain.Facets;
import no.nmdc.api.search.domain.SearchParameters;
import no.nmdc.api.search.domain.SearchResults;

/**
 * Accepts requests to the Metadata API REST interface
 * 
 * @author endrem
 *
 */
@Controller
public class MetadataController {
	
	private static final Logger logger = LoggerFactory.getLogger(MetadataController.class);
    
    @Autowired
    private MetadataApi metadataApi;
    
    @Autowired
    private LandingPageHash landingpageHash;
    
    @RequestMapping("/getFacets")
    public @ResponseBody Facets getFacets() throws Exception {
        
        return metadataApi.getFacets(); 
    }
    
    @RequestMapping("/search")
    public @ResponseBody SearchResults  search(@RequestParam("q") String query, 
            @RequestParam(value = "offset", required = false) Integer offset,
            @RequestParam(value = "beginDate", required = false) String beginDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            @RequestParam(value = "bbox", required = false) String bbox,
            @RequestParam(value = "dateSearchMode", required = false) String dateSearchMode) throws Exception {
                
        SearchParameters req = new SearchParameters( query, offset, beginDate, endDate, bbox, dateSearchMode );
        SearchResults result = metadataApi.search( req );
        return result; 
    }
    
    @RequestMapping("/getUrl/{entityId}")
    public @ResponseBody Object getUrl(@PathVariable String entityId) {
    	return landingpageHash.getUrlAsMap(entityId);
    }
    
    @RequestMapping( "/landingpage/{pageName}" )
    public ModelAndView getLandingpage(@PathVariable String pageName) throws IOException {
    	
    	String htmlFilePath = "";
    	List<String> filePath = landingpageHash.getFileFromHash( pageName );

    	if ( filePath.size() > 0 ) {
    		htmlFilePath = filePath.get(0);
    	}
        ModelAndView mav = new ModelAndView("landingpage");
    	File file = new File(htmlFilePath );
    	if ( file.exists() ) {
    		String htmlFragment = FileUtils.readFileToString(file, "UTF-8");
    		mav.addObject("page", htmlFragment);
    	} else {
    		mav.addObject("page", "Metadata page not found");
    	}

        return mav;
    }
    
    @RequestMapping( "/landingpageDummy" )
    public ModelAndView landingpageDummy() {
    	return new ModelAndView("landingpageDummy");
    }
}
