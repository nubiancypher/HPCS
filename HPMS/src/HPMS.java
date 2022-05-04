package HPMS;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONException;
import HPMS.Billing.Billing;
import HPMS.Portal.Portal;



public class HPMS {
	
	/**
	 *  Main method
	 *  @param args string arguments for the method, must be preceded with "--"
	 *  @throws IOException for wrong file
	 *  @throws InterruptedException for HTTP inturruptions
	**/
	public static void main(String args[]) throws IOException, InterruptedException {
		//verify that arguments begin with double hyphen
		if(args.length == 0) {
			runHPMS();
		} else {
			if(args.length > 1) {
				String argument = args[0];
				String id = args[1];
				if((!valid(argument)) || (!valid(id))) {
					if((!valid(id)) && (valid(argument))){
						System.out.println("Invalid ID");
						System.exit(1);
					}
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
	 * Runs the main HPMS application
	 * @throws IOException for wrong file
	 * @throws InterruptedException for HTTP interruption
	**/
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
	 * Runs the billing component of HPMS
	 * @throws IOException for wrong file
	 * @throws InterruptedException for HTTP interruption
	**/
	public static void runBilling() throws IOException, InterruptedException  {
		Billing billing = new Billing();
		Billing(billing);
	}
	
	/**
	 *  Runs the billing component and returns customer report based on customer id
	 *  @param id the id that is provided to the billing component
	 *  @throws JSONException for if the JSON object cannot be found
	 *  @throws IOException for wrong file
	 *  @throws InterruptedException for HTTP interruption
	**/
	public static void runBilling(int id) throws JSONException, IOException, InterruptedException {
		Billing billing = new Billing();
		Billing(billing, id);

	}
	
	/**
	 * Runs the Patient Portal component of HPMS
	 * @throws IOException for wrong file
	 * @throws InterruptedException for HTTP interruption
	**/
	public static void runPP() throws IOException, InterruptedException{
		Portal portal = new Portal();
		Portal(portal);
	}
	
	/**
	 * Runs patient portal and returns customer report based on customer id
	 * @param id the id that is provided to the patient portal 
	 * @throws IOException for wrong file
	 * @throws InterruptedException for HTTP interruption
	**/
	public static void runPP(int id) throws IOException, InterruptedException {
		Portal portal = new Portal();
		Portal(portal, id);
	}
	
	/**
	 * Makes sure that arguments entered by user are valid 
	 * @param argument is a string argument that is being tested
	 * @throws InterruptedException for HTTP interruption
	 * @throws IOException for wrong file
	 * @return boolean to indicate if an argument is valid or not.
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

	/**
	 *  Billing
	 *  @throws IOException for missing file
	 *  @throws InterruptedException for HTTP interruption
	**/
	public static void Billing(Billing billing) throws IOException, InterruptedException {
		System.out.print("Welcome to the HPMS Billing page please enter your customer ID \n"+
						 "Your ID: ");
		Scanner scan = new Scanner(System.in);
		int possibleID = scan.nextInt();
		if(billing.verify(possibleID)) {
			billing.getBillingInfo(possibleID);
		}else {
			System.out.println("Invalid ID");
			System.exit(1);
		}
	}
	
	/**
	 *  Another constructor for Billing
	 *  @param id The id of a customer of happypets
	 *  @throws IOException for missing file
	 *  @throws InterruptedException for HTTP interruption
	**/
	public static void Billing(Billing billing, int id) throws JSONException, IOException, InterruptedException {
		billing.getBillingInfo(id);
	}
	
	public static void Portal(Portal portal) throws IOException, InterruptedException {
        	System.out.println("Welcome to HMPS Portal, please enter your customer id \nYour ID: ");
        	final Scanner scan = new Scanner(System.in);
        	final int possibleID = scan.nextInt();
        	if (portal.verify(possibleID)) {
            		portal.getPortalInfo(possibleID);
        	}
        	else {
            		System.out.println("Invalid ID");
            		System.exit(1);
        }
    }
    
    public static void Portal(Portal portal, int id) throws IOException, InterruptedException {
        portal.getPortalInfo(id);
    }

	
}
	

