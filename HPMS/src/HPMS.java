
public class HPMS {
	
	public static void main(String args[]) {
		int customerID = 0;
		switch (args[0]) {
		case "--billing":
			if(args[1] != null) {
				if(args[1]=="--customer-id") {
					runBilling(customerID);
				}
			}
			runBilling();
			break;
		case "--patient-portal":
			if(args[1] != null) {
				if(args[1]=="customer-id") {
					runPP(customerID);
				}
			}
			runPP();
			break;
		default:
			System.out.println("Not a valid option");
			System.exit(1);
		}
		
		
	}

	/**
	 * runs patient portal
	**/
	private static void runPP() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * runs billing portal
	**/
	private static void runBilling() {
		// TODO Auto-generated method stub
		
	}
	
	private static void runBilling(int customerId) {
		// TODO Auto-generated method stub
		
	}
	
	private static void runPP(int customerId) {
		// TODO Auto-generated method stub
		
	}
}
