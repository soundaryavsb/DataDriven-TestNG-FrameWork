package part05_XSSF_Exceldata;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DataDrivenUsingPOI {

	static List<String> UserNames=new ArrayList<String>();
	static List<String> Passwords=new ArrayList<String>();
	public void readExcel() throws IOException
	{
		FileInputStream file=new FileInputStream("C:\\Automation Projects\\Login OrangeHRM\\UserName and Password 1.xlsx");
		Workbook workbook=new XSSFWorkbook(file);
		Sheet sheet=workbook.getSheetAt(0);
		Iterator <Row> rowIterators= sheet.iterator();

		while (rowIterators.hasNext()) {
			Row rowValue = rowIterators.next();
			Iterator <Cell> ColumnIterator=rowValue.iterator();
			int i=2;
			while(ColumnIterator.hasNext())
			{
				if(i%2==0)
				{
					UserNames.add(ColumnIterator.next().getStringCellValue());
					
				}
				else
				{
					Passwords.add(ColumnIterator.next().getStringCellValue());										
				}
				i++;
			}
		}
	}

	public void Login(String UserName_Data, String PassWord_Data) throws InterruptedException
	{
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
	
	public void executeTest() throws InterruptedException
	{
		for(int i=0;i<UserNames.size();i++)
		{
			Login(UserNames.get(i), Passwords.get(i));
		}
	}
	public static void main(String[] args) throws IOException, InterruptedException {
		DataDrivenUsingPOI usingPOI=new DataDrivenUsingPOI();
		usingPOI.readExcel();
		System.out.println("User Names: "+UserNames);
		System.out.println("Pass Words: "+Passwords);
		usingPOI.executeTest();
	}

}
