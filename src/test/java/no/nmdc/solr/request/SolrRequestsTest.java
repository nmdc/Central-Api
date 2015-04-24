package no.nmdc.solr.request;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import no.nmdc.solr.domain.FieldInfoComparator;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.LukeRequest;
import org.apache.solr.client.solrj.response.LukeResponse;
import org.apache.solr.client.solrj.response.LukeResponse.FieldInfo;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

public class SolrRequestsTest {

    @Test
    public void lukeRequest() throws SolrServerException, IOException {
        SolrClient solrClient = new HttpSolrClient("http://dev1.nmdc.no:8983/solr/nmdc/");

        LukeRequest lukeRequest = new LukeRequest();
        lukeRequest.setNumTerms(1);
        LukeResponse lukeResponse = lukeRequest.process(solrClient);

        List<FieldInfo> sorted = new ArrayList<FieldInfo>(lukeResponse.getFieldInfo().values());
        Collections.sort(sorted, new FieldInfoComparator());
        for (FieldInfo infoEntry : sorted) {
            System.out.println("name: " + infoEntry.getName());
            System.out.println("docs: " + infoEntry.getDocs());
        }
    }
    
    @Test
    public void search() throws Exception {
        SolrDocumentList docs = new SolrRequests().search("Havforskningsinstituttet", "Provider");
        
        SolrDocument doc = docs.get(0);
        System.out.println("doc:"+doc);
        String entryId = (String)doc.getFieldValue("Entry_ID");
        assertTrue( entryId.contains("imr") );
        assertNotNull( doc.getFieldValue("Start_Date") );

    }
}
