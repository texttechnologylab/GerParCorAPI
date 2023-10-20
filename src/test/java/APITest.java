import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.texttechnologylab.annotation.type.QuickAnnoUnit;
import org.texttechnologylab.gerparcor.GerParCorAPI;
import org.texttechnologylab.gerparcor.data.Protocol;
import org.texttechnologylab.gerparcor.helper.QueryBuilder;

import java.io.File;
import java.io.IOException;

public class APITest {

    @Test
    public void testAlpha(){

        GerParCorAPI pAPI = new GerParCorAPI();
        for (String listCountry : pAPI.getFactory().listCountries()) {
            System.out.println(listCountry);
        }
        for (String listCountry : pAPI.getFactory().listParliaments()) {
            System.out.println(listCountry);
        }
        for (String listCountry : pAPI.getFactory().listDevision()) {
            System.out.println(listCountry);
        }

    }

    @Test
    public void testBeta() throws IOException, JSONException {

        GerParCorAPI pAPI = new GerParCorAPI();

        QueryBuilder qBuilder = new QueryBuilder();
        qBuilder.withCountry("Germany");
        qBuilder.withParliament("Bremen");
        for (Protocol protocol : pAPI.getFactory().getProtocol(qBuilder)) {
            File pFile = pAPI.getFactory().download(protocol);
            System.out.println(pFile.getAbsoluteFile());
        }

    }

}
