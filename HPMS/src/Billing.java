import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;

import org.json.JSONObject; 

public class Billing {
	private customer guy;
	JSONObject file;
	public Billing() throws IOException, InterruptedException {
		//extract json file from http request and represent customer 
		String string = getJSON();
		System.out.println(string);
		//process(file);
		//TODO 
	    /*
	     * Extract JSON objects from string
	     * 		possibly make string into a txt file and process the JSON objects from the text file.
	     * Create customer object
	     * make getters from that object to get attributes
	    */
	}

	
	/**
	 * Extract JSON data from happypets server
	 * @throws InterruptedException 
	 * @throws IOException 
	**/
	private String getJSON() throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://happypets-2140724455.us-east-1.elb.amazonaws.com/api/billing"))
				.build();
		HttpResponse<String> response =
					client.send(request, BodyHandlers.ofString());
		String str = response.body();
		return str;
	}
	/*
		
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

	
	
	
	*/
	
	/*
		  public static HttpRequest Get() {
        HttpURLConnection connection;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://happypets-2140724455.us-east-1.elb.amazonaws.com/api/billing")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())//tell server to recieve response as a string
                .thenApply(HttpResponse::body) // returns response gofuture data type used for aysnchronous programming
                .thenApply(HPMS_Billing::getCustomerID)// once we receive the response we want to apply the ::body method to previous results//:: means it's a lamda , once that is done we use this body to print out, using printline method
                .join();
        return request;
    }

    public static String getCustomerID(String responseBody)
    {

        JSONArray Billing = new JSONArray(responseBody);

        for (int i = 0; i < Billing.length(); i++) {
            JSONObject BillingCustomerId = Billing.getJSONObject(i);
            int id = BillingCustomerId.getInt("customerId");
            System.out.println(id);
        }
        return null;
    }

	
	
	*/
	
	
	
	
	
	
}
