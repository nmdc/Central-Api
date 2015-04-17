package no.nmdc.solr.pojo;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import org.junit.Test;

public class MarshallFieldTest {
    
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
}
