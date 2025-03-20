package part03_DataProvider_TestNg;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Part03_Login_usingDataProvider {

	String[][] data= {
			{"Admin","admin1"},
			{"Admin1","admin123"},
			{"Admin","admin123"},
			{"Admin1","admin1"}
	};
	
	@DataProvider(name="LoginData")
	public String[][] LoginDataProvide()
	{
	return data;
	}
	
	@Test(dataProvider = "LoginData")
	public void Login(String UserName_Data,String PassWord_Data) throws InterruptedException
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
}
