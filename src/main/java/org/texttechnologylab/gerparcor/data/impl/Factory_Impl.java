package org.texttechnologylab.gerparcor.data.impl;

import org.apache.tools.ant.taskdefs.TempFile;
import org.json.JSONException;
import org.texttechnologylab.gerparcor.data.Factory;
import org.texttechnologylab.gerparcor.data.Filter;
import org.texttechnologylab.gerparcor.data.Format;
import org.texttechnologylab.gerparcor.data.Protocol;
import org.texttechnologylab.gerparcor.helper.QueryBuilder;
import org.texttechnologylab.utilities.helper.TempFileHandler;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of the Factory
 */
public class Factory_Impl implements Factory {

    QueryBuilder pBuilder = null;

    public Factory_Impl(QueryBuilder pBuilder){
        this.pBuilder = pBuilder;
    }

    @Override
    public Protocol getProtocol(String sID) throws JSONException {
        return this.pBuilder.withID(sID).build().stream().findFirst().get();
    }

    @Override
    public Set<Protocol> getProtocol(QueryBuilder pBuilder) throws JSONException {
        return pBuilder.build();
    }

    @Override
    public Set<String> listParliaments() {
        Set<String> rSet = new HashSet<>(0);
        try {
            rSet= this.pBuilder.getRESTHandler().simpleQuery(Filter.PARLIAMENT);
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
        return rSet;
    }

    @Override
    public Set<String> listCountries() {
        Set<String> rSet = new HashSet<>(0);
        try {
            rSet= this.pBuilder.getRESTHandler().simpleQuery(Filter.COUNTRY);
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
        return rSet;
    }

    @Override
    public Set<String> listDevision() {
        Set<String> rSet = new HashSet<>(0);
        try {
            rSet= this.pBuilder.getRESTHandler().simpleQuery(Filter.DEVISION);
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
        return rSet;
    }

    @Override
    public File download(Protocol pProtocol) throws IOException {
        return download(pProtocol, Format.XMI, false);
    }

    @Override
    public File download(Protocol pProtocol, Format pFormat, boolean bOverride) throws IOException {
        File pFile = this.pBuilder.getRESTHandler().download(pProtocol, Format.XMI);
        return pFile;
    }

    @Override
    public void download(QueryBuilder pBuilder, Format pFormat, String sOutput, boolean bOverride) throws IOException, JSONException {
        for (Protocol protocol : pBuilder.build()) {
            String sSavePath = protocol.getSavePath();
            File tempFile = TempFileHandler.getTempFile();
            tempFile = this.pBuilder.getRESTHandler().download(protocol, pFormat);

        }
    }
}
