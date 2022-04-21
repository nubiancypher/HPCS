import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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

	public static void runBilling() throws IOException, InterruptedException  {
		Billing component = new Billing();
	}
	
	public static void runBilling(int id) throws JSONException, IOException, InterruptedException {
		Billing billing = new Billing(id);
	}
	
	public static void runPP() {
		System.out.println("This is the method that will implement the patient portal component, whatever that means.");
		//use patient-portal.jar to access online portal
	}
	
	public static void runPP(int id) {
		System.out.println("run PP with id "+id);
	}
	
	/**
	 * Makes sure that argument is valid 
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
