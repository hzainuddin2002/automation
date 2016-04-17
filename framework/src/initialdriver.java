import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.jdt.internal.compiler.lookup.UpdatedMethodBinding;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class initialdriver {
	//Data Driven Framework for Automation 
	 static WebDriver driver;
	 public static int reportFlag;
	 public static void main(String [] args) throws InterruptedException, IOException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		
		 
		 
		String dtpath = "/Users/huzaifazainuddin/desktop/automation training/ConfigFile.xls";
		String[][] recData = ProjectMethods.ReadXLSheet(dtpath, "Sheet1");	
		
		
		for(int i=1; i<recData.length; i++){
			String scriptName= recData[i][2];// 2 is used to indicate column that you are getting the script names from in the excel file	
			if(recData[i][1].equalsIgnoreCase("Y")){
				reportFlag=1;
				//Java Reflections or Reflective
				Method testScript = AutomationScripts.class.getMethod(scriptName);
				testScript.invoke(testScript);
				if(reportFlag == 1){
					ProjectMethods.WriteXlSheet(dtpath, "Sheet1", i, 3, "Pass");
					}else{
						ProjectMethods.WriteXlSheet(dtpath, "Sheet1", i, 3, "Fail");
						}
				}else{
					System.out.println("In row number "+i+", script name '"+scriptName+"' skipped from execution");
					}
			}
		}
	 
	 
	 
	
	 
	 
	
	
}
