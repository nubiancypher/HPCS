import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
/**
 * PortalTest Runner
 * @author Ken Cooper and Jamal Bourne
 * This class is the test harness for automating the testing of 
 * all the methods within the Portal Component 
 */


public class PortalTestRunner {
    /**
     * 
     * @param args
     * the main conducts is used to execute all of the tests for the portal component 
     */
    public static void main(String[]args){
        Result result = JUnitCore.runClasses(PortalTest.class);

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
