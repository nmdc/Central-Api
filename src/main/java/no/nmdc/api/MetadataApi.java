package no.nmdc.api;

import no.nmdc.api.facets.domain.Facets;
import no.nmdc.api.search.domain.SearchResults;

/**
 * TODO: skriv doc
 * @author endrem
 *
 */
public interface MetadataApi {
    
    /**
     * 
     * @return JSON of faceted search
     * @throws Exception
     */
    public Facets getFacets() throws Exception;
    public SearchResults search( String query, Integer offset ) throws Exception;
    public String getMetadataDetail( String doi );
}
