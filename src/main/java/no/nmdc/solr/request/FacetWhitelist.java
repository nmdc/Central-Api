package no.nmdc.solr.request;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:/no/nmdc/api/facets_whitelist.properties")
public class FacetWhitelist {
    
    public List<String> facetWhitelist;
    
    @Value("#{'${no.nmdc.facets}'.split(',')}")
    public void setFacetWhitelist(List<String> whitelist ) {
        this.facetWhitelist = whitelist;
    }
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
       return new PropertySourcesPlaceholderConfigurer();
    }
    
    public boolean isInWhitelist(String facetName) { 
        for(String str: facetWhitelist) {
            if(str.trim().contains(facetName))
               return true;
        }
        return false;
    }
}
