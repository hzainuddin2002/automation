

import java.io.IOException;

import org.openqa.selenium.firefox.FirefoxDriver;

public class AutomationScripts extends ProjectMethods{
	//Login to SFDC application - 2
		 public static void loginToSFDC() throws InterruptedException, IOException {
			 
			 	ProjectMethods.startReport("Login_To_SalesForce","/Users/huzaifazainuddin/desktop/report");
				ProjectMethods.loginpage();
				// Enter userName
				ProjectMethods.enterText("//*[@id='username']", "huzaifa.aamir@tijaaratraabehah.com", "UserName");
				// Enter Password
				ProjectMethods.enterText("//*[@id='password']", "Hz7863354!", "Password");
				//Click Login Button
				ProjectMethods.clickbutton("//*[@id='Login']", "Login to SFDC");
				Thread.sleep(40000);
				//Click on Business Owner
				ProjectMethods.clickbutton("//*[@id='owner-choice']/div", "Business Owner");
				Thread.sleep(4000);
				// Close window
				ProjectMethods.clickbutton("//*[@id='walkthrough-callout-close']/img", "TO Close the box");
				ProjectMethods.bw.close();
			  }		  
		//Login Error Message- 1
		 public static void loginerrorMsg() throws InterruptedException, IOException{
			 ProjectMethods.startReport("Login_To_SalesForce","/Users/huzaifazainuddin/desktop/report");
			 ProjectMethods.loginpage();
			 ProjectMethods.Update_Report("Pass", "Launch Browser", "Salesforce browser is launched"); 
			 Thread.sleep(3000);  
			  /* Enter username*/
			  ProjectMethods.enterText("//*[@id='username']", "user@gmail.com", "UserName");
			  /*Leave blank field for pw*/
			  ProjectMethods.clickbutton("//*[@id='Login']", "Login to Salesforce"); 
			  ProjectMethods.errorMessage("//*[@id='error']", "No Password", "Please enter your Password.");
			  ProjectMethods.bw.close();;
			  }
		 //Check Remember Me - 3
		 public static void rembMe() throws InterruptedException, IOException{
			 loginToSFDC();
			 ProjectMethods.startReport("Remember Username","/Users/huzaifazainuddin/desktop/report");
			 Thread.sleep(4000);
			 ProjectMethods.clickbutton("//*[@id='userNav-menuItems']/a[4]", "Logout");
			 ProjectMethods.chckbxon("//*[@id='rememberUn']", "Remember Username");
			 ProjectMethods.Update_Report("Pass", "Remember username", "Checkbox to remember username has been turned on"); 
			 ProjectMethods.bw.close();
		 }
		 
		 //Forgot Password 4-A
		 public static void frgotpw() throws InterruptedException, IOException{
			 ProjectMethods.startReport("Login_To_SalesForce", "/Users/huzaifazainuddin/desktop/report");
			 ProjectMethods.loginpage();
			 Thread.sleep(3000);
			 ProjectMethods.clickbutton("//*[@id='forgot']/span[1]/a", "Forgot Password");
			 Thread.sleep(4000);
			 ProjectMethods.enterText("//*[@id='un']", "huzaifa.aamir@tijaaratraabehah.com", "User Name");
			 ProjectMethods.clickbutton("//*[@id='continue']", "Continue");
			 ProjectMethods.Update_Report("Pass", "Forgot Password", "Entered fields to retrieve forgotten password");
			 ProjectMethods.bw.close();
		 }
		
		 //Forgot Password 4-B
		 public static void frgotpw2() throws InterruptedException, IOException{
			 ProjectMethods.startReport("Login_To_SalesForce", "/Users/huzaifazainuddin/desktop/report");
			 ProjectMethods.loginpage();
			 Thread.sleep(3000);
			 ProjectMethods.enterText("//*[@id='username']", "123", "UserName");
			 ProjectMethods.enterText("//*[@id='password']", "22131", "Password");
			 ProjectMethods.clickbutton("//*[@id='Login']", "Login to Salesforce");
			 ProjectMethods.errorMessage("//*[@id='error']", "Incorrect Password", "Your login attempt has failed. The username or password may be incorrect, or your location or login time may be restricted. Please contact the administrator at your company for help.");
			 ProjectMethods.Update_Report("Pass", "Password Incorrect", "Login attempt failed, username or password may be incorrect");
			 ProjectMethods.bw.close();
		 }
}
