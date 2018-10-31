package motone;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class login_rent {
	
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
 	}
		
		//start test
	/*	public void login_app() {
			driver.findElementByXPath("//android.widget.TextView[@text='Settings']").click();
			//go to login
			driver.findElementById("org.blueotter.motone:id/btnSignin").click();
			driver.findElementById("org.blueotter.motone:id/edtEmail").sendKeys("hamn007@blueotter.net");
			driver.findElementById("org.blueotter.motone:id/edtPass").sendKeys("123123");
			driver.hideKeyboard();
			driver.findElementById("org.blueotter.motone:id/btnSignin").click();
			//go to Home
			driver.findElementByXPath("//android.widget.TextView[@text='Home']").click();
		}*/
		
		public void search_bike() {
			//search bike name=solo
			driver.findElementById("org.blueotter.motone:id/imvIconSearch").click();
			driver.findElementById("org.blueotter.motone:id/edtSearchBike").sendKeys("bike@@");
			driver.findElementById("org.blueotter.motone:id/imvBikeItemSearch").click();
	
		}
		
		public void rent_bike() {
			
			//1. Go to Details Bike => Rent
			driver.findElementById("org.blueotter.motone:id/btnRent").click();
			//2. Select method payment=>Cash
			driver.findElementById("org.blueotter.motone:id/llLayoutCash").click();
			driver.findElementById("org.blueotter.motone:id/btnRentContinue").click();
			//3. Waiting confirmation
			driver.manage (). timeouts (). implicitlyWait (30, TimeUnit.SECONDS);
			//4. Skip guide
			driver.findElementById("org.blueotter.motone:id/txtSkip").click();
			//5. Get PIN
			driver.findElementById("org.blueotter.motone:id/btnAcceptBike").click();
			String PIN=driver.findElementById("org.blueotter.motone:id/txtRentPin").getText();
			System.out.println(PIN);
			driver.manage (). timeouts (). implicitlyWait (30, TimeUnit.SECONDS);
		}
		
		public void return_bike() {
			
			//1. Request return bike
			if(driver.findElementById("org.blueotter.motone:id/btnAcceptBike").getText()=="RETURN REQUEST") {
				driver.findElementById("org.blueotter.motone:id/btnAcceptBike").click();
			}else {
				driver.manage (). timeouts (). implicitlyWait (30, TimeUnit.SECONDS);
				driver.findElementById("org.blueotter.motone:id/btnAcceptBike").click();
			}
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
			driver.findElementByXPath("//android.widget.Button[@text='INPUT TOKEN']").click();
			
		}
		
		public void logout_app() {
			//Logout
			driver.findElementById("org.blueotter.motone:id/btnSignOut").click();
		}
		
		
		
		public static void main(String[] args) {
			//get launch app
			
			login_rent obj = new login_rent();
			obj.Launch_app();
			//obj.login_app();
			//System.out.println("PASS login");
			
			obj.search_bike();
			System.out.println("PASS search");
			
			obj.rent_bike();
			System.out.println("finish rent bike");
			
			obj.return_bike();
			
			//obj.logout_app();
			//System.out.println("logout successful");

		}
}

