package no.nmdc.api;

import java.util.ArrayList;
import java.util.List;

import no.nmdc.api.facets.domain.FacetName;
import no.nmdc.api.facets.domain.FacetValue;
import no.nmdc.api.facets.domain.Facets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Creates a hiearchy of FacetValue objects from the "Parameter" facets.
 * @author endrem
 *
 */
public class PathHierarchyFacetHelper {
    
    private static final Logger logger = LoggerFactory.getLogger(MetadataApiImpl.class);
    
    private static final String PARAMETER = "Parameter";
    
    protected void createFacetHierarchy( Facets facets ) {
        List<FacetName> names = facets.getFacets();

        for ( FacetName f : names ) {
            if ( f.getName().equals( PARAMETER ) ) {
                
                List<FacetValue> parameterChildren = f.getChildren();
                
                //assume first element is root node
                FacetValue firstElement = parameterChildren.get(0);
                
                for ( int i=1; i < parameterChildren.size(); i++ ) {
                    FacetValue nextPathObject = parameterChildren.get(i);
                    String nextPathString = nextPathObject.getValue();
                    String nextPathMatches = nextPathObject.getMatches();
                    
                    if ( nextPathString.contains(">")) {
                        getNextNode( nextPathString, nextPathMatches, firstElement );
                    } else {
                        logger.error("Two root elements: 1."+firstElement+ " and 2."+nextPathObject);
                    }
                }
                List<FacetValue> earthScience = new ArrayList<FacetValue>(1);
                earthScience.add(firstElement);
                f.setChildren(earthScience);
            }
        }
    }
    
    private void getNextNode( String tokenName, String matches, FacetValue currentLeaf ) {
        
        String nextToken = tokenName.substring( tokenName.indexOf(">") + 1 );

        String aTokenString = "";
        if ( nextToken.indexOf(">") > 0 ) {
            aTokenString = nextToken.substring(0, nextToken.indexOf(">")).trim();
        } else {
            aTokenString = nextToken;
        }
        
        boolean foundFlag = false;
        if ( currentLeaf != null ) {
            for ( FacetValue aValue : currentLeaf.getChildFacets() ) {
                if ( aValue.getValue().equals(aTokenString))  {
                    foundFlag = true;
                    break;
                }
            }
        }   
        
        FacetValue childNode = null;
        if ( foundFlag ) {
            childNode = createNode(aTokenString, matches, currentLeaf);
        } else {
            childNode = currentLeaf;
        }
        if ( nextToken.indexOf(">") > 0 ) {
            currentLeaf = childNode;
            getNextNode(nextToken, matches, currentLeaf );
        } else {
            createNode(aTokenString, matches, currentLeaf );
        }
    }
    
    private FacetValue createNode( String aTokenString, String matches, FacetValue leafNode) {
        FacetValue childValue = new FacetValue();
        childValue.setValue( aTokenString.trim() );
        childValue.setMatches( matches );
        boolean foundOnLevel = false;
        if ( leafNode.getChildFacets() != null ) {
            for ( FacetValue existingOnLevel : leafNode.getChildFacets()) {
                if ( existingOnLevel.getValue().equals(childValue.getValue()) ) {
                    foundOnLevel = true;
                    childValue = existingOnLevel;
                    break;
                }
            }
        }
        if ( foundOnLevel == false ) {
            leafNode.addChildFacet( childValue );
        }
        return childValue;
    }
}
