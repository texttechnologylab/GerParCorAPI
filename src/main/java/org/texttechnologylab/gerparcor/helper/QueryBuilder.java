package org.texttechnologylab.gerparcor.helper;

import org.json.JSONException;
import org.texttechnologylab.gerparcor.data.Format;
import org.texttechnologylab.gerparcor.data.Protocol;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import static org.texttechnologylab.gerparcor.data.Filter.*;

/**
 *
 */
public class QueryBuilder {

    private RESTHandler restHandler = null;


    /**
     * Default Constructor
     */
    public QueryBuilder(){
        restHandler = new RESTHandler();
    }

    /**
     * Return the RESTHandler for specific Operations
     * @return
     */
    public RESTHandler getRESTHandler(){
        return this.restHandler;
    }

    /**
     * Filter for specific ID's
     * @param sID
     * @return
     */
    public QueryBuilder withID(String sID){
        this.restHandler.addFilter(ID, sID);
        return this;
    }

    /**
     * Filter for specific ID's
     * @param sID
     * @return
     */
    public QueryBuilder withID(Set<String> sID){

        for (String s : sID) {
            this.restHandler.addFilter(ID, s);
        }

        return this;
    }

    /**
     * Filter for Parliaments
     * @param sValue
     * @return
     */
    public QueryBuilder withParliament(String sValue){
        this.restHandler.addFilter(PARLIAMENT, sValue);
        return this;
    }

    /**
     * Filter for Devisions
     * @param sValue
     * @return
     */
    public QueryBuilder withDevision(String sValue){
        this.restHandler.addFilter(DEVISION, sValue);
        return this;
    }

    /**
     * Filter for historical Parliaments
     * @param bValue
     * @return
     */
    public QueryBuilder withHistory(boolean bValue){
        this.restHandler.addFilter(DEVISION, bValue);
        return this;
    }

    /**
     * Filter for Countries
     * @param sValue
     * @return
     */
    public QueryBuilder withCountry(String sValue){
        this.restHandler.addFilter(COUNTRY, sValue);
        return this;
    }

    /**
     * Filter for a start-Date
     * @param pDate
     * @return
     */
    public QueryBuilder withDateStart(Date pDate){
        this.restHandler.addFilter(FROMDATE, pDate.getTime());

        return this;
    }

    /**
     * Filter for a end-Date
     * @param pDate
     * @return
     */
    public QueryBuilder withDateEnd(Date pDate){
        this.restHandler.addFilter(TODATE, pDate.getTime());
        return this;
    }

    /**
     * Filter for one concrete Date
     * @param pDate
     * @return
     */
    public QueryBuilder withDate(Date pDate){
        this.restHandler.addFilter(DATE, pDate.getTime());
        return this;
    }

    /**
     * Filter for the Download
     * @param pFormat
     * @return
     */
    public QueryBuilder withFormat(Format pFormat){
        this.restHandler.addFilter(FORMAT, pFormat);
        return this;
    }

    /**
     * Building Method for executing the Query
     * @return
     * @throws JSONException
     */
    public Set<Protocol> build() throws JSONException {

        Set<Protocol> rSet = new HashSet<>(0);

            rSet = this.restHandler.generateQuery();

            this.restHandler.clear();

        return rSet;
    }

}
