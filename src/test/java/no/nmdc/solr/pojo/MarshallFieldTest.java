package no.nmdc.solr.pojo;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import no.nmdc.api.MetadataApi;
import no.nmdc.api.MetadataApiImpl;
import no.nmdc.api.domain.facets.Facets;
import no.nmdc.solr.domain.Field;
import no.nmdc.solr.request.FacetRequest;
import no.nmdc.solr.request.FacetWhitelist;
import no.nmdc.solr.request.QueryRequest;
import no.nmdc.solr.request.NmdcSolrServer;

import org.apache.solr.client.solrj.request.LukeRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


public class MarshallFieldTest {
    
    private MetadataApiImpl impl = new MetadataApiImpl();
    
    private NmdcSolrServer solr = new NmdcSolrServer();
    QueryRequest query = new QueryRequest();
    FacetRequest luke = new FacetRequest();
    
    @Before
    public void setUp() {
        solr.setSolrUrl("http://dev1.nmdc.no:8983/solr/nmdc");
        query.setSolr(solr);
        luke.setSolr(solr);
        luke.setFacetWhitelist( new FacetWhitelist( Arrays.asList("Provider")) );
        impl.setQueryRequest(query);
        impl.setFacetRequest(luke);
    }
    
    @Test
    public void marshallFieldTest() throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Field.class);
    
        Field field = new Field();
        
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        
        marshaller.marshal(((Object)field), transformAString());
        System.out.println(field.getName());
    }
    
    private StringWriter transformAString() throws Exception {
        StringReader reader = new StringReader("<response><lst name=\"index\"></lst></response>");
        StringWriter writer = new StringWriter();
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();

        transformer.transform(
                new javax.xml.transform.stream.StreamSource(reader), 
                new javax.xml.transform.stream.StreamResult(writer));

        return writer;
    }
    
    @Test
    public void marshallFacetsTest() throws Exception {
        JAXBContext jc = JAXBContext.newInstance(Facets.class);
        
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        StringWriter writer = new StringWriter();
        marshaller.marshal(((Object)impl.getFacets()), writer);
        
        System.out.println(writer.getBuffer().toString());
    }
}
