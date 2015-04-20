package no.nmdc.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import no.nmdc.solr.pojo.FacetName;
import no.nmdc.solr.pojo.FacetValue;
import no.nmdc.solr.pojo.Facets;
import no.nmdc.solr.pojo.Field;
import no.nmdc.solr.pojo.FieldInfoComparator;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.LukeRequest;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.LukeResponse;
import org.apache.solr.client.solrj.response.LukeResponse.FieldInfo;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.stereotype.Component;

@Component
public class MetadataApiImpl implements MetadataApi {
    
    private String serverUrl = "http://dev1.nmdc.no:8983/solr/nmdc1/";
    
    private String facetedSearch = "q=*:*&rows=0&facet=true&facet.field=Parameter";
    
    public String getFieldFaceting() throws Exception {
        Facets facets = getFacets();
        JAXBContext jc = JAXBContext.newInstance(Field.class);
        
        Field field = new Field();
        
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        
//        marshaller.marshal(((Object)field), transformAString());
        return "";
    }
    
    public Facets getFacets() throws Exception {
        
        Facets facets = getFields();
        for ( FacetName f : facets.getFacets() ) {
            SolrClient solrClient = new HttpSolrClient(serverUrl);
    
            SolrQuery solrQuery = new SolrQuery();
            solrQuery.setQuery("*:*");
            solrQuery.setFacet(true);
            solrQuery.setRows(new Integer(0));
            solrQuery.setFacetMinCount(1);
            solrQuery.addFacetField(f.getName());
 
            QueryResponse queryResponse = solrClient.query(solrQuery);
            List<FacetField> fields = queryResponse.getFacetFields();
            FacetField theField = fields.get(0);
            
            System.out.println("name:"+f.getName()+" matches:"+theField.getValueCount());
            f.setMatches( "" + theField.getValueCount() );
            for ( Count c : theField.getValues() ) {
                FacetValue facetChild = new FacetValue();
                facetChild.setValue(c.getName());
                facetChild.setMatches(""+c.getCount());
                f.addChild(facetChild);
                System.out.println("\t" + c.getName());
                System.out.println("\t" + c.getCount());
            }

        }
        return facets;
    }
    
    protected Facets getFields() throws Exception {
        SolrClient solrClient = new HttpSolrClient(serverUrl);

        LukeRequest lukeRequest = new LukeRequest();
        lukeRequest.setNumTerms(1);
        LukeResponse lukeResponse = lukeRequest.process(solrClient);

        List<FieldInfo> sorted = new ArrayList<FieldInfo>(lukeResponse.getFieldInfo().values());
        Collections.sort(sorted, new FieldInfoComparator());
        Facets facets = new Facets();
        for (FieldInfo infoEntry : sorted) {
            FacetName facet = new FacetName();
            facet.setName(infoEntry.getName());
            facets.addFacet(facet);
            System.out.println("name: " + infoEntry.getName());
            System.out.println("docs: " + infoEntry.getDocs());
        }
        return facets;
        
    }
    public String search( String criteria ) {return "";}
    public String getMetadataDetail( String doi ) {return "";}
}
