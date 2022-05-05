import org.json.*;
import org.junit.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.io.*;
import HPMS.Portal.Customer_Portal;
import HPMS.Billing.customer;
import HPMS.Portal.*;
import static org.junit.Assert.*;
/**
 * Portal Tests
 * The Portal Tests test all the methods within the Portal Component
 * @Author Ken Cooper and Jamal Bourne
 * 
 * 
 */


public class PortalTest {
    

    public ArrayList OfficialIDs;
    
    /**
     * 
     * 
     * @throws IOException
     * @throws InterruptedException
     *  Test getJSON This method is used
     *   to determine if the correct rest api- service is
     *   accessed
     * @param no parameters 
     * @return nothing 
     */
    @Test
    public void getJSON_is_sucess() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://happypets-2140724455.us-east-1.elb.amazonaws.com/api/patients"))
                .build();
        URI uri = request.uri();
        String val = uri.toString();
        assertTrue(val.equals("http://happypets-2140724455.us-east-1.elb.amazonaws.com/api/patients"));
    }


    /**
     * 
     * 
     * @throws IOException
     * @throws InterruptedException
     *   Test getCustomerID this method
     *   tests if the values generated are the correct
     *   CustomerIDs
     */

    @Test
    public void getCustomerID_test_is_success() throws IOException, InterruptedException {
        // creates portal instance
        Portal tester = new Portal();
        String testResponse=  tester.getJSON();
        //testing if the values in getCustomerID are the correct customer IDs
        ArrayList<Integer>mockIDList= new ArrayList<Integer>();
        mockIDList.add(4051);
        mockIDList.add(4052);
        mockIDList.add(4053);
        assertEquals(tester.getCustomerID(testResponse).get(0),mockIDList.get(0));
        
    }
    
    /**
     * 
     * 
     * @throws IOException
     * @throws InterruptedException
     *   Test find -
     *   This method tests if the find method is able to
     *   identify the desired index of the value
     *   for which it's searching, in this case a
     *   particular customerID
     */

    @Test
    public void find_test_is_success() throws IOException, InterruptedException {
        Portal tester= new Portal();
        ArrayList<JSONObject>mockArray = new ArrayList<JSONObject>();
        JSONObject mock = new JSONObject();
        JSONObject mock2 = new JSONObject();
        JSONObject mock3 = new JSONObject();
        mock.put ("customerId",1234);
        mock2.put("customerId",1111);
        mock3.put("customerId",4321);
        mockArray.add(mock);
        mockArray.add(mock2);
        mockArray.add(mock3);
        assertEquals(tester.find(1111,mockArray),1);
       // assertEquals(tester.find(1234,mockArray),1234);


    }

    
    /**
     * 
     * 
     * @throws IOException
     * @throws InterruptedException
     * Tests getBillingInfo -
    *  This method tests whether or not the method is
    *  sucessfully ableto generate the correct
    *  information given a real customerID
    *                           
     * 
     */
    @Test
    public void getPortalInfo_is_sucess() throws IOException, InterruptedException {
      
        Portal tester = new Portal();
        ArrayList<JSONObject> patients = new ArrayList();
        JSONArray patient_info = new JSONArray(tester.getJSON());
        for (int i = 0; i < patient_info.length(); i++) {
            JSONObject person = patient_info.getJSONObject(i);
            patients.add(person);
        }
        JSONObject user = patients.get(0);
       Portal_Customer testVal = new Portal_Customer(user);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old= System.out;
        System.setOut(ps);
        tester.getPortalInfo(4051);
        System.out.flush();
        System.setOut(old);
        String actualVal =baos.toString();
        String testValRep = testVal.toString().replace("\\s","");
        String actualValRep = actualVal.replace("\\s","");
        ArrayList<Character>compareVal1 = new ArrayList<Character>();
        ArrayList<Character> compareVal2 = new ArrayList<Character>();
        for(int i =0;i<testValRep.length();i++){
            compareVal1.add(testValRep.charAt(i));
            compareVal2.add(actualValRep.charAt(i));
        }
        assertTrue(compareVal1.equals(compareVal2));
      
        

        
        
    }
    
    /**
     * 
     * 
     * @throws IOException
     * @throws InterruptedException
     *   Tests verify- This method tests whether or not
     *   the verification method is capable
     *   of verifying the existence of an id any given
     *   arraylist.
     */

    @Test
    public void verify_is_success() throws IOException, InterruptedException {
        Portal tester = new Portal();
        this.OfficialIDs=Portal.getCustomerID(Portal.getJSON());
        int nonOfficialID = 1111;
        boolean verifyTester= tester.verify(nonOfficialID);
        assertEquals(verifyTester,false);




    }

}

