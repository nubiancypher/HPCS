import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import HPMS.Billing.Billing;
import org.json.JSONException;

public class HPMS {
	
	public static void main(String args[]) throws IOException, InterruptedException {
		//verify that arguments begin with double hyphen
		if(args.length == 0) {
			runHPMS();
		} else {
			if(args.length > 1) {
				String argument = args[0];
				String id = args[1];
				if((!valid(argument)) || (!valid(id))) {
					System.out.println("One or more arguments invalid");
					System.exit(1);
				}
				switch(argument) {
				case "--patient-portal":
					int i = 2;
					String identity = "";
					while(i<id.length()) {
						identity+=id.charAt(i);
						i++;
					}
					runPP(Integer.parseInt(identity));
					break;
				case "--billing":
					int j = 2;
					String identity2 = "";
					while(j<id.length()) {
						identity2+=id.charAt(j);
						j++;
					}
					runBilling(Integer.parseInt(identity2));
					break;
				}
				
			} else {
				String argument = args[0];
				if(!valid(argument)) {
					System.exit(1);
				}
				switch(argument) {
				case "--patient-portal":
					runPP();
					break;
				case "--billing":
					runBilling();
					break;
				}
			}
		}
		
		
	}
	
	
	/**
	 *  Runs the main HPMS application
	**/

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
	

	private static void runHPMS() throws IOException, InterruptedException {
		System.out.print("Welcome to the Happypets Patient Management Service, what would you like to do?\n"+
						   "1. HappyPets Billing\n"+
						   "2. HappyPets Patient Portal\n"+
						   "Your choice: ");
		Scanner scan = new Scanner(System.in);
		int choice = scan.nextInt();
		switch(choice) {
		case 1:
			runBilling();
			break;
		case 2:
			runPP();
			break;
		default:
			System.out.println("Invalid choice");
			runHPMS();
		}
		
	}


	/**
	 *  Runs the billing component of HPMS
	**/
	public static void runBilling() throws IOException, InterruptedException  {
		Billing component = new Billing();
	}
	
	/**
	 *  Runs the billing component and returns customer report based on customer id
	 *  @param id the id that is provided to the billing component
	**/
	public static void runBilling(int id) throws JSONException, IOException, InterruptedException {
		Billing billing = new Billing(id);
	}
	
	
	public static void runPP() {
		System.out.println("This is the method that will implement the patient portal component, whatever that means.");
		//use patient-portal.jar to access online portal
	}
	
	/**
	 * Runs patient portal and returns customer report based on customer id
	 * @param the id that is provided to the patient portal 
	**/
	public static void runPP(int id) {
		System.out.println("run PP with id "+id);
	}
	
	/**
	 * Makes sure that arguments entered by user are valid 
	 * @param argument is a string argument that is being tested
	 * @throws InterruptedException 
	 * @throws IOException 
	**/
	public static boolean valid(String argument) throws IOException, InterruptedException {
		if ((argument.charAt(0)=='-') && (argument.charAt(1) =='-')) {
			//check the rest of the argument
			int i = 2;
			String command = "";
			while(i<argument.length()) {
				command+=argument.charAt(i);
				i++;
			}
			switch(command) {
			case "billing":
				return true;
			case "patient-portal":
				return true;
			default:
				String file = Billing.getJSON();
				ArrayList<Integer> patientIDs = Billing.getCustomerID(file);
				if(patientIDs.contains(Integer.parseInt(command))) {
					return true;
				}
				return false;
			}
		}
		else {
			return false;
		}
	}
}
