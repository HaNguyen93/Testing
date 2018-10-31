package demo03;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class check_fb {
		
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
			 public void login_fb() {
				
				//go to login
				 
				driver.findElementById("org.blueotter.motone:id/imvFbLogin").click();
				List<MobileElement> elements = driver.findElements(By.className("android.webkit.WebView"));
				
				driver.findElementById("m_login_email").sendKeys("aaaaa");
				driver.findElementById("m_login_password").sendKeys("aaaaa");
				driver.findElementById("android.widget.Button").click();
				driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
				
				
				
			}
			

			
			public void logout_app() {
				//Logout
				driver.findElementById("org.blueotter.motone:id/btnSignOut").click();
			}
			
			
			
			public static void main(String[] args) {
				
				check_fb obj =new check_fb();
				obj.Launch_app();
				obj.login_fb();
			
				//obj.logout_app();
				//System.out.println("logout successful");

			}
	}



