package demo03;


//import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Test_xemoto {
	
		AppiumDriver<MobileElement> driver = null;
		
		//@Test(priority = 0)
		@Test
		public void Launch_app() {
		//Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "My Phone");
		caps.setCapability("udid", "LE66A06190140096"); //Give Device ID of your mobile phone
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "6.0.1");
	
		caps.setCapability("appPackage", "org.blueotter.xemoto");
		caps.setCapability("appActivity", "org.blueotter.xemoto.MenuActivity"); 
		
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
		//driver.findElementByXPath("//android.widget.TextView[@text='Settings']").click();
	}
		/*@Test(priority = 1)
		//start test
		 public void login_email() {
			
			//go to login
			driver.findElementById("org.blueotter.motone:id/btnSignin").click();
			driver.findElementById("org.blueotter.motone:id/edtEmail").sendKeys("hamn007@blueotter.net");
			driver.findElementById("org.blueotter.motone:id/edtPass").sendKeys("123456");
			driver.hideKeyboard();
			driver.findElementById("org.blueotter.motone:id/btnSignin").click();
		
		}*/
		/*
		@Test(priority = 2)
		public void logout_app() {
			//Logout
			driver.findElementById("org.blueotter.motone:id/btnSignOut").click();
		}*/
		//@Test(priority = 3)
		/*@Test
		public void exit_app() {
			//Logout
			driver.quit();*/
		//}
		public static void main(String[] args) {
			//get launch app
			
			Test_xemoto obj = new Test_xemoto();
			obj.Launch_app();
			
//			obj.login_email();
//			System.out.println("PASS login by email");
//
//			obj.logout_app();
			
			//obj.exit_app();
			//obj.logout_app();
			//System.out.println("logout successful");

		}
}
