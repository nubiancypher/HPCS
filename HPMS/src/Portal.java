import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;


public class Portal {
    public ArrayList<Integer>OfficialIDs;

    public static String getJSON() throws IOException, InterruptedException {
        //creates client
        //defines the return type of the JSON object to be a String
        //returns JSON request as a string
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request= HttpRequest.newBuilder()
                .uri(URI.create("http://happypets-2140724455.us-east-1.elb.amazonaws.com/api/patients"))
                 .build();
        HttpResponse<String>response=
                client.send(request, HttpResponse.BodyHandlers.ofString());
        String str = response.body();
        return str;

    }
    public static ArrayList<Integer>getCustomerID(String responsebody)
    {
        // creates an arraylist of all the customer id's
        //returns that arraylist
        ArrayList<Integer>IDs = new ArrayList<>();
        JSONArray Portal = new JSONArray(responsebody);
        for(int i =0;i<Portal.length();i++)
        {
            JSONObject PortalId = Portal.getJSONObject(i);
            int id = PortalId.getInt("customerId");
            IDs.add(id);
        }
        return IDs;

    }
    private boolean verify(int possilbeID) {//verifies customer ID is a valid ID from user
        //run through the arraylist of cusomter ID's
        for (int i =0;i<OfficialIDs.size();i++) {
            //checks to see if the possilbe id is in the array
            if(possilbeID==OfficialIDs.get(i)) {
                return true;
            }
        }
        //return false if the array entered isn't in the array
        // of all the id's
        return false;

    }

    private int find(int possibleID, ArrayList<JSONObject>patients){//finds the JSONObject,based on customer ID
        int found = 0;// index of the id if within the array
        for(int i =0;i<patients.size(); i++){
            JSONObject guy = patients.get(i);
            int guyID= guy.getInt("customerId");
            if(guyID==possibleID) {
                found =i;
            }

        }
        return found;


    }
    private void getPortalInfo(int possibleID) throws IOException, InterruptedException {
        //getJSON returns a string created from the JSON
        //makes JSONArray from all the JSON objects listed in in the JSON
        JSONArray patient_info = new JSONArray(getJSON());
        ArrayList<JSONObject>patients=new ArrayList();
        //go through the JSON array and add each object to a seperate arraylist
        //that object is all of the different attributes per object
        for(int i =0;i<patient_info.length();i++){
            JSONObject person =patient_info.getJSONObject(i);
            patients.add(person);
        }
        JSONObject user= patients.get(find(possibleID,patients));
        System.out.println(new Customer_Portal(user));
    }


    public Portal() throws IOException, InterruptedException {
        // fills the arraylist of official id's with all the id's extracted
        //from the JSON
        OfficialIDs = getCustomerID(getJSON());
        System.out.println("Welcome to HMPS Portal, please enter your customer id \n Your ID: ");
        Scanner scan  = new Scanner(System.in);
        //reads computer input
        int possibleID =  scan.nextInt();
        //verify that id is in the array of customer id's
        if(verify(possibleID)){
            getPortalInfo(possibleID);
        }else{
            System.out.println("Invalid ID");
            System.exit(1);
        }


    }
    public Portal(int id) throws IOException, InterruptedException {
        getPortalInfo(id);
    }






}






