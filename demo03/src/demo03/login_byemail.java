/*package demo03;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class login_byemail {

	
	public static void main(String[] args) {
		
		AppiumDriver<MobileElement> driver = null;
		 
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
		try {2
				 driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);		


		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
		
		//Added 5 seconds wait so that the app loads completely before starting with element identification
		try {
					Thread.sleep(5000);
			} catch (InterruptedException e) {
					e.printStackTrace();
				}  
		
	// go to settings
		List<MobileElement> elements = driver.findElementsByClassName("android.widget.TextView");
		for(MobileElement element : elements) {
			if(element.getAttribute("text").equals("Settings")) {
				element.click(); 
				break;
			}
		
		}
	//go to signin by email
		driver.findElementById("org.blueotter.motone:id/btnSignin").click();
		driver.findElementById("org.blueotter.motone:id/edtEmail").sendKeys("hamn007@blueotter.net");
		driver.findElementById("org.blueotter.motone:id/edtPass").sendKeys("123123");
		driver.findElementById("org.blueotter.motone:id/btnSignin").click();
	//login by fb
        driver.findElementById("org.blueotter.motone:id/imvFbLogin").click();
       
        
        
        driver.findElementById("m_login_email").sendKeys("hanguyen21052018@gmail.com");
        driver.findElementById("m_login_password").sendKeys("danang@205");
        driver.findElementById("android.widget.Button").click();
        
		
	}
}

*/