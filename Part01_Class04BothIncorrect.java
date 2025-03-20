package part01_Introduction_LoginTestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Part01_Class04BothIncorrect {
	@Test
	public void loginWithBothIncorrect() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "C:\\chrome Driver\\chromedriver-win32-v134\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
		
		Thread.sleep(3000);
		
		WebElement UserName=driver.findElement(By.name("username"));
//		UserName.click();
		UserName.sendKeys("Admin1");
		
		WebElement Password=driver.findElement(By.xpath("//*[@name='password']"));
		Password.sendKeys("admin");
		
		Thread.sleep(2000);
		
		WebElement Login=driver.findElement(By.xpath("//*[@type='submit']"));
		Login.click();
		
		driver.quit();
	}
}
