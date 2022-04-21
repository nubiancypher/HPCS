import org.json.JSONObject;

//represents a customer of happy pets inc
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
	
	//what data should people be able to get?
	public String toString() {
		return ("Name: "+ firstName+" "+lastName+"\n"+
				"Email: "+email+"\n"+
				"Location: "+city+" "+state+", "+country+"\n"+
				"Current credit balance: "+creditBalance);
	}
	
	
}
