package motone;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class mix_return {
	
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
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		System.out.println("Launch motone successful!");
 	}
		
		public void return_request_motone() {
			//1. Send return request
			driver.findElementById("org.blueotter.motone:id/btnAcceptBike").click();
			
			//2. Click yes on pop-up
			driver.manage (). timeouts (). implicitlyWait (20, TimeUnit.SECONDS);
			driver.findElementById("org.blueotter.motone:id/btnYes").click();
			
			//3. Go to Details return bike
			String price_rent_bike= driver.findElementById("org.blueotter.motone:id/txtBikePrice").getText();
			String Number_day_rent=driver.findElementById("org.blueotter.motone:id/txtNumberOfDaysRented").getText();
			String Fee= driver.findElementById("org.blueotter.motone:id/txtConvenienceFee").getText();
			String Money= driver.findElementById("org.blueotter.motone:id/txtTotalCost").getText();
			
			System.out.println(price_rent_bike.substring(0,price_rent_bike.length()-2).replace(",",""));
			System.out.println(Number_day_rent.substring(0,1));
			System.out.println(Fee);
			System.out.println(Money);
			
			
			int price= Integer.parseInt(price_rent_bike.substring(0,price_rent_bike.length()-2).replace(",",""));
			//int price= Integer.parseInt(price_rent_bike.substring(0,7).replace(",",""));
			int day= Integer.parseInt(Number_day_rent.substring(0,1));
			int fee15= Integer.parseInt(Fee.replace(",",""));
			int Total= Integer.parseInt(Money.replace(",",""));
			
			fee15= (int) (price*day*0.15);
			int moneyTotal=price*day+ fee15;
			//4. Check calculation
			if(Total==moneyTotal) {
				System.out.println("Pass calculate");
			}else {
				System.out.println("Failed calculate");
			}

			
		}
		
		public void input_token(String p) {

			// Click on Input Token=> Go to 	
			driver.findElementByXPath("//android.widget.Button[@text='INPUT TOKEN']").click();
			System.out.println("go to input token to return bike");
			driver.findElementById("org.blueotter.motone:id/pinView").sendKeys(p);
			driver.findElementById("org.blueotter.motone:id/btnSubmitPin").click();
			
		}
		
		public void feedback_bike() {
			//vote 4 star
			driver.findElementByXPath("//android.widget.ImageView[@index='3']").click();
			//input feedback
			driver.findElementById("org.blueotter.motone:id/edtReasonRating").sendKeys("automation test with appium=> this bike very OK");
			//submit
			driver.findElementById("org.blueotter.motone:id/btnSubmitRating").click();
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ;
			
			if (driver.findElementById("org.blueotter.motone:id/txtTitle").getText()=="BIKE LIST") {
				
				System.out.println("Back to Home screen=> return bike successfully");
			}else {
				System.out.println(" return bike failed");
			}
			
		}
		
		public void exit_motone() {
			//exit
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
			
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			System.out.println("Launch xemoto successful!");
			
		}

		//xemoto accept renting request
		public String get_PIN_xemoto() {
			driver.findElementById("org.blueotter.xemoto:id/title_bar_left_menu").click();
			driver.findElementByXPath("//android.widget.TextView[@text='Notifications']").click();
			//click on renting request
			driver.findElementById("org.blueotter.xemoto:id/nl_rentee_name_label").click();
			//go to Details renting request=> scroll  screen to Accept button
			
			driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().resourceId(\"org.blueotter.xemoto:id/tamount\")).setAsVerticalList().scrollIntoView("
					+ "new UiSelector().className(\"android.widget.Button\"))"));
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			System.out.println("scroll successful!");
			
			String pin_return=driver.findElementById("org.blueotter.xemoto:id/return_token_text_field").getText();
			System.out.println(pin_return);
			return pin_return;
					
		
		}
		
		public void exit_xemoto() {
			//exit
			driver.quit();			
		}
		
		
		public static void main(String[] args) {
			
			mix_return obj = new mix_return();
			//motone
			obj.Launch_motone();	
			obj.return_request_motone();
			System.out.println("send return request finish");
			obj.exit_motone();
			
			//xemoto
			obj.launch_xemoto();
			String pin=obj.get_PIN_xemoto();
			obj.exit_xemoto();
			
			//motone
			obj.Launch_motone();
			obj.input_token(pin);
			obj.feedback_bike();
			System.out.println("finish return bike");
			
			
		}
}


