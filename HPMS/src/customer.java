package HPMS.Billing;
/**
 * Represents a customer of Happy Pets
 * @author Jamal Bourne
 * @author Kenneth Cooper
**/
import org.json.JSONObject;
public class customer {
	private int id;
	public String firstName;
	public String lastName;
	public String email;
	private String token;
	private String country;
	private String city;
	private String state;
	private int userType;
	private int userStat;
	private String createdAt;
	private String updatedAt;
	private int creditBalance;
	
	/**
	 *  Constructor for customer
	 *  @param person The JSON object that contains the data needed
	**/
	public customer(JSONObject person) {
		id = person.getInt("customerId");
		firstName = person.getString("firstName");
		lastName = person.getString("lastName");
		email = person.getString("email");
		token = person.getString("token");
		country = person.getString("country");
		city = person.getString("city");
		state = person.getString("state");
		userType = person.getInt("userType");
		userStat = person.getInt("userStatus");
		createdAt = person.getString("createdAt");
		updatedAt = person.getString("updatedAt");
		creditBalance = person.getInt("creditBalance");
	}
	
	
	/**
	 * To string method
	 * @return string representation of a customer
	**/
	public String toString() {
		return ("Name: "+ firstName+" "+lastName+"\n"+
				"Email: "+email+"\n"+
				"Location: "+city+" "+state+", "+country+"\n"+
				"Current credit balance: "+creditBalance);
	}
	
	
}
