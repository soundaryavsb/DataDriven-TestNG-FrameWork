package part04_DataProvider_Excel_JXL_97_2003_WorkBook;

import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class LoginExcelData {
	String[][] data= null;
	WebDriver driver;
	
	@DataProvider(name="LoginData")
	public String[][] ExcelData() throws BiffException, IOException
	{
		data=GetExcelData();
		return data;
	}

//	@Test
	public String[][] GetExcelData() throws BiffException, IOException
	{
		FileInputStream fileLocation=new FileInputStream("C:\\Automation Projects\\Login OrangeHRM\\UserName and Password.xls");
		Workbook workBookLocation=Workbook.getWorkbook(fileLocation);
		Sheet sheetLocation=workBookLocation.getSheet(0);
		int rowCount=sheetLocation.getRows();
		int columnCount=sheetLocation.getColumns();
		String[][] testData=new String[rowCount-1][columnCount];
		for(int i=1;i<rowCount;i++)
		{
			for(int j=0;j<columnCount;j++)
			{
				testData[i-1][j]=sheetLocation.getCell(j,i).getContents();
			}
		}
		return testData;
	}

	@BeforeTest
	public void BeforeTesting()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\chrome Driver\\chromedriver-win32-v134\\chromedriver.exe");
		driver=new ChromeDriver();

	
	}
	
	@Test(dataProvider = "LoginData")
	public void Login(String UserName_Data,String PassWord_Data) throws InterruptedException
	{
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		driver.manage().window().maximize();
		Thread.sleep(3000);

		WebElement UserName=driver.findElement(By.name("username"));
		UserName.sendKeys(UserName_Data);

		WebElement Password=driver.findElement(By.xpath("//*[@name='password']"));
		Password.sendKeys(PassWord_Data);

		Thread.sleep(2000);

		WebElement Login=driver.findElement(By.xpath("//*[@type='submit']"));
		Login.click();

	}
	
	@AfterTest
	public void AfterTesting()
	{
		driver.quit();
	}
}
