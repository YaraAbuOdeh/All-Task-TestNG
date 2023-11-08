package automationFramwork;

import org.testng.annotations.Test;

public class Priority {
	public class WithPriority{
		@Test (priority=1)
		public void c() {
			System.out.println("method c");
		}
		
		@Test (priority=2)
		public void b() {
			System.out.println("method b");
		}
		
		
	}
	}


