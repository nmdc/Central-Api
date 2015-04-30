package no.nmdc.solr.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import no.nmdc.api.facets.domain.FacetName;
import no.nmdc.api.facets.domain.Facets;

import org.apache.solr.client.solrj.request.LukeRequest;
import org.apache.solr.client.solrj.response.LukeResponse;
import org.apache.solr.client.solrj.response.LukeResponse.FieldInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FacetRequest {

    private NmdcSolrServer solr;
    
    private FacetWhitelist facetWhitelist;
    
    @Autowired
    public void setSolr(NmdcSolrServer solr) {
        this.solr = solr;
    }

    @Autowired
    public void setFacetWhitelist( FacetWhitelist whitelist ) {
        this.facetWhitelist = whitelist;
    }
    
    public Facets getFacetsFromSolr() throws Exception {
        List<FieldInfo> fieldInfos = getFieldInfo();
        Facets facets = new Facets();
        for (FieldInfo infoEntry : fieldInfos) {
            if ( facetWhitelist.isInWhitelist(infoEntry.getName()) ) {
                FacetName facet = new FacetName();
                facet.setName(infoEntry.getName());
                facets.addFacet(facet);
            }
        }
        return facets;
    }
    
    protected List<FieldInfo> getFieldInfo() throws Exception {

        LukeRequest lukeRequest = new LukeRequest();
        lukeRequest.setNumTerms(1);
        LukeResponse lukeResponse = lukeRequest.process(solr.getSolrClient());

        Map<String, FieldInfo> fieldInfos = lukeResponse.getFieldInfo();
        return new ArrayList<FieldInfo>(fieldInfos.values());
    }
}
