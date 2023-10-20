package org.texttechnologylab.gerparcor.data;

import org.json.JSONException;
import org.texttechnologylab.gerparcor.helper.QueryBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * Factory for enabling functions
 */
public interface Factory {

    Protocol getProtocol(String sID) throws JSONException;
    Set<Protocol> getProtocol(QueryBuilder pBuilder) throws JSONException;

    Set<String> listParliaments();
    Set<String> listCountries();
    Set<String> listDevision();

    File download(Protocol pProtocol) throws IOException;
    File download(Protocol pProtocol, Format pFormat, boolean bOverride) throws IOException;

    void download(QueryBuilder pBuilder, Format pFormat, String sOutput, boolean bOverride) throws IOException, JSONException;
}
