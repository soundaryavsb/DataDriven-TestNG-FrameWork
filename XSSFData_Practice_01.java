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

public class XSSFData_Practice_01 {

	static List<String> UserNames=new ArrayList<String>();
	static List<String> PassWords=new ArrayList<String>();
	public void ExcelData() throws IOException
	{
		FileInputStream file=new FileInputStream("C:\\Automation Projects\\Login OrangeHRM\\UserName and Password 1.xlsx");
		Workbook workbook=new XSSFWorkbook(file);
		Sheet sheet=workbook.getSheetAt(0);
		Iterator <Row> rowIterator=sheet.iterator();

		while(rowIterator.hasNext())
		{
			Row RowValue=rowIterator.next();
			Iterator <Cell> ColumnIterator=RowValue.iterator();
			int i=2;
			while(ColumnIterator.hasNext())
			{
				if(i%2==0)
				{
					UserNames.add(ColumnIterator.next().getStringCellValue());
				}
				else
				{
					PassWords.add(ColumnIterator.next().getStringCellValue());	
				}
				i++;
			}
		}
	}

	public void LoginA(String UserName_Data, String PassWord_Data) throws InterruptedException
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

	public void executeDataA() throws InterruptedException
	{
		for(int i=0;i<UserNames.size();i++)
		{
			LoginA(UserNames.get(i), PassWords.get(i));
		}
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		XSSFData_Practice_01 usingPOIA=new XSSFData_Practice_01();
		usingPOIA.ExcelData();
		System.out.println("UserName: "+UserNames);
		System.out.println("PassWord: "+PassWords);
		usingPOIA.executeDataA();

	}

}
