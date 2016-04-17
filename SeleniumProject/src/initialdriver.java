import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;



public class initialdriver {
	 
	 public static WebDriver driver;
	
	 
	 public void testSample() throws Exception {
		 
		 
		
		  driver = new FirefoxDriver(); //Will launch firefox browser
		  driver.get("http://www.salesforce.com");
		  driver.manage().window().maximize(); //Maximize the window
		  driver.findElement(By.id("button-login")).click();
		  
		  Thread.sleep(3000);  
		  String UN = "inihih";
		  String pwd = "password";
		  /* Enter username*/
		  ProjectMethods.enterText(".//*[@id='username']", UN, "UserName");
	 
		  /*Enter password */
		  ProjectMethods.enterText(".//*[@id='password']", pwd, "Password");
		  ProjectMethods.clickbutton(".//*[@id='Login']", "Login to Salesforce");
		  
		  driver.quit();
		  }
}

