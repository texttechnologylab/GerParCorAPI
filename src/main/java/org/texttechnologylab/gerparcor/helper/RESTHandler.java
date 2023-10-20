package org.texttechnologylab.gerparcor.helper;

import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.dependency.PARATAXIS;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.texttechnologylab.gerparcor.data.Filter;
import org.texttechnologylab.gerparcor.data.Format;
import org.texttechnologylab.gerparcor.data.Protocol;
import org.texttechnologylab.gerparcor.data.impl.Protocol_Impl;
import org.texttechnologylab.utilities.helper.FileUtils;
import org.texttechnologylab.utilities.helper.RESTUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class for communication with the REST API
 */
public class RESTHandler {


    /**
     * default URI - Anonymized
     *
     */
    private String sApiURL = "http://myuri/rest";

    private Map<String, Object> pFilter = new HashMap<>(0);

    /**
     * Constructor
     */
    public RESTHandler(){

    }

    /**
     * Constructor
     * @param sURI
     */
    public RESTHandler(String sURI){
        this.sApiURL = sURI;
    }

    /**
     * Select Protocols based on given query
     * @return
     * @throws JSONException
     */
    public Set<Protocol> generateQuery() throws JSONException {
        Set<Protocol> rSet = new HashSet<>();

            Map<String, Object> pObject = new HashMap<>();

            for (String s : this.pFilter.keySet()) {
                pObject.put(s.toLowerCase(), pFilter.get(s));
            }

            pObject.put("query", "request");

            JSONObject rObject = RESTUtils.getObjectFromRest(sApiURL, RESTUtils.METHODS.GET, pObject);

            JSONArray rArray = rObject.getJSONArray("result");

            for(int a=0; a<rArray.length(); a++){
                rSet.add(new Protocol_Impl(rArray.getJSONObject(a)));
            }

        return rSet;
    }

    /**
     * Add a filter to the query
     * @param pFilter
     * @param pValue
     * @return
     */
    public RESTHandler addFilter(Filter pFilter, Object pValue) {

        JSONArray pArray = new JSONArray();
        if(this.pFilter.containsKey(pFilter.name())){
            Object tObject = this.pFilter.get(pFilter.name());
            if(!(tObject instanceof JSONArray)){
                pArray.put(tObject);
            }
        }
        pArray.put(pValue);

        this.pFilter.put(pFilter.name(), pArray);

        return this;
    }

    /**
     * Download a parliament document
     * @param pValue
     * @param pFormat
     * @return
     * @throws IOException
     */
    public File download(Protocol pValue, Format pFormat) throws IOException {
        File pFile = null;

        pFile = FileUtils.downloadFile(pValue.getID(), sApiURL+"?query=download&id="+pValue.getID()+"&format="+pFormat);

        return pFile;
    }

    /**
     * Helper for simple queries
     * @param pFilter
     * @return
     * @throws JSONException
     */
    public Set<String> simpleQuery(Filter pFilter) throws JSONException {

        Set<String> rSet = new HashSet<>(0);

        Map<String, Object> params = new HashMap<>();

        params.put("query", pFilter.name().toLowerCase());

        JSONObject rObject = RESTUtils.getObjectFromRest(sApiURL, RESTUtils.METHODS.GET, params);
        JSONArray rArray = rObject.getJSONArray("result");

        for(int a=0; a<rArray.length(); a++){
            rSet.add(rArray.getString(a));
        }

        return rSet;
    }

    public void clear(){
        this.pFilter.clear();
    }

}
