package org.texttechnologylab.gerparcor;

import org.texttechnologylab.gerparcor.data.Factory;
import org.texttechnologylab.gerparcor.data.impl.Factory_Impl;
import org.texttechnologylab.gerparcor.helper.QueryBuilder;

/**
 * Primary class for entering the API
 */
public class GerParCorAPI {

    private Factory pFactory = null;

    public GerParCorAPI(){
        this.pFactory = new Factory_Impl(new QueryBuilder());
    }

    public Factory getFactory(){
        return this.pFactory;
    }

}
