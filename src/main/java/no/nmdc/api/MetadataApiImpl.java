package no.nmdc.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.nmdc.api.facets.domain.FacetName;
import no.nmdc.api.facets.domain.FacetValue;
import no.nmdc.api.facets.domain.Facets;
import no.nmdc.api.search.domain.SearchParameters;
import no.nmdc.api.search.domain.SearchResults;
import no.nmdc.solr.request.CreateSearchRequest;
import no.nmdc.solr.request.FacetRequest;

import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @see no.nmdc.api.MetadataApi
 * 
 * @author endrem
 *
 */
@Component
public class MetadataApiImpl implements MetadataApi {
    
    private static final Logger logger = LoggerFactory.getLogger(MetadataApiImpl.class);
    
    private CreateSearchRequest queryRequest;
    
    private FacetRequest lukeRequest;
    
    @Autowired
    public void setQueryRequest(CreateSearchRequest queryRequest) {
        this.queryRequest = queryRequest;
    }
    
    @Autowired
    public void setFacetRequest(FacetRequest lukeRequest) {
        this.lukeRequest = lukeRequest;
    }
    
    /** {@inheritDoc} */
    public Facets getFacets() throws Exception {
        Facets facets = lukeRequest.getFacetsFromSolr();
        for ( FacetName f : facets.getFacets() ) {
            FacetField theField = queryRequest.facetedQuery( f );
            
            f.setMatches( "" + theField.getValueCount() );
            for ( Count c : theField.getValues() ) {
                FacetValue facetChild = new FacetValue();
                facetChild.setValue(c.getName());
                facetChild.setMatches(""+c.getCount());
                f.addChild(facetChild);
            }
        }
        
        createFacetHierarchy(facets);
        return facets;
    }
    
    protected void createFacetHierarchy( Facets facets ) {
        List<FacetName> names = facets.getFacets();

        for ( FacetName f : names ) {
            if ( f.getName().equals("parameter") ) {
                List<FacetValue> rootValues = new ArrayList<FacetValue>(f.getChildren().size());
                FacetValue rootNode = new FacetValue();;
                for ( FacetValue paths: f.getChildren() ) {
                    String pathFirstElement = paths.getValue();
                    if ( pathFirstElement.contains(">")) {
                        pathFirstElement = pathFirstElement.substring(0, pathFirstElement.indexOf(">"));
                        if ( !rootNode.getValue().equals( pathFirstElement )) {
                            rootNode = paths;
                            rootValues.add( rootNode );
                        }
                        String childValue = paths.getValue();
                        rootNode.setValue( childValue.substring(0, childValue.indexOf(">")));
                        getNextNode( childValue, rootNode );
                    }
                }
                f.setChildren(rootValues);
                
            }
        }
    }
    
    private void getNextNode( String tokenName, FacetValue leafNode ) {
        
        String nextToken = tokenName.substring( tokenName.indexOf(">") + 1 );
        
        boolean foundFlag = false;
        String aTokenString = "";
        for ( FacetValue aValue : leafNode.getChildFacets() ) {
            if ( aValue.getValue().equals(aTokenString))  {
                foundFlag = true;
                break;
            }
        }        
        
        if ( nextToken.indexOf(">") > 0 ) {
            aTokenString = nextToken.substring(0, nextToken.indexOf(">")).trim();
        } else {
            aTokenString = nextToken;
        }
        if ( nextToken.indexOf(">") > 0 ) {
            FacetValue childNode = createNode(foundFlag, aTokenString, leafNode);
            leafNode = childNode;
            getNextNode(nextToken, leafNode );
        } else {
            createNode(foundFlag, aTokenString, leafNode);
        }
    }
    
    private FacetValue createNode( boolean foundFlag, String aTokenString, FacetValue leafNode) {
        if ( foundFlag == false ) {
            FacetValue childValue = new FacetValue();
            childValue.setValue( aTokenString );
            childValue.setMatches( leafNode.getMatches() );
            boolean foundOnLevel = false;
            if ( leafNode.getChildFacets() != null ) {
                for ( FacetValue existingOnLevel : leafNode.getChildFacets())
                    if ( existingOnLevel.getValue().equals(childValue.getValue()) ) {
                        foundOnLevel = true;
                        childValue = existingOnLevel;
                        break;
                    }
                }
            if ( foundOnLevel == false )
                leafNode.addChildFacet( childValue );
            return childValue;
        } else return leafNode;
    }
    
    /** {@inheritDoc} */
    public SearchResults search( SearchParameters query ) throws Exception {
        SolrDocumentList solrDocs = queryRequest.search( query);
        
        logger.info("query.toString:"+query.toString());
        
        
        SearchResults results = new SearchResults();
        for ( SolrDocument adoc : solrDocs ) {
            Collection<String> names = adoc.getFieldNames();
            Map<String, Object> record = new HashMap<String, Object>();
            for ( String name : names) {
                record.put( name, adoc.getFieldValue( name ).toString() );
            }
            results.addResult( record );
        }
        results.setMatches(solrDocs.size());
        results.setNumFound(solrDocs.getNumFound());
        
        logger.info("results:"+results.getResults());
        return results;
    }
    
    /** {@inheritDoc} */
    public SearchResults getMetadataDetail( String doi ) throws Exception {
        SearchParameters p = new SearchParameters();
        p.setQuery("Entry_ID:\"" + doi + "\"");
        return search(p);
    }
}
