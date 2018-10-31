package study;
// user @DataProvider
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.List;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


public class demo_testng {

		AppiumDriver<MobileElement> driver = null;
		
		@Test (priority=0)
		public void launch_xemoto(){
 
			//Set the Desired Capabilities
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("deviceName", "My Phone");
			caps.setCapability("udid", "LE66A06190140096"); //Give Device ID of your mobile phone
			caps.setCapability("platformName", "Android");
			caps.setCapability("platformVersion", "6.0.1");
		
			caps.setCapability("appPackage", "org.blueotter.xemoto");
			caps.setCapability("appActivity", "org.blueotter.xemoto.feature.ActivitySplash"); 
			
			caps.setCapability("noReset", "true");
			
			//Instantiate Appium Driver
			try {
					 driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);		


			} catch (MalformedURLException e) {
				System.out.println(e.getMessage());
			}
			
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			
			System.out.println("launch xemoto app is successfully");
			
		}
		
		@DataProvider(name = "Authentication")
		 
		public static Object[][] credentials() {
		 
		        return new Object[][] { { "auto002@gmail.com", "123123" }, { "ha@gmail.com", "123456" }};
		 
		  }
		 
		@Test(priority=1, dataProvider = "Authentication")
		public void login(String Email, String Pass) {
			
			driver.findElementById("org.blueotter.xemoto:id/edtEmail").sendKeys(Email);
			driver.findElementById("org.blueotter.xemoto:id/edtPass").sendKeys(Pass);
			driver.hideKeyboard();
			driver.findElementById("org.blueotter.xemoto:id/btnSignin").click();
			
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			if(driver.findElementById("org.blueotter.xemoto:id/txtMainTitle").getText().equals("MY BIKES")) {
			    	
			    	System.out.println("Sign in xemoto is successfully: "+Email +"&"+Pass );
			}else {
				
					System.out.println("Sign in xemoto is Failed: "+Email +"&"+Pass );
			}
			
					
						//go to settings			
						driver.findElementById("org.blueotter.xemoto:id/rlLayoutMenu").click();
						driver.findElementByXPath("//android.widget.TextView[@text='Settings']").click();
						if(driver.findElementById("org.blueotter.xemoto:id/txtMainTitle").getText().equals("SETTINGS")) {
							System.out.println("Go to settings screen successfully");
						}
						
						//click on Signout button
						driver.findElementById("org.blueotter.xemoto:id/btnSignOut").click();
						driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
						
						//info singout successful
						if(driver.findElementById("org.blueotter.xemoto:id/btnSignin").isDisplayed()) {
							System.out.println("signout OK, please login to use app");
						}else {
							System.out.println("sign-out failed");
						}
					
				  
						
		
	}	
		
}

