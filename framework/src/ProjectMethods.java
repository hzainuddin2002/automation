import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ProjectMethods extends initialdriver {
	static BufferedWriter bw = null;
	static BufferedWriter bw1 = null;
	static String htmlname;
	static String objType;
	static String objName;
	static String TestData;
	static String rootPath;
	static int report;


	static Date cur_dt = null;
	static String filenamer;
	static String TestReport;
	int rowcnt;
	static String exeStatus = "True";
	static int iflag = 0;
	static int j = 1;

	static String fireFoxBrowser;
	static String chromeBrowser;

	static String result;

	static int intRowCount = 0;
	static String dataTablePath;
	static int i;
	static String browserName;
	
	
	/*Name of method: Click button 
	 * Description: Clicks button on website
	 * Arguments: 
	 */
	public static void clickbutton(String objXpath, String report){
		if(driver.findElement(By.xpath(objXpath)).isDisplayed()){
			driver.findElement(By.xpath(objXpath)).click();
			System.out.println("Pass: " +report+" button is clicked");
		}else{
			reportFlag=0;
			System.out.println("Fail: object id "+report+ " does not exist");
		}
		
	}
	
	/*Name of method: EnterText
	 * Description: Enters text into fields
	 */
	
	public static void enterText(String objXpath, String textVal, String report) throws IOException{
		 
		  if (driver.findElement(By.xpath(objXpath)).isDisplayed()){
			  driver.findElement(By.xpath(objXpath)).clear();
			  driver.findElement(By.xpath(objXpath)).sendKeys(textVal);
			  Update_Report("Pass", "enterText", textVal+" is entered in"+report+" field");	  
			  System.out.println("Pass: "+textVal+" is entered in "+report+" field");  
			  }else{
				  reportFlag=0;
				  Update_Report("Fail", "enterText", textVal+" was not entered in"+report+" field");
				  System.out.println("Fail: "+textVal+" field does not exist, please check your applicatoin");
			  }
			  
		  }
				
	/*Name of method: Validate Error Message
	 * Description: Generated error message on login if no password has been entered
	 */
	public static void errorMessage(String objXpath, String report, String expectedErrorMsg) throws IOException{
		if (driver.findElement(By.xpath(objXpath)).isDisplayed()){
			String actualErrorMsg = driver.findElement(By.xpath(objXpath)).getText();
			if(expectedErrorMsg.equals(actualErrorMsg)){
				System.out.println("Pass:  "+ report +" error message '"+ actualErrorMsg+"' matches expected error message '"+ expectedErrorMsg+"'");
				Update_Report("Pass", "Error Message", report +" error message '"+ actualErrorMsg+"' matches expected error message '"+ expectedErrorMsg);
				}else{
				reportFlag=0;	
				System.out.println("Fail:  " + report + " error message '" + actualErrorMsg +"' does not match expected error message '" + expectedErrorMsg+"'");
				Update_Report("Fail", "Error Message", report + " error message '" + actualErrorMsg +"' does not match expected error message '" + expectedErrorMsg);
				}
			}else{
				System.out.println("Unable to locate objectId '"+objXpath+"', please check your applicatoin");
			}
	}
	/* Name of method: Print Validate message
	 * Description: Prints the message of objxpath
	 */
	public static void textinFrame(String objxpath, String report) throws IOException{
		if (driver.findElement(By.xpath(objxpath)).isDisplayed()){
			driver.switchTo().frame("itarget");
			String validateText = driver.findElement(By.xpath(objxpath)).getText();
			System.out.println(validateText);
		}else{
			System.out.println("Unable to locate objectId '"+objxpath+"', please check your applicatoin");
		}
	}
	
	/*Name of method: Login Page
	 * Description: Navigates to salesforce.com, launches login page
	 */
	public static void loginpage() throws IOException{
		driver = new FirefoxDriver();
		driver.get("https://login.salesforce.com/");
		Update_Report("Pass", "Lauch Browser", "SalesForce browser is Launched");
		driver.manage().window().maximize();
		
	}
	
	/*Name of method: Validate Check box on
	 * Description: Validates if remember boxes and other saved features and being stored on website
	 */
	public static void chckbxon(String objxpath, String report){
		if(driver.findElement(By.xpath(objxpath)).isDisplayed()){
			if(driver.findElement(By.xpath(objxpath)).isSelected()){
				System.out.println("Pass: "+report+"has already been selected");
			}else{
				driver.findElement(By.xpath(objxpath)).click();
				System.out.println("Pass: "+report+"has been seleced");
			}
		}else{
			System.out.println("Unable to locate objectId '"+objxpath+"', please check your application");
		}
	}
	/*Name of method: Validate check box off
	 * Description: Validates to make sure that check box is off 
	 */
	public static void chckboxoff(String objxpath, String report){
		if(driver.findElement(By.xpath(objxpath)).isDisplayed()){
			if(driver.findElement(By.xpath(objxpath)).isSelected()){
				driver.findElement(By.xpath(objxpath)).click();
				System.out.println("Pass: "+report+"has been unchecked");
			}else{
				System.out.println("Pass: "+report+"has already been unchecked");
			}
		}else{
			System.out.println("Unable to locate objectId '"+objxpath+"', please check your application");
		}
	}
	
	/*Method : Start Report
	 * Description: Used to start and create HTML Report
	 */
	public static void startReport(String scriptName, String ReportsPath) throws IOException{
		
		String strResultPath = null;
		
		
		String testScriptName =scriptName;
		
		
		cur_dt = new Date(); 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String strTimeStamp = dateFormat.format(cur_dt);

		if (ReportsPath == "") { 
			
			ReportsPath = "C:\\";
		}

		if (ReportsPath.endsWith("\\")) { 
			ReportsPath = ReportsPath + "\\";
		}

		strResultPath = ReportsPath + "Log" + "/" +testScriptName + "/"; 
		File f = new File(strResultPath);
		f.mkdirs();
		htmlname = strResultPath  + testScriptName + "_" + strTimeStamp 
				+ ".html";
		
		

		bw = new BufferedWriter(new FileWriter(htmlname));

		bw.write("<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR><TD BGCOLOR=#66699 WIDTH=27%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Browser Name</B></FONT></TD><TD COLSPAN=6 BGCOLOR=#66699><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>"
				+ "FireFox " + "</B></FONT></TD></TR>");
		bw.write("<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR COLS=7><TD BGCOLOR=#BDBDBD WIDTH=3%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>SL No</B></FONT></TD>"
				+ "<TD BGCOLOR=#FF0000 WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Step Name</B></FONT></TD>"
				+ "<TD BGCOLOR=#F27107 WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Execution Time</B></FONT></TD> "
				+ "<TD BGCOLOR=##40FF00 WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Status</B></FONT></TD>"
				+ "<TD BGCOLOR=#F207CF WIDTH=47%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Detailed Report</B></FONT></TD></TR>");


	}
	
	/*Method: Update Report
	 * Description: Updates HTML Report
	 */
	public static void Update_Report(String Res_type,String Action, String result) throws IOException {
		String str_time;
		Date exec_time = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		str_time = dateFormat.format(exec_time);
		if (Res_type.startsWith("Pass")) {
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
					+ (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ "Passed"
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ result + "</FONT></TD></TR>");

		} else if (Res_type.startsWith("Fail")) {
			exeStatus = "Failed";
			report = 1;
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
					+ (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
					+ "<a href= "
					+ htmlname
					+ "  style=\"color: #FF0000\"> Failed </a>"

					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
					+ result + "</FONT></TD></TR>");

		} 
	}
	/*Method = Read Excel Sheet
	 * Description: Reads an excel document and views it by a 2d array
	 */
	public static String[][] ReadXLSheet(String datatablePath, String sheetName) throws IOException{
		
		/*Step 1: Get the XL Path*/
		File xlFile = new File(datatablePath);
		/*step2: Access the Xl File*/
		FileInputStream xlDoc = new FileInputStream(xlFile);
		/*Step3: Access the work book */
		HSSFWorkbook wb = new HSSFWorkbook(xlDoc);
		/*Step4: Access the Sheet */
		HSSFSheet sheet = wb.getSheet(sheetName);
		int iRowCount = sheet.getLastRowNum()+1;
		int iColCount = sheet.getRow(0).getLastCellNum();
		String[][] xlData = new String[iRowCount][iColCount];
		for(int i =0; i < iRowCount; i++){
			for(int j =0; j <iColCount; j++){
				HSSFCell cell = sheet.getRow(i).getCell(j);
				xlData[i][j] = cell.getStringCellValue();
			}			
		}
		return xlData;
	}
	
	public static void WriteXlSheet(String datatablePath, String sheetName, int iRow, int iCol, String xlData) throws IOException{
		//Step 1 read the contents from the excel sheet 
		File xlFile = new File(datatablePath);
		
		//Step 2 Access the excel file
		FileInputStream xlDoc = new FileInputStream(xlFile);
		
		//Step 3 Access the workbook in the excel file
		HSSFWorkbook wb = new HSSFWorkbook(xlDoc);
		
		//Step 4 Access the sheet
		HSSFSheet sheet = wb.getSheet(sheetName);		
		
		//Access Row
		HSSFRow row = sheet.getRow(iRow);		
		
		//Access Column
		HSSFCell cell = row.getCell(iCol);	
		
		//Set String Type
		cell.setCellValue(HSSFCell.CELL_TYPE_STRING);
		if(xlData.equalsIgnoreCase("Pass")){
			HSSFCellStyle titleStyle = wb.createCellStyle();
			titleStyle.setFillForegroundColor(new HSSFColor.GREEN().getIndex());
			titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cell.setCellStyle(titleStyle);
		}else{
			HSSFCellStyle titleStyle = wb.createCellStyle();
			titleStyle.setFillForegroundColor(new HSSFColor.RED().getIndex());
			titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cell.setCellStyle(titleStyle);
		}
		cell.setCellValue(xlData);		
		
		
		
		FileOutputStream fout = new FileOutputStream(datatablePath);
		wb.write(fout);
		fout.flush();
		fout.close();
		
		//Set Color
		
	}
}
	
