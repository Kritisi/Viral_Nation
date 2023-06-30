package VN;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_1 {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments("--remote-allow-origins=*"));
		driver.manage().window().maximize();
		driver.get("https://auth.dev.vntech.io");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//button[text()='Accept All']")).click();
		Thread.sleep(2000);
		String pwin = driver.getWindowHandle();
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//div[@class='nsm7Bb-HzV7m-LgbsSe-bN97Pc-sM5MNb oXtfBe-l4eHX']")).click();
		Set<String> a = driver.getWindowHandles();
		for (String win : a) {
			driver.switchTo().window(win);
		}
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("testuser1@gmail.com");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		Thread.sleep(2000);
		driver.close();
		driver.switchTo().window(pwin);
//		driver.findElement(By.xpath("(//button[normalize-space()='Continue with Facebook'])[1]")).click();
//		driver.findElement(By.cssSelector("#email")).sendKeys("facebookuser@gmail.com");
//		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("Password@123");
//		driver.findElement(By.xpath("//input[@id='u_0_0_vV']")).click();
//		Thread.sleep(2000);
		WebElement signUpButton = driver.findElement(By.xpath("//a[text()='Sign up']"));
		signUpButton.click();
		signUpWithEmail(driver, "testuser5@gmail.com", "Gmail", "password123");
		signUpWithEmail(driver, "testuser6@yahoo.com", "Yahoo", "password123");
		signUpWithEmail(driver, "testuser7@hotmail.com", "Hotmail", "password123");
		
		driver.quit();
	}

	private static void signUpWithEmail(WebDriver driver, String email, String fname, String lname) throws InterruptedException {
		driver.findElement(By.xpath("//p[text()='Other']")).click();
		
		WebElement firstname = driver.findElement(By.xpath("//p[text()='First Name']/following-sibling::div/input"));
		firstname.sendKeys(fname);

		WebElement nameInput = driver.findElement(By.xpath("//p[text()='Last Name']/following-sibling::div/input"));
		nameInput.sendKeys(lname);

		WebElement Email = driver.findElement(By.xpath("//p[text()='Email']/following-sibling::div/input"));
		Email.sendKeys(email);

		WebElement Create_Account = driver.findElement(By.xpath("//button[text()='Create Account']"));
		Create_Account.click();
		Thread.sleep(2000);
        driver.navigate().refresh();

		
	}
}
