package study;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class display_test {
	
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
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		System.out.println("Launch motone successful!");

 	}
		
	public void checklogin() {
		
		// go to settings
					List<MobileElement> elements = driver.findElementsByClassName("android.widget.TextView");
					for(MobileElement element : elements) {
						if(element.getAttribute("text").equals("Settings")) {
							element.click(); 
							break;
						}
					}
	
					Assert.assertFalse(driver.findElementById("org.blueotter.motone:id/btnSignin").isDisplayed());	
					
	}
		
	public void login_app() {
			driver.findElementByXPath("//android.widget.TextView[@text='Settings']").click();
			//go to login
			driver.findElementById("org.blueotter.motone:id/btnSignin").click();
			driver.findElementById("org.blueotter.motone:id/edtEmail").sendKeys("hamn007@blueotter.net");
			driver.findElementById("org.blueotter.motone:id/edtPass").sendKeys("123123");
			driver.hideKeyboard();
			driver.findElementById("org.blueotter.motone:id/btnSignin").click();
			//go to Home
			driver.findElementByXPath("//android.widget.TextView[@text='Home']").click();
		}

	public void logout_app() {
			//Logout
			driver.findElementById("org.blueotter.motone:id/btnSignOut").click();
		}
}
		