package motone;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class mix_renting {
	
		AppiumDriver<MobileElement> driver = null;
		
		public void Launch_motone() {
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
		driver.manage (). timeouts (). implicitlyWait (30, TimeUnit.SECONDS);
		System.out.println("Launch motone successful!");
 	}
		
		//login motone
		
		public void login_motone() {
			//driver.manage (). timeouts (). implicitlyWait (10, TimeUnit.SECONDS);
			//driver.findElementByXPath("//android.widget.TextView[@text='Settings']").click();
			// go to settings
						List<MobileElement> elements = driver.findElementsByClassName("android.widget.TextView");
						for(MobileElement element : elements) {
							if(element.getAttribute("text").equals("Settings")) {
								element.click(); 
								break;
							}
						}
			
			
			
			//go to login
			driver.findElementById("org.blueotter.motone:id/btnSignin").click();
			driver.findElementById("org.blueotter.motone:id/edtEmail").sendKeys("hamn007@blueotter.net");
			driver.findElementById("org.blueotter.motone:id/edtPass").sendKeys("123123");
			driver.hideKeyboard();
			driver.findElementById("org.blueotter.motone:id/btnSignin").click();
			//go to Home
			driver.findElementByXPath("//android.widget.TextView[@text='Home']").click();
		}
		
		//search motone
		public void search_bike_motone() {
			//search bike name=solo
			driver.findElementById("org.blueotter.motone:id/imvIconSearch").click();
			driver.findElementById("org.blueotter.motone:id/edtSearchBike").sendKeys("solo");
			driver.findElementById("org.blueotter.motone:id/imvBikeItemSearch").click();
	
		}
		
		//start rent bike
		public void rent_request() {
			
			//1. Go to Details Bike => Rent
			driver.findElementById("org.blueotter.motone:id/btnRent").click();
			//2. Select method payment=>Cash
			driver.findElementById("org.blueotter.motone:id/llLayoutCash").click();
			driver.findElementById("org.blueotter.motone:id/btnRentContinue").click();
			//3. Waiting confirmation
			driver.manage (). timeouts (). implicitlyWait (20, TimeUnit.SECONDS);
		}
		
		public void rent_request_onReviews() {
			
			//1. Go to Details Bike => scroll 
			MobileElement element2 = driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().ClassName(\"android.widget.LinearLayout\")).setAsVerticalList().scrollIntoView("
					+ "new UiSelector().resourceId(\"org.blueotter.motone:id/txtCountBikeReview\"))"));
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			
			//go to reviews screen 
			driver.findElementById("org.blueotter.motone:id/txtCountBikeReview").click();
			
			//2. Select method payment=>Cash
			driver.findElementById("org.blueotter.motone:id/llLayoutCash").click();
			driver.findElementById("org.blueotter.motone:id/btnRentContinue").click();
			//3. Waiting confirmation
			driver.manage (). timeouts (). implicitlyWait (20, TimeUnit.SECONDS);
		}
			
		public String motone_get_PIN() {
			driver.manage (). timeouts (). implicitlyWait (20, TimeUnit.SECONDS);
			//driver.findElementById("org.blueotter.motone:id/txtSkip").click();
			driver.findElementById("org.blueotter.motone:id/btnAcceptBike").click();
			String PIN=driver.findElementById("org.blueotter.motone:id/txtRentPin").getText();
			driver.findElementById("org.blueotter.motone:id/btnRentDone").click();
			System.out.println("get PIN successful");
			System.out.println(PIN);
			return PIN;
			
		}
		
		public void exit_motone() {
			driver.quit();
		}
				
		public void launch_xemoto(){
			
			//AppiumDriver<MobileElement> driver = null;
			 
			//Set the Desired Capabilities
			DesiredCapabilities caps1 = new DesiredCapabilities();
			caps1.setCapability("deviceName", "My Phone");
			caps1.setCapability("udid", "LE66A06190140096"); //Give Device ID of your mobile phone
			caps1.setCapability("platformName", "Android");
			caps1.setCapability("platformVersion", "6.0.1");
		
			caps1.setCapability("appPackage", "org.blueotter.xemoto");
			caps1.setCapability("appActivity", "org.blueotter.xemoto.MenuActivity"); 
			
			caps1.setCapability("noReset", "true");
			
			//Instantiate Appium Driver
			try {
					 driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps1);		


			} catch (MalformedURLException e) {
				System.out.println(e.getMessage());
			}
			
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			System.out.println("Launch xemoto successful!");
			
		}

		//xemoto accept renting request
		public void accept_rent_request() {
			driver.findElementById("org.blueotter.xemoto:id/title_bar_left_menu").click();
			driver.findElementByXPath("//android.widget.TextView[@text='Notifications']").click();
			//click on renting request
			driver.findElementById("org.blueotter.xemoto:id/nl_rentee_name_label").click();
			//go to Details renting request=> scroll  screen to Accept button
			
			MobileElement element1 = driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().resourceId(\"org.blueotter.xemoto:id/period\")).setAsVerticalList().scrollIntoView("
					+ "new UiSelector().resourceId(\"org.blueotter.xemoto:id/rent_accept_button\"))"));
		
			
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			System.out.println("scroll successful!");
			//click on Accept button
			element1.click(); 
		
		}
		
		public void input_PIN(String maPIN) {
			
			driver.findElementById("org.blueotter.xemoto:id/title_bar_left_menu").click();
			driver.findElementByXPath("//android.widget.TextView[@text='Notifications']").click();
			//click on return request
			driver.findElementById("org.blueotter.xemoto:id/nl_rentee_name_label").click();
			//go to Details renting request=> scroll  screen to Accept button
			
			MobileElement element2 = driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().resourceId(\"org.blueotter.xemoto:id/period\")).setAsVerticalList().scrollIntoView("
					+ "new UiSelector().resourceId(\"org.blueotter.xemoto:id/rent_accept_button\"))"));
		
			
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			System.out.println("scroll successful!");
			
			driver.findElementById("org.blueotter.xemoto:id/txt_pin_entry").sendKeys(maPIN);
			//click on Deliver button
			element2.click(); 
		}
		
	
		public void exit_xemoto() {
			driver.quit();
		}
		
		public static void main(String[] args) {
			mix_renting obj=new mix_renting();
			//motone
			obj.Launch_motone();
			//obj.login_motone();
			obj.search_bike_motone();	
			obj.rent_request();
			obj.exit_motone();
			
			//xemoto accept request
			obj.launch_xemoto();
			obj.accept_rent_request();
			obj.exit_xemoto();
			
			//motone get PIN
			obj.Launch_motone();
			String pin=obj.motone_get_PIN();
			obj.exit_motone();
			

			//xemoto input PIN
			obj.launch_xemoto();		
			obj.input_PIN(pin);
			obj.exit_xemoto();
			System.out.println("finish rent bike");
			
		}
		
		
		
}

