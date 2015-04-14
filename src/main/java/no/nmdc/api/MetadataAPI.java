package no.nmdc.api;

/**
 * TODO: skriv doc
 * @author endrem
 *
 */
public interface MetadataAPI {
    public String getFacets();
    public String search( String criteria );
    public String getMetadataDetail( String doi );
}
