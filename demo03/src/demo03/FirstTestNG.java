package demo03;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class FirstTestNG {
	
		AppiumDriver<MobileElement> driver = null;
		
		
		public void Launch_app() {
		//Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "My Phone");
		caps.setCapability("udid", "LE66A06190140096"); //Give Device ID of your mobile phone
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "6.0.1");
	
		caps.setCapability("appPackage", "org.blueotter.motone");
		caps.setCapability("appActivity", "org.blueotter.motone.feature.main.view.ActivityMain"); 
		
		caps.setCapability("noReset", "true");
		
		//Instantiate Appium Driver
		try {
				 driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);		


		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
		
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ;
		System.out.println("Launch motone successful!");
		//go to settings
		driver.findElementByXPath("//android.widget.TextView[@text='Settings']").click();
 	}
		
		//start test
		 public void login_email() {
			
			//go to login
			driver.findElementById("org.blueotter.motone:id/btnSignin").click();
			driver.findElementById("org.blueotter.motone:id/edtEmail").sendKeys("hamn007@blueotter.net");
			driver.findElementById("org.blueotter.motone:id/edtPass").sendKeys("123123");
			driver.hideKeyboard();
			driver.findElementById("org.blueotter.motone:id/btnSignin").click();
		
		}
		 
		 public void login_google() {
			 driver.findElementById("org.blueotter.motone:id/imvGgLogin").click();
			 //driver.findElementByXPath("//android.widget.TextView[@text='hetnghile205@gmail.com']");
			 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
			 
				
				//2.here we getting the list of context
				Set<String> contextHandles = driver.getContextHandles();
				for (String s : contextHandles) {
				System.out.println("Context : " + s);
				//if context contains webview then set the webview context
				if (s.contains("WEBVIEW")) {
					driver.context(s);
				}
				}
				
				//3. Do Facebook Login Flow in webview :
				/*driver.findElementByXPath("//android.widget.EditText[@text='Phone or Email']").sendKeys("1234@gmail.com");
				driver.findElementByXPath("//android.widget.EditText[@NAF='true']").sendKeys("1234566");
				driver.findElementByXPath("//android.widget.Button[@text='LOG IN']").click();
				WebDriverWait driverWait = new WebDriverWait(driver, 10);
				driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='__CONFIRM__']"))); // waiting for the element to be clickable
				System.out.println(driver.getPageSource()); // get the page source 
				driver.findElementByXPath("//button[@name='__CONFIRM__']").click(); //  this step login process is done.
*/				
				
				driver.findElementByXPath("//android.widget.TextView[@text='hetnghile205@gmail.com']");
				//4. Come back to Native view :
				
				// here i need to get the context again
				Set<String> contextHandles2 = driver.getContextHandles();
				for (String s : contextHandles2) {
				System.out.println("Context : " + s);
				if (s.contains("NATIVE_APP")) {
					driver.context(s);
					}
				}
				
			
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
		 }
		
		public void search_bike() {
			//search bike name=solo
			driver.findElementByXPath("//android.widget.TextView[@text='Home']").click();
			driver.findElementById("org.blueotter.motone:id/imvIconSearch").click();
			driver.findElementById("org.blueotter.motone:id/edtSearchBike").sendKeys("solo");
			driver.findElementById("org.blueotter.motone:id/imvBikeItemSearch").click();
	
		}
		
	
		public void rent_bike() {
			
			//1. Go to Details Bike => Rent
			driver.findElementById("org.blueotter.motone:id/btnRent").click();
			//2. Select method payment=>Cash
			driver.findElementById("org.blueotter.motone:id/llLayoutCash").click();
			driver.findElementById("org.blueotter.motone:id/btnRentContinue").click();
			//3. Waiting confirmation
			driver.manage (). timeouts (). implicitlyWait (30, TimeUnit.SECONDS);
			
		}
		
		
		public void exit_app() {
			
			driver.quit();
		}
		public static void main(String[] args) {
			//get launch app
			
			FirstTestNG obj = new FirstTestNG();
			obj.Launch_app();
			obj.login_google();
			
			//obj.login_email();
			//System.out.println("PASS login by email");

			//obj.logout_app();
			
			//obj.exit_app();
			//obj.logout_app();
			//System.out.println("logout successful");

		}
}
