package motone;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class return_bike {
	
		AppiumDriver<MobileElement> driver = null;
		
		@Test(priority=0)
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
 	}
		
		@Test(priority=1)
		public void return_request() {
			
			//1. Request return bike
			/*if(driver.findElementById("org.blueotter.motone:id/btnAcceptBike").getText()=="RETURN REQUEST") {
				driver.findElementById("org.blueotter.motone:id/btnAcceptBike").click();
			}else {
				driver.manage (). timeouts (). implicitlyWait (30, TimeUnit.SECONDS);
				driver.findElementById("org.blueotter.motone:id/btnAcceptBike").click();
			}*/
			driver.findElementById("org.blueotter.motone:id/btnAcceptBike").click();
			
			//2. Click yes on pop-up
			driver.manage (). timeouts (). implicitlyWait (30, TimeUnit.SECONDS);
			driver.findElementById("org.blueotter.motone:id/btnYes").click();
			
			//3. Go to Details return bike
			String price_rent_bike= driver.findElementById("org.blueotter.motone:id/txtBikePrice").getText();
			String Number_day_rent=driver.findElementById("org.blueotter.motone:id/txtNumberOfDaysRented").getText();
			String Fee= driver.findElementById("org.blueotter.motone:id/txtConvenienceFee").getText();
			String Money= driver.findElementById("org.blueotter.motone:id/txtTotalCost").getText();
			
			System.out.println(price_rent_bike.substring(0,7).replace(",",""));
			System.out.println(Number_day_rent.substring(0,1));
			System.out.println(Fee);
			System.out.println(Money);
			
			
			int price= Integer.parseInt(price_rent_bike.substring(0,7).replace(",",""));
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

			//5. Click on Input Token=> Go to 
			System.out.println("go to input token to return bike");
			driver.findElementByXPath("//android.widget.Button[@text='INPUT TOKEN']").click();
			
		}
		
		@Test(priority=2)
		public void exit_app() {
			//exit
			driver.quit();			
		}
		
		
		
		public static void main(String[] args) {
			//get launch app
			
			return_bike obj = new return_bike();
			obj.Launch_app();
			
			obj.return_request();
			System.out.println("finish rent bike");

			obj.exit_app();
			//System.out.println("logout successful");

		}
}

