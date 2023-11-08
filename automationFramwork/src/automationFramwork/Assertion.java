package automationFramwork;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
public class Assertion {
	@Test

	public void softAssert(){

		 SoftAssert soft;

	System.out.println("softAssert Method Was Started");
	
	soft= new SoftAssert();

	soft.assertTrue(false);

	System.out.println("softAssert Method Was Executed");

	}
     
	@Test
	public void hardAssert() {

	System.out.println("hardAssert Method Was Started");

	org.testng.Assert.assertTrue(false);

	System.out.println("hardAssert Method Was Executed");
	}

}
