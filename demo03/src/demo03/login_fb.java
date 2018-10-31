package demo03;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class login_fb {

	
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
		
	// go to settings
		
			List<MobileElement> elements = driver.findElementsByClassName("android.widget.TextView");
			for(MobileElement element : elements) {
				if(element.getAttribute("text").equals("Settings")) {
					element.click(); 
					break;
				}
			
			}

		driver.findElementById("org.blueotter.motone:id/btnSignin").click();
		driver.findElementById("org.blueotter.motone:id/edtEmail").sendKeys("hamn007@blueotter.net");
		driver.findElementById("org.blueotter.motone:id/edtPass").sendKeys("123123");
		driver.hideKeyboard();
		driver.findElementById("org.blueotter.motone:id/btnSignin").click();
		
		try {
			Thread.sleep(5000);
	        } catch (InterruptedException e) {
			e.printStackTrace();
		}  
		
		// go to home
		
        List<MobileElement> elements1 = driver.findElementsByClassName("android.widget.TextView");
					for(MobileElement element : elements1) {
						if(element.getAttribute("text").equals("Home")) {
							element.click(); 
							break;
						}
					
					}
	
		//go to Search
		driver.findElementById("org.blueotter.motone:id/imvIconSearch").click();
		driver.findElementById("org.blueotter.motone:id/edtSearchBike").sendKeys("solo");
		driver.findElementById("org.blueotter.motone:id/imvBikeItemSearch").click();
		
		//go to Details Bike => Rent
		driver.findElementById("org.blueotter.motone:id/btnRent").click();
		// payment
		driver.findElementById("org.blueotter.motone:id/llLayoutCash").click();
		driver.findElementById("org.blueotter.motone:id/btnRentContinue").click();
		//waiting confirmation
		driver.manage (). timeouts (). implicitlyWait (30, TimeUnit.SECONDS);
		driver.findElementById("org.blueotter.motone:id/txtSkip").click();
		
		//get PIN
		driver.findElementById("org.blueotter.motone:id/btnAcceptBike").click();
		String PIN=driver.findElementById("org.blueotter.motone:id/txtRentPin").getText();
		System.out.println(PIN);
		
		try {
			Thread.sleep(5000);
	        } catch (InterruptedException e) {
			e.printStackTrace();
		}  
		
		//close get PIn
		driver.findElementById("org.blueotter.motone:id/btnRentDone").click();
		try {
			Thread.sleep(10000);
	        } catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
		//request return
		//if(driver.findElementById("org.blueotter.motone:id/btnAcceptBike").getText()=="RETURN REQUEST") {
		driver.findElementById("org.blueotter.motone:id/btnAcceptBike").click();
		
			//System.out.println("please remind owner input PIN to finish renting BIke");
		
		
		driver.findElementById("org.blueotter.motone:id/btnYes").click();
		
		
		String price_rent_bike= driver.findElementById("org.blueotter.motone:id/txtBikePrice").getText();
		String Number_day_rent=driver.findElementById("org.blueotter.motone:id/txtNumberOfDaysRented").getText();
		String Fee= driver.findElementById("org.blueotter.motone:id/txtConvenienceFee").getText();
		String Money= driver.findElementById("org.blueotter.motone:id/txtTotalCost").getText();
		int Total= Integer.parseInt(Money);
		
		System.out.println(price_rent_bike.substring(0,7));
		System.out.println(Number_day_rent);
		System.out.println(Fee);
		System.out.println(Money);
		
		int price= Integer.parseInt(price_rent_bike);
		int day= Integer.parseInt(Number_day_rent);
		int fee15= Integer.parseInt(Fee);
		int moneyTotal= Integer.parseInt(Money);
		
		fee15= (int) (price*day*0.15);
		moneyTotal=price*day+ fee15;
		
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ;
		if(moneyTotal== Total) {
			System.out.println("PASS");
	
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ;
		//Go to input PIN
		driver.findElementById("org.blueotter.motone:id/btnAcceptBike").click();
		//Input PIN
		driver.findElementById("org.blueotter.motone:id/pinView").sendKeys("99999");
		driver.findElementById("org.blueotter.motone:id/btnSubmitPin").click();
		//Go to Reviews
		//--skip feedback---
		driver.findElementById("org.blueotter.motone:id/txtSkipRating").click();
		
		
		}		
		
		
	}
	
}

