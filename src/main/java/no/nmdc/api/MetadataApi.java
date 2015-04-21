package no.nmdc.api;

import no.nmdc.api.domain.Facets;

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
    public String search( String criteria );
    public String getMetadataDetail( String doi );
}
