package xemoto;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


public class launch_xe {

		AppiumDriver<MobileElement> driver = null;
		
		public void launch_xemoto(){

			 
			//Set the Desired Capabilities
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("deviceName", "My Phone");
			caps.setCapability("udid", "LE66A06190140096"); //Give Device ID of your mobile phone
			
			//caps.setCapability("udid", "192.168.0.101:5555"); //Give Device ID of your mobile phone
			
			//caps.setCapability("udid", "FA46MWM02471"); //Give Device ID of your mobile phone
			caps.setCapability("platformName", "Android");
			caps.setCapability("platformVersion", "6.0.1");
		
			caps.setCapability("appPackage", "org.blueotter.xemoto");
			caps.setCapability("appActivity", "org.blueotter.xemoto.feature.ActivitySplash"); 
			
			caps.setCapability("noReset", "true");
			
			//Instantiate Appium Driver
			try {
					 driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);		
					 
					 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);					
					 System.out.println("launch xemoto app is successfully");

			} catch (MalformedURLException e) {
				System.out.println(e.getMessage());
			}
			
			
			
		}
		
}	