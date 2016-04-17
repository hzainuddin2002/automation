
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Day1selenium {
	public WebDriver driver;
	
	
	
	


 @Test
  public void testSample() throws Exception {
	  driver = new FirefoxDriver(); //Will launch firefox browser
	  driver.get("http://www.salesforce.com");
	  driver.manage().window().maximize(); //Maximize the window
	  driver.findElement(By.id("button-login")).click();
	  
	  Thread.sleep(3000);
	  driver.findElement(By.id("username")).clear();
	  driver.findElement(By.id("username")).sendKeys("huzaifa@me.com"); // Inputs user name and clicks
//	  System.out.println(driver.findElement(By.id("username")).getAttribute("value"));
//	  System.out.println(driver.findElement(By.id("username")).getAttribute("onkeyup"));
	  driver.findElement(By.id("password")).clear();
	  driver.findElement(By.id("password")).clear(); //Inputs no password
	  
	  //<Tag name>[@<attribute name>}
	  //.//*[@id='error']
	  //div[@class='loginError'] --> xpath
	  
	  driver.findElement(By.id("Login")).click(); //Click on password
	 
	  String expectedErrorMsg = "Please enter your Password.";
	  String actualErrorMsg = driver.findElement(By.xpath(".//*[@id='error']")).getText();
	  	  
	  if(expectedErrorMsg.equals(actualErrorMsg)){
		  System.out.println("Pass: Expected == Actual");
		  }else{
			  System.out.println("Fail: Expected " +expectedErrorMsg+ "did not equal actual "+actualErrorMsg);
		  }
	  driver.quit();
  
	  driver = new FirefoxDriver(); //Will launch firefox browser
	  driver.get("http://www.salesforce.com");
	  driver.manage().window().maximize(); //Maximize the window
	  driver.findElement(By.id("button-login")).click();
	  
	  Thread.sleep(3000);
	  driver.findElement(By.id("username")).sendKeys(“email@me.com”); // Inputs user name and clicks
	  driver.findElement(By.id("password")).sendKeys("passw!"); //Inputs no password
	  driver.findElement(By.id("Login")).click();
	  driver.findElement(By.xpath("//div[@id='owner-choice']/div")).click();
	  driver.findElement(By.xpath("//div[@id='walkthrough-callout-close']/img")).click();
	  
	  //<Tag name>[@<attribute name>}
	  //.//*[@id='error']
	  //div[@class='loginError']	  
	  driver.findElement(By.id("Login")).click(); //Click on password
  }
}
