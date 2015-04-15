package no.nmdc.web.config;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.configuration.reloading.ReloadingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
//@ComponentScan(basePackages = {"no.imr.sea2data.web.config"})
@ComponentScan(basePackages = {"no"})
@EnableAspectJAutoProxy
public class ApplicationContext {
    /**
     * Configuration object for communicating with property data.
     *
     * @return Configuration object containg properties.
     * @throws ConfigurationException Error during instansiation.
     */
//    @Bean
//    public org.apache.commons.configuration.Configuration configuration() throws ConfigurationException {
//        org.apache.commons.configuration.PropertiesConfiguration configuration = new org.apache.commons.configuration.PropertiesConfiguration(System.getProperty("catalina.base") + "/conf/s2dweb.properties");
//        ReloadingStrategy reloadingStrategy = new FileChangedReloadingStrategy();
//        configuration.setReloadingStrategy(reloadingStrategy);
//        return configuration;
//    }
}
