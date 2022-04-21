import java.io.IOException;
import java.util.Scanner;

public class HPMS {
	
	public static void main(String args[]) throws IOException, InterruptedException {
		//verify that arguments begin with double hyphen
		if(args.length == 0) {
			runHPMS();
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

	public static void runBilling() throws IOException, InterruptedException {
		Billing component = new Billing();
	}
	
	public static void runPP() {
		System.out.println("This is the method that will implement the patient portal component, whatever that means.");
		//use patient-portal.jar to access online portal
	}
	
	/**
	 * Makes sure that argument is valid 
	**/
	public static boolean valid(String argument) {
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
				//invalid command exception
				return false;
			}
		}
		else {
			return false;
		}
	}
}
