package driverFactory;

import org.apache.bcel.generic.ObjectType;
import org.openqa.selenium.WebDriver;

import common.FunctionsLibrary;
import utilities.ExcelFileUtil;

public class DriverScript extends FunctionsLibrary {

	String inputpath = "D:\\eclipse\\workspace\\ERP_Hybrid\\FileInput\\DataEngine.xlsx";
	String outputpath = "D:\\eclipse\\workspace\\ERP_Hybrid\\FileOutput\\results.xlsx";
	
	public void startTest()throws Throwable
	{
		String Module_Status = "";
		//call the exilfile util class method
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//iterate all rows in mastertestcase sheet
		
		for(int i=1;i<=xl.rowcount("MasterTestCases");i++)
		{
			if(xl.getcelldata("MasterTestCases", i, 2).equalsIgnoreCase("y"))
			{
			//store corresponding sheet into variable
			String TCModule = xl.getcelldata("MasterTestCases", i, 1);
			//iterate all rows in TCModule
			for(int j=1;j<=xl.rowcount(TCModule);j++)
			{
				//call all cells
				String Description = xl.getcelldata(TCModule, j, 0);
				String ObjectType = xl.getcelldata(TCModule, j, 1);
				String LocatorType = xl.getcelldata(TCModule, j, 2);
				String LocatorValue = xl.getcelldata(TCModule, j, 3);
				String  TestData = xl.getcelldata(TCModule, j, 4);
				try {
					if(ObjectType.equalsIgnoreCase("startBrowser"))
					{
						driver = FunctionsLibrary.StartBrowser();
					}
					else if(ObjectType.equalsIgnoreCase("openUrl"))
					{
						FunctionsLibrary.openurl(driver);
					}
					else if(ObjectType.equalsIgnoreCase("waitForElements"))
					{
						FunctionsLibrary.waitForElement(driver, LocatorType, LocatorValue, TestData);
					}
					else if(ObjectType.equalsIgnoreCase("typeAction"))
					{
						FunctionsLibrary.typeAction(driver, LocatorType, LocatorValue, TestData);
					}
					else if(ObjectType.equalsIgnoreCase("clickAction"))
					{
						FunctionsLibrary.clickAction(driver, LocatorType, LocatorValue);
					}
					else if(ObjectType.equalsIgnoreCase("validateTitle"))
					{
						FunctionsLibrary.validateTitle(driver, TestData);
					}
					else if(ObjectType.equalsIgnoreCase("closeBrowser"))
					{
						FunctionsLibrary.closeBrowser(driver);
					}
					//write as status into pass cell TCModule
					xl.setcelldata(TCModule, j, 5, "pass", outputpath);
					Module_Status="True";
					
				}
			catch(Exception e)
					{
				System.out.println(e.getMessage());
				//write as status into fail cell TCModule
				xl.setcelldata(TCModule, j, 5, "Fail", outputpath);
				Module_Status="Fail";
				
					}
				if(TCModule.equalsIgnoreCase("True"))
					{
					xl.setcelldata("MasterTestCases", i, 3, "pass", outputpath);
					}
				else
				{
					xl.setcelldata("MasterTestCases", i, 3, "Fail", outputpath);
				}
					}
					}
			
			else
		{
			//write a block which are flag to N
				xl.setcelldata("", i, 3, "Blocked",outputpath);
		}
		
	}
	}
}
		
