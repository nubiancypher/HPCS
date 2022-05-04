import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import HPMS.HPMSTest;

/** Runs the HPMS.Billing test client
*
**/

public class HPMSTestRunner {
	   
	   /** Runs the test
	   *   @param args The array of arguments for the program
	   **/
	   public static void main(String[] args) {
			Result result = JUnitCore.runClasses(HPMSTest.class);
			     
			if (result.getFailures().size() > 0){
				System.err.println("A unit test failed:");
				for (Failure failure : result.getFailures()) {
					System.err.println("\t" + failure.toString());
				}
							             
				System.exit(1);
								           }
			             
			    System.out.println("All tests passed");
				System.exit(0);
					      }
} 
