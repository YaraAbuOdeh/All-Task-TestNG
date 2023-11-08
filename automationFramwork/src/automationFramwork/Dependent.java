package automationFramwork;

import org.testng.annotations.Test;

public class Dependent {
	
	@Test(dependsOnMethods= {"OpenBrawser"})
	public void SignIn() {
		System.out.println("\"this well execute second SignIn ");
	}
	
	@Test 
	public void OpenBrawser() {
		System.out.println("this well execute first OpenBrawser ");
	}
	
	@Test (dependsOnMethods = {"SignIn"})
	public void logout() {
		System.out.println("this well execute thired logout");
	}
}
