package HPMS;
import org.json.*;
import org.junit.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import HPMS.*;
import static org.junit.Assert.*;
/**
 *  Test client for the HPMS application 
 *  @author Jamal Bourne
    @author Ken Cooper
**/
public class HPMSTest {
    	@Test
	/**
	 * Tests invalid arguments
	 * @throws IOException for missing file
	 * @throws InterruptedException for HTTP interruptions
	**/
	public void invalid_argument_test() throws IOException, InterruptedException{
		assertFalse(HPMS.valid("billing"));
		assertFalse(HPMS.valid("-billing"));
		assertFalse(HPMS.valid("-patient portal"));
		assertFalse(HPMS.valid("patient portal"));
		assertFalse(HPMS.valid("anything"));
		assertFalse(HPMS.valid("chocolate"));
		assertFalse(HPMS.valid("eggs "));
		assertFalse(HPMS.valid("more eggs"));
		assertFalse(HPMS.valid("too-many-eggs"));
		assertFalse(HPMS.valid("testing"));


   	 }
	@Test
	/**
	 * Tests valid arguments
	 * @throws IOException for missing file
	 * @throws InterruptedException for HTTP interruptions
	**/
public void valid_argument_test() throws IOException, InterruptedException{
		assertTrue(HPMS.valid("--billing"));
		assertTrue(HPMS.valid("--patient-portal"));
		}
}