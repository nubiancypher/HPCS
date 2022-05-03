package HPMS.Billing;
/**
 *  Billing component of HPMS Application 
 *  
 *  @author Jamal Bourne
 *  @author Ken Cooper
 **/
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject; 

public class Billing { 
	public  ArrayList<Integer> OfficialIDs;
	
	/**
	 *  Constructor for Billing
	**/
	public Billing() throws IOException, InterruptedException {
		OfficialIDs = getCustomerID(getJSON());
		System.out.print("Welcome to the HPMS Billing page please enter your customer ID \n"+
						 "Your ID: ");
		Scanner scan = new Scanner(System.in);
		int possibleID = scan.nextInt();
		if(verify(possibleID)) {
			getBillingInfo(possibleID);
		}else {
			System.out.println("Invalid ID");
			System.exit(1);
		}
	}
	
	/**
	 *  Another constructor for Billing
	 *  @param id the id entered by the user
	**/
	public Billing(int id) throws JSONException, IOException, InterruptedException {
		getBillingInfo(id);
	}
	
	/**
	 *  Prints out customer objects which reveals all customer information
	 *  @param possibleID the ID provided by the user
	**/
	public void getBillingInfo(int possibleID) throws JSONException, IOException, InterruptedException {
		JSONArray patient_info = new JSONArray(getJSON());
		ArrayList<JSONObject> patients = new ArrayList<JSONObject>();
		for(int i=0;i<patient_info.length();i++) {
			JSONObject person = patient_info.getJSONObject(i);
			patients.add(person);
		}
		JSONObject user = patients.get(find(possibleID, patients));
		System.out.println(new customer(user));
		
		
	}
	
	/**
	 *  Returns the index of the JSONObject pertaining to the id provided
	 *  @return index of JSONObject
	 *  @param possibleID the ID provided by the user
	 *  @param patients ArrayList of JSONObjects to be searched.
	**/	
	public int find(int possibleID, ArrayList<JSONObject> patients) {
		int found = 0;
		for(int i=0;i<patients.size();i++) {
			JSONObject guy = patients.get(i);
			int guyID = guy.getInt("customerId");
			if(guyID == possibleID)
				found = i;
		}
		return found;
	}

	/**
	 *  Checks whether a given ID is in the online database
	 *  @returns true if the ID is in the database
	 *  @param possibleID is the ID that is being searched
	**/	
	public boolean verify(int possibleID) {
		for(int i=0;i<OfficialIDs.size();i++) {
			if(possibleID == OfficialIDs.get(i)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Extract JSON data from happypets server
	 * @return String representation of a JSON file
	 * @throws InterruptedException 
	 * @throws IOException 
	**/
	public static String getJSON() throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://happypets-2140724455.us-east-1.elb.amazonaws.com/api/billing"))
				.build();
		HttpResponse<String> response =
					client.send(request, BodyHandlers.ofString());
		String str = response.body();
		return str;
	}
	
	/**
	 *  Returns a list of all of the IDs in the JSON file
	 *  @return A list of all IDs across each JSON object in a file
	 *  @param String representation of a JSON file
	**/
	public static ArrayList<Integer> getCustomerID(String responseBody)
	{
	    ArrayList<Integer> IDs = new ArrayList<Integer>();
		JSONArray Billing = new JSONArray(responseBody);
	    for (int i = 0; i < Billing.length(); i++) {
	        JSONObject BillingCustomerId = Billing.getJSONObject(i);
	        int id = BillingCustomerId.getInt("customerId");
	        IDs.add(id);
	    }
	    return IDs;
	}
}
