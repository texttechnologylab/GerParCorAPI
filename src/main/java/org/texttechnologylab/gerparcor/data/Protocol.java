package org.texttechnologylab.gerparcor.data;

import java.io.File;
import java.sql.Date;

/**
 * Interface for a plenary Protocol
 */
public interface Protocol extends Comparable<Protocol> {

    /**
     * Return the ID
     * @return
     */
    String getID();

    /**
     * Return the Date
     * @return
     */
    Date getDate();

    /**
     * Return the Parliament
     * @return
     */
    String getParliament();

    /**
     * Return the Devision
     * @return
     */
    String getDevision();

    /**
     * Return the Country
     * @return
     */
    String getCountry();

    /**
     * Return if its is Historic
     * @return
     */
    boolean isHistorical();

    /**
     * Path for saving on disk
     * @return
     */
    String getSavePath();

    /**
     * Return the Content of the protocol
     * @return
     */
    String getContent();

    /**
     * Download the protocol
     * @return
     */
    File download();

    /**
     * Download the protocol in a specific format
     * @param pFormat
     * @return
     */
    File download(Format pFormat);

}
