package no.nmdc.api;

/**
 * TODO: skriv doc
 * @author endrem
 *
 */
public interface MetadataApi {
    public String getFacets();
    public String search( String criteria );
    public String getMetadataDetail( String doi );
}
