package demo03;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Test_settings {
	
		AppiumDriver<MobileElement> driver = null;
		
		@BeforeTest
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
		@Test
		//start test
		 public void login_email(String email,String pass) {
			
			//go to login
			driver.findElementById("org.blueotter.motone:id/btnSignin").click();
			driver.findElementById("org.blueotter.motone:id/edtEmail").sendKeys(email);
			driver.findElementById("org.blueotter.motone:id/edtPass").sendKeys(pass);
			driver.hideKeyboard();
			driver.findElementById("org.blueotter.motone:id/btnSignin").click();
		
		}
		@Test
		 // change personal
		 public void change_password(String pass,String newPass) { 
			 
			 // Go to settings screen
			 driver.findElementByXPath("//android.widget.TextView[@text='Settings']").click();
			 //1. Go to change pass screen
			 driver.findElementByXPath("//android.widget.LinearLayout[@index='2']").click();
			 //2. Input current pass
			 driver.findElementById("org.blueotter.motone:id/edtCurrentPass").sendKeys(pass);
			 //3. Input New pass
			 driver.findElementById("org.blueotter.motone:id/edtNewPass").sendKeys(newPass);
			 //4. Input confirm new pass
			 driver.findElementById("org.blueotter.motone:id/edtConfirmNewPass").sendKeys(newPass);
			 driver.hideKeyboard();
			 //5. Click on Change button
			 
			 driver.findElementById("org.blueotter.motone:id/btnChangeAccount").click();
			 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ;
		 }
		@Test
		public void logout_app() {
			//Logout
			driver.findElementById("org.blueotter.motone:id/btnSignOut").click();
		}
		
		@AfterTest
		public void exit_app() {
			//Logout
			driver.quit();
		}
		
		public static void main(String[] args) {
			//get launch app
			String email1="hamn007@blueotter.net";
			String pass1= "123456";
			String newpass1= "123123";
			
			Test_settings obj = new Test_settings();
			obj.Launch_app();
			
			obj.login_email(email1,pass1);
			System.out.println("PASS login by email");
			
			obj.change_password(pass1, newpass1);
			obj.logout_app();
			
			obj.login_email(email1, newpass1);
			System.out.println("login with newpass successful");
			
			obj.exit_app();
			//obj.logout_app();
			//System.out.println("logout successful");

		}
}
