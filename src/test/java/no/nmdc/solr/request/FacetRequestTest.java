package no.nmdc.solr.request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.LukeRequest;
import org.apache.solr.client.solrj.response.LukeResponse;
import org.apache.solr.client.solrj.response.LukeResponse.FieldInfo;
import org.junit.Test;

public class FacetRequestTest {
    @Test
    public void lukeHandlerTest() throws SolrServerException, IOException {
        SolrClient solrClient = new HttpSolrClient("http://dev1.nmdc.no:8983/solr/nmdc/");

        LukeRequest lukeRequest = new LukeRequest();
        lukeRequest.setNumTerms(1);
        LukeResponse lukeResponse = lukeRequest.process(solrClient);

        List<FieldInfo> fields = new ArrayList<FieldInfo>(lukeResponse.getFieldInfo().values());
        for (FieldInfo infoEntry : fields) {
            System.out.println("name: " + infoEntry.getName());
            System.out.println("docs: " + infoEntry.getDocs());
        }
    }
}
