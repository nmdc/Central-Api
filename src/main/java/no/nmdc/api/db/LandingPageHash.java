package no.nmdc.api.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class LandingPageHash {
		
	@Autowired(required=false)
	private JdbcTemplate jdbcTemplate;
	
    @Autowired(required=false)
    private Configuration configuration;
    
    public List<String> getFileFromHash(String hash) {
		
		return jdbcTemplate.query("select filename_html from nmdc_v1.dataset where hash = ?", 
				new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("filename_html");
			}
		}, hash);
	}
	
	public List<String> getHashFromEntityId(String entityId) {
		
		return jdbcTemplate.query("select hash from nmdc_v1.dataset where identifier = ?", 
				new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("hash");
			}
		}, entityId);
	}
	
	public Map<String, String> getUrlAsMap(String entityId) {
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
}
