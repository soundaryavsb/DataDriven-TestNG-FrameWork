package part04_DataProvider_Excel_JXL_97_2003_WorkBook;

import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class LoginPractice1 {

	String data[][]=null;
	@DataProvider(name="Login Data")
	public String[][] DataProviderData() throws BiffException, IOException
	{
		data=ExcelData();
		return data;
	}
	
	public String[][] ExcelData() throws BiffException, IOException
	{
		FileInputStream file=new FileInputStream("C:\\Automation Projects\\Login OrangeHRM\\UserName and Password.xls");
	Workbook workbook=Workbook.getWorkbook(file);
	Sheet sheet=workbook.getSheet(0);
	int RowCount=sheet.getRows();
	int ColumnCount=sheet.getColumns();
	
	String testData[][]=new String[RowCount-1][ColumnCount];
	for(int i=1;i<RowCount;i++)
	{
		for(int j=0;j<ColumnCount;j++)
		{
			testData[i-1][j]=sheet.getCell(j, i).getContents();
		}
	}
	return testData;
	}
	
	@Test(dataProvider="Login Data")
	public void Login(String UserName_Data,String PassWord_Data) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\chrome Driver\\chromedriver-win32-v134\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
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
		
		driver.quit();
	}
	
}
