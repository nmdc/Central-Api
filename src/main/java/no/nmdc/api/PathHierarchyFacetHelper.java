package no.nmdc.api;

import java.util.ArrayList;
import java.util.List;

import no.nmdc.api.facets.domain.FacetName;
import no.nmdc.api.facets.domain.FacetValue;
import no.nmdc.api.facets.domain.Facets;

public class PathHierarchyFacetHelper {
    
    protected void createFacetHierarchy( Facets facets ) {
        List<FacetName> names = facets.getFacets();

        for ( FacetName f : names ) {
            if ( f.getName().equals("Parameter") ) {
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
            childValue.setValue( aTokenString.trim() );
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
}
