package no.nmdc.api;

import no.nmdc.api.domain.SearchResults;
import no.nmdc.api.domain.facets.Facets;

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
    public SearchResults search( String query, String facets ) throws Exception;
    public String getMetadataDetail( String doi );
}
