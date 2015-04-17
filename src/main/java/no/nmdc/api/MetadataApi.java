package no.nmdc.api;

/**
 * TODO: skriv doc
 * @author endrem
 *
 */
public interface MetadataApi {
    public String getFieldFaceting() throws Exception;
    public String search( String criteria );
    public String getMetadataDetail( String doi );
}
