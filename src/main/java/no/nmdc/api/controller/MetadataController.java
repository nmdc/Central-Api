package no.nmdc.api.controller;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import no.nmdc.api.MetadataApi;
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
	
	private JdbcTemplate jdbcTemplate;
	
    @Autowired
    private Configuration configuration;
	
	private static final Logger logger = LoggerFactory.getLogger(MetadataController.class);
	
	public final void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Autowired
	public MetadataController(DataSource dataSource) {
		this.setDataSource(dataSource);
	}
    
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
            @RequestParam(value = "bbox", required = false) String bbox,
            @RequestParam(value = "dateSearchMode", required = false) String dateSearchMode) throws Exception {
                
        SearchParameters req = new SearchParameters( query, offset, beginDate, endDate, bbox, dateSearchMode );
        SearchResults result = metadataApi.search( req );
        return result; 
    }
    
    @RequestMapping("/getMetadataDetails")
    public @ResponseBody SearchResults  getMetadataDetails(@RequestParam("doi") String doi) throws Exception {

        return metadataApi.getMetadataDetail(doi);
    }
    
    @RequestMapping("/getUrl/{entityId}")
    public @ResponseBody Object getUrl(@PathVariable String entityId) {
    	String urlToPage = configuration.getString("url.page");
    	
    	List<String> hashs = getHashFromEntityId(entityId);
    	Map<String, String> urlMap = new HashMap<String, String>();
    	if (hashs.size() > 0) {
    		urlMap.put("url", urlToPage + hashs.get(0));
    		return urlMap;
    	}
    	urlMap.put("url", "not found");
    	return urlMap;
    }
    
    @RequestMapping("/landingpage/{pageName}")
    public ModelAndView getLandingpage(@PathVariable String pageName) throws IOException {
    	
    	String htmlFilePath = "";
    	List<String> filePath = getFileFromHash( pageName );

    	if ( filePath.size() > 0 ) {
    		htmlFilePath = filePath.get(0);
    	}
    	
        ModelAndView mav = new ModelAndView("landingpage");
    	File file = new File(htmlFilePath );
    	String htmlFragment = FileUtils.readFileToString(file, "UTF-8");

    	mav.addObject("page", htmlFragment);
        return mav;
    }
    
	private List<String> getFileFromHash(String hash) {
		
		return jdbcTemplate.query("select filename_html from nmdc_v1.dataset where hash = ?", 
				new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("filename_html");
			}
		}, hash);
	}
	
	private List<String> getHashFromEntityId(String entityId) {
		
		return jdbcTemplate.query("select hash from nmdc_v1.dataset where identifier = ?", 
				new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("hash");
			}
		}, entityId);
	}
}
