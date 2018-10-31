package demo03;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
;
public class note {
	
	public static void main(String[] args) {
		
		AppiumDriver<MobileElement> driver = null;
		 
		//Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "My Phone");
		caps.setCapability("udid", "LE66A06190140096"); //Give Device ID of your mobile phone
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "6.0.1");
		caps.setCapability("appPackage", "org.blueotter.xemoto");
		//caps.setCapability("appPackage", "org.blueotter.motone");
		
		caps.setCapability("appActivity", "org.blueotter.xemoto.MenuActivity"); 
		//caps.setCapability("appActivity", "org.blueotter.motone.feature.main.view.ActivityMain"); 
		
		caps.setCapability("noReset", "true");
		
		//Instantiate Appium Driver
		try {
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
      
      
	
	/* //**********************HorizontalList scroll
      MobileElement element = driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"org.blueotter.xemoto:id/h_reserved_button\")).setAsHorizontalList().scrollIntoView("
				+ "new UiSelector().resourceId(\"org.blueotter.xemoto:id/h_ontheroad_button\"))"));
		
		//Perform the action on the element
        element.click();
        // go to add bike
        driver.findElementById("org.blueotter.xemoto:id/thumbnail").click();
        

      //**********************vertical scroll
        MobileElement element2 = driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"org.blueotter.xemoto:id/infotitle\")).setAsVerticalList().scrollIntoView("
				+ "new UiSelector().resourceId(\"org.blueotter.xemoto:id/save\"))"));
		
		//Perform the action on the element
		//System.out.println(element2.getAttribute("id"));
		element2.click();   */   
		
		// go to menu
			//	driver.findElementById("org.blueotter.xemoto:id/title_bar_left_menu").click();
		List<MobileElement> elements = driver.findElements(By.className("android.widget.CompoundButton"));
		int count=0;
		for(MobileElement element : elements) {		
			if(element.getAttribute("checked").equals("false")) {
				element.click(); 
				 count ++;					
				//break;
			}			
		}

		System.out.println(count);	
	}	
	
}