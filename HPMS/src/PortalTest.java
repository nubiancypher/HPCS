import org.json.JSONObject;
import org.junit.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;

public class PortalTest {
    int dummyId;

    //create new instance of Portal
    public Portal getPortal () throws IOException, InterruptedException {
        int id=0;
        return new Portal(dummyId);

    }
    @Test
    public void getJSON_is_sucess() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://happypets-2140724455.us-east-1.elb.amazonaws.com/api/patients"))
                .build();
        String JsonReturner = getPortal().getJSON();
        AssertEquals()
    }

    @Test
    public void getCustomerID_test_is_success() throws IOException, InterruptedException {
        dummyId=4051;
        Portal portal= this.getPortal();
        String portal= getPortal().getCustomerID(String responseBody);

    }
    @Test
    public void find_test_is_success(){



    }
    @Test
    public void getPortalInfo_is_sucess(){


    }




}
