package motone;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class login_fb {
	
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
		
public void loginby_fb() {
	
	// go to settings
			List<MobileElement> elements = driver.findElementsByClassName("android.widget.TextView");
			for(MobileElement element : elements) {
			if(element.getAttribute("text").equals("Settings")) {
				element.click(); 
				break;
					}
				}
	 		
			
			//1. Click Facebook login button which is native :
			driver.findElementById("org.blueotter.motone:id/imvFbLogin").click();	
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			/*
			//2.here we getting the list of context
			Set<String> contextHandles = driver.getContextHandles();
			for (String s : contextHandles) {
			System.out.println("Context : " + s);
			//if context contains webview then set the webview context
			if (s.contains("WEBVIEW")) {
				driver.context(s);
				}
			}*/
			
			//3. Do Facebook Login Flow in webview :
			driver.findElementByXPath("//android.widget.EditText[@text='Phone or Email']").sendKeys("hanguyen21052018@gmail.com");
			driver.hideKeyboard();
			driver.findElementByXPath("//android.widget.EditText[@NAF='true']").sendKeys("danang@205");
			driver.findElementByXPath("//android.widget.Button[@text='LOG IN']").click();
			/*WebDriverWait driverWait = new WebDriverWait(driver, 10);
			driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='__CONFIRM__']"))); // waiting for the element to be clickable
			System.out.println(driver.getPageSource()); // get the page source 
			driver.findElementByXPath("//button[@name='__CONFIRM__']").click(); //  this step login process is done.
*/	
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
			
			//4. Come back to Native view :
			
			/*// here i need to get the context again
			Set<String> contextHandles2 = driver.getContextHandles();
			for (String s : contextHandles2) {
			System.out.println("Context : " + s);
			if (s.contains("NATIVE_APP")) {
				driver.context(s);
				}
			}*/
			
			
		}
		
		
	
		public static void main(String[] args) {
			login_fb obj = new login_fb();
			obj.Launch_app();
			obj.loginby_fb();
			
			
		}
}


