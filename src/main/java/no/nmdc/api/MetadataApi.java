package no.nmdc.api;

import no.nmdc.api.facets.domain.Facets;
import no.nmdc.api.search.domain.SearchParameters;
import no.nmdc.api.search.domain.SearchResults;

/**
 * Queries Solr and maps from solrj objects to nmdc API objects
 * 
 * @author endrem
 *
 */
public interface MetadataApi {
    
    /**
     * 
     * @return {@literal SearchResults} as JSON 
     * @throws Exception
     */
    public Facets getFacets() throws Exception;
    
    /**
     * 
     * @return {@literal SearchResults} as JSON 
     * @throws Exception
     */
    public SearchResults search( SearchParameters query ) throws Exception;
    
    /**
     * 
     * @return {@literal SearchResults} as JSON 
     * @throws Exception
     */
    public SearchResults getMetadataDetail( String doi ) throws Exception;
}
