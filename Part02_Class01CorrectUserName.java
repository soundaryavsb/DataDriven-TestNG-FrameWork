package part02_Parameters_LoginTestCases;

import java.util.concurrent.ConcurrentHashMap.KeySetView;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Part02_Class01CorrectUserName {

	@Test
	@Parameters({"UserName","PassWord"})
	public void loginWithCorrectUserName(String UserName_Data,String PassWord_Data) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "C:\\chrome Driver\\chromedriver-win32-v134\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
		
		Thread.sleep(3000);
		
		WebElement UserName=driver.findElement(By.name("username"));
//		UserName.click();
		UserName.sendKeys(UserName_Data);
		
		WebElement Password=driver.findElement(By.xpath("//*[@name='password']"));
		Password.sendKeys(PassWord_Data);
		
		Thread.sleep(2000);
		
		WebElement Login=driver.findElement(By.xpath("//*[@type='submit']"));
		Login.click();
		
		driver.quit();
	}
}
