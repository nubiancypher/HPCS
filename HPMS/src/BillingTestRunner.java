import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
/**
 * Billing TestRunner
 * @author Ken Cooper and Jamal Bourne
 * This test automates the process of testing all the methods
 * within teh Billing component 
 * 
 */

public class BillingTestRunner {
    /**
     * 
     * @param args
     * the main method is used to execute all of the tests for the Billing component
     */
    
    public static void main(String[]args){
        
        Result result = JUnitCore.runClasses(BillingTest.class);

        if (result.getFailures().size()>0){
            System.err.println("A unit test has failed");
            for(Failure failure:result.getFailures()){
                System.err.println("\t"+ failure.toString());

            }
            System.exit(1);


        }
        System.out.println("all tests passed");
        System.exit(0);


    }

}
