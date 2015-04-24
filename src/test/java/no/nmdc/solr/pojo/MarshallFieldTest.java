package no.nmdc.solr.pojo;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import no.nmdc.api.MetadataApi;
import no.nmdc.api.MetadataApiImpl;
import no.nmdc.api.domain.facets.Facets;
import no.nmdc.solr.domain.Field;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class MarshallFieldTest {
    
    @Autowired
    private MetadataApiImpl impl;
    
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
        
//        System.out.println( "" + impl.facetWhitelist );
        
        System.out.println(writer.getBuffer().toString());
    }
}
