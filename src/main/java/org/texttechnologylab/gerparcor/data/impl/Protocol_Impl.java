package org.texttechnologylab.gerparcor.data.impl;

import org.json.JSONException;
import org.json.JSONObject;
import org.texttechnologylab.gerparcor.data.Factory;
import org.texttechnologylab.gerparcor.data.Format;
import org.texttechnologylab.gerparcor.data.Protocol;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Protocol_Impl implements Protocol {

    private JSONObject pObject = null;

    private Factory pFactory = null;

    public Protocol_Impl(Factory pFactory, JSONObject pObject){
        this.pObject = pObject;
        this.pFactory = pFactory;
    }

    public Protocol_Impl(JSONObject pObject){
        this.pObject = pObject;
    }

    @Override
    public String getID() {
        return getValue("id");
    }

    @Override
    public Date getDate() {
        Date rDate = null;
        rDate = new Date(getValueLong("timestamp"));
        return rDate;
    }

    @Override
    public String getParliament() {
        return getValue("parliament");
    }

    @Override
    public String getDevision() {
        return getValue("devision");
    }

    @Override
    public String getCountry() {
        return getValue("country");
    }

    @Override
    public boolean isHistorical() {
        return getValueBoolean("country");
    }

    @Override
    public String getContent() {
        return getValue("content");
    }

    @Override
    public File download() {

        File rFile = null;

        return rFile;

    }

    @Override
    public File download(Format pFormat) {
        File rFile = null;

        return rFile;
    }


    @Override
    public int compareTo(Protocol protocol) {
        return this.getID().compareTo(protocol.getID());
    }

    private String getValue(String sKey){

        String rValue = "";
        try {
            rValue = pObject.getString(sKey);
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
        return rValue;

    }

    private long getValueLong(String sKey){

        long rValue = -1l;
        try {
            rValue = pObject.getLong(sKey);
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
        return rValue;

    }

    private int getValueInt(String sKey){

        int rValue = -1;
        try {
            rValue = pObject.getInt(sKey);
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
        return rValue;

    }

    private boolean getValueBoolean(String sKey){

        boolean rValue = false;
        try {
            rValue = pObject.getBoolean(sKey);
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
        return rValue;

    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return getID()+"\t"+sdf.format(getDate())+"\t"+getParliament()+"\t"+getDevision()+"\t"+getCountry();
    }
}
