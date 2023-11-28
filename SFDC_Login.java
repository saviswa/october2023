package seleniumPractice;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class SFDC_Login {
	static WebDriver driver;
	static String user = "savita@sand.com";
	static String password = "test@123";

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		// WebDriver driver = new EdgeDriver();
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	
		TC1_loginErrormsg();
		TC4B_loginErrormsg();
		TC2_login();
		TC3_logout();
		TC4A_forgotPassword();
		browserClose();
		}
	static void TC1_loginErrormsg() {
		browserLaunch();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(user);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("Login")).click();
		String actualErrorMessage = driver.findElement(By.id("error")).getText();
		String expectedErrorMessage = "Please enter your password.";
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
		
	}

//Launch the browser and login to the salesforce page.
	
        static void TC2_login() throws InterruptedException {
		browserLaunch();
		Assert.assertEquals(driver.getTitle(), "Login | Salesforce", "Loginpage Title is not as expected");
		// System.out.println(driver.getTitle());
		WebElement username = driver.findElement(By.id("username"));
		username.clear();
		username.sendKeys(user);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("rememberUn")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("Login")).click();
		Assert.assertEquals(driver.getTitle(), "Home Page ~ Salesforce - Developer Edition",
				"Homepage Title is not as expected");
		// System.out.println(driver.getTitle());
	}

	// Logout from salesfoce home page.
	static void TC3_logout() throws InterruptedException {
		driver.findElement(By.id("userNavLabel")).click();
		driver.findElement(By.xpath("//a[@title=\"Logout\"]")).click();
		Thread.sleep(5000);
		// String title = driver.getTitle();
		// System.out.println(title);
		Assert.assertEquals(driver.getTitle(), "Login | Salesforce", "Logout is not as successful");
		String name = driver.findElement(By.id("idcard-identity")).getText();
		Assert.assertEquals(name, user, "username is not populated");
		String checkboxStatus = driver.findElement(By.id("rememberUn")).getAttribute("checked");
		Assert.assertEquals(checkboxStatus, "true", "Remember me checkbox is unchecked");

	}
	static void TC4A_forgotPassword() throws InterruptedException {
		browserLaunch();
	    driver.findElement(By.id("forgot_password_link")).click();
	    //Assert Forgot Your Password | Salesforce
	    //id un sendkys
	    //id continue click
	    //text Check Your Email xpath "//h1[contains(text(),'Check Your Email')]"
	    //title Check Your Email | Salesforce
	    Assert.assertEquals(driver.getTitle(),"Forgot Your Password | Salesforce","forgot password page is not displayed");
	    driver.findElement(By.id("un")).sendKeys(user);
	    driver.findElement(By.id("continue")).click();
	    Thread.sleep(5000);
	    Assert.assertEquals(driver.getTitle(),"Check Your Email | Salesforce");
	    String actualMessage = driver.findElement(By.xpath("//div[@class='message']/p[1]")).getText();
	    String expectedMessage = "We’ve sent you an email with a link to finish resetting your password.";
	    Assert.assertEquals(actualMessage, expectedMessage);    
	}
	static void TC4B_loginErrormsg() {
		browserLaunch();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("123");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("2345");
		driver.findElement(By.id("Login")).click();
		String actualErrorMessage = driver.findElement(By.id("error")).getText();
		String expectedErrorMessage = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
		
	}

	static void browserLaunch() {
		driver.get("https://login.salesforce.com/");
	}

	static void browserClose() {
		driver.close();
	}
 
}