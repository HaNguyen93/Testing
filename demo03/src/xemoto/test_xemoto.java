package xemoto;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;



public class test_xemoto {

		AppiumDriver<MobileElement> driver = null;
		public void launch_xemoto(){

			 
			//Set the Desired Capabilities
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("deviceName", "My Phone");
			caps.setCapability("udid", "LE66A06190140096"); //Give Device ID of your mobile phone
			caps.setCapability("platformName", "Android");
			caps.setCapability("platformVersion", "6.0.1");
		
			caps.setCapability("appPackage", "org.blueotter.xemoto");
			caps.setCapability("appActivity", "org.blueotter.xemoto.MenuActivity"); 
			
			caps.setCapability("noReset", "true");
			
			//Instantiate Appium Driver
			try {
					 driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);		


			} catch (MalformedURLException e) {
				System.out.println(e.getMessage());
			}
			
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			
		}
		
		
		public void logout() {
			
			driver.findElementById("org.blueotter.xemoto:id/title_bar_left_menu").click();
			driver.findElementByXPath("//android.widget.TextView[@text='Settings']").click();
			driver.findElementById("org.blueotter.xemoto:id/sign_out_button").click();
			
		}
		
		public void login_byemail() {
			driver.findElementById("org.blueotter.xemoto:id/emailfield").sendKeys("haxm007@blueotter.net");
			driver.findElementById("org.blueotter.xemoto:id/passwordfield").sendKeys("123123");
			driver.hideKeyboard();
			driver.findElementByClassName("android.widget.Button").click();
			System.out.println("login successful");
		}
	   
		public void login_fb() {
			
			//1. Click Facebook login button which is native :
			driver.findElementById("org.blueotter.xemoto:id/facebook_button").click();	
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			
			//2.here we getting the list of context
			Set<String> contextHandles = driver.getContextHandles();
			for (String s : contextHandles) {
			System.out.println("Context : " + s);
			//if context contains webview then set the webview context
			if (s.contains("WEBVIEW")) {
				driver.context(s);
			}
			}
			
			//3. Do Facebook Login Flow in webview :
			driver.findElementByXPath("//android.widget.EditText[@text='Phone or Email']").sendKeys("1234@gmail.com");
			driver.findElementByXPath("//android.widget.EditText[@NAF='true']").sendKeys("1234566");
			driver.findElementByXPath("//android.widget.Button[@text='LOG IN']").click();
			WebDriverWait driverWait = new WebDriverWait(driver, 10);
			driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='__CONFIRM__']"))); // waiting for the element to be clickable
			System.out.println(driver.getPageSource()); // get the page source 
			driver.findElementByXPath("//button[@name='__CONFIRM__']").click(); //  this step login process is done.
			
			//4. Come back to Native view :
			
			// here i need to get the context again
			Set<String> contextHandles2 = driver.getContextHandles();
			for (String s : contextHandles2) {
			System.out.println("Context : " + s);
			if (s.contains("NATIVE_APP")) {
				driver.context(s);
				}
			}
			
		}
		
		public void add_bike(String BikeName,String RentPrice, String LicenseNo, String Value,
				String BikeMake, String BikeModel, String BikeType, String Size, String Year) {
			
			//go to add bike
			driver.findElementById("org.blueotter.xemoto:id/thumbnail").click();
			
			//add cover picture
			driver.findElementById("org.blueotter.xemoto:id/ab_cover_image").click();
			driver.findElementByXPath("//android.widget.TextView[@text='Camera']").click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.findElementById("org.codeaurora.snapcam:id/shutter_button").click();
			driver.findElementById("org.codeaurora.snapcam:id/btn_done").click();	
			
			/*// add 1 normal picture
			driver.findElementById("org.blueotter.xemoto:id/ab_image_one").click();
			driver.findElementByXPath("//android.widget.TextView[@text='Camera']").click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.findElementById("org.codeaurora.snapcam:id/shutter_button").click();
			driver.findElementById("org.codeaurora.snapcam:id/btn_done").click();*/
			
			//Add info bike
			driver.findElementById("org.blueotter.xemoto:id/ab_bike_name_text_view").sendKeys(BikeName);
			driver.findElementById("org.blueotter.xemoto:id/ab_rent_per_day_text_view").sendKeys(RentPrice);
			driver.findElementById("org.blueotter.xemoto:id/ab_license_text_view").sendKeys(LicenseNo);
			driver.findElementById("org.blueotter.xemoto:id/ab_market_text_view").sendKeys(Value);
			driver.hideKeyboard();
			
			//scroll screen
			MobileElement element2 = driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().resourceId(\"org.blueotter.xemoto:id/infcontainer\")).setAsVerticalList().scrollIntoView("
					+ "new UiSelector().resourceId(\"org.blueotter.xemoto:id/save\"))"));
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			
			driver.findElementById("org.blueotter.xemoto:id/ab_make_spinner").click();	
			select_dropdownlist(BikeMake);       
			//model
			driver.findElementById("org.blueotter.xemoto:id/ab_model_spinner").click();	
			select_dropdownlist(BikeModel);
			//type
			driver.findElementById("org.blueotter.xemoto:id/ab_bike_type_spinner").click();	
			select_dropdownlist(BikeType);
			//cc
			driver.findElementById("org.blueotter.xemoto:id/ab_engine_size_spinner").click();
			select_dropdownlist(Size);
			//year 
			driver.findElementById("org.blueotter.xemoto:id/ab_year_spinner").click();
			select_dropdownlist(Year);
			
			//condition=>skip (not support)
			
			 //save
			driver.findElementById("org.blueotter.xemoto:id/save").click();
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			System.out.println("Add new bike successful");
			
			
		}
		
		 public void select_dropdownlist(String item) {
			 List dropList = driver.findElements(By.id("android:id/text1"));
			 for(int i=0; i< dropList.size(); i++){
				   MobileElement listItem = (MobileElement) dropList.get(i);   
				   if (listItem.getText().equals(item)) {
					   listItem.click();
					   break;
				   }
				   
				  }  
			 
		 }
		 
		 //edit bike 
		 
		public void edit_bike(String NameBikeEdit, String BikeName,String RentPrice, String LicenseNo, String Value,
				String BikeMake, String BikeModel, String BikeType, String Size, String Year){
			
			//go to search 
			driver.findElementById("org.blueotter.xemoto:id/search_button").click();
			driver.findElementById("org.blueotter.xemoto:id/searchv").sendKeys(NameBikeEdit);
			driver.hideKeyboard();
			
			//go to bike details
			driver.findElementByXPath("//android.widget.TextView[@text='"+NameBikeEdit+"']").click();;
			
			//go to edit bike
			driver.findElementById("org.blueotter.xemoto:id/bd_edit_button").click();
			
			//clear information 
			driver.findElementById("org.blueotter.xemoto:id/ab_bike_name_text_view").clear();
			driver.findElementById("org.blueotter.xemoto:id/ab_rent_per_day_text_view").clear();
			driver.findElementById("org.blueotter.xemoto:id/ab_license_text_view").clear();
			driver.findElementById("org.blueotter.xemoto:id/ab_market_text_view").clear();
			
			
			//edit info bike
			driver.findElementById("org.blueotter.xemoto:id/ab_bike_name_text_view").sendKeys(BikeName);
			driver.findElementById("org.blueotter.xemoto:id/ab_rent_per_day_text_view").sendKeys(RentPrice);
			driver.findElementById("org.blueotter.xemoto:id/ab_license_text_view").sendKeys(LicenseNo);
			driver.findElementById("org.blueotter.xemoto:id/ab_market_text_view").sendKeys(Value);
			driver.hideKeyboard();
			
			
			//scroll screen
			MobileElement element2 = driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().resourceId(\"org.blueotter.xemoto:id/infcontainer\")).setAsVerticalList().scrollIntoView("
					+ "new UiSelector().resourceId(\"org.blueotter.xemoto:id/save\"))"));
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			
			driver.findElementById("org.blueotter.xemoto:id/ab_make_spinner").click();	
			select_dropdownlist(BikeMake);       
			//model
			driver.findElementById("org.blueotter.xemoto:id/ab_model_spinner").click();	
			select_dropdownlist(BikeModel);
			//type
			driver.findElementById("org.blueotter.xemoto:id/ab_bike_type_spinner").click();	
			select_dropdownlist(BikeType);
			//cc
			driver.findElementById("org.blueotter.xemoto:id/ab_engine_size_spinner").click();
			select_dropdownlist(Size);
			//year 
			driver.findElementById("org.blueotter.xemoto:id/ab_year_spinner").click();
			select_dropdownlist(Year);
			
			//condition=>skip (not support)
			
			 //save
			driver.findElementById("org.blueotter.xemoto:id/save").click();
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			System.out.println("Edit bike successful");
			driver.navigate().back();
		}
		
		public void delete_bike(String NameBikeDelete) {
			//go to search 
			driver.findElementById("org.blueotter.xemoto:id/search_button").click();
			driver.findElementById("org.blueotter.xemoto:id/searchv").sendKeys(NameBikeDelete);
			driver.hideKeyboard();
			
			//go to bike details
			driver.findElementByXPath("//android.widget.TextView[@text='"+NameBikeDelete+"']").click();;
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			//go to delete
			driver.findElementById("org.blueotter.xemoto:id/bd_delete_button").click();
			//select yes on pop-up 
			driver.findElementByXPath("//android.widget.Button[@text='Ok']").click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			System.out.println("delete bike is successful");
			
		}
	 
		 public static void main(String[] args) {
	    		test_xemoto obj=new test_xemoto();
	    		obj.launch_xemoto();
	    		//obj.logout();
    		
	    		//obj.login_fb();
	    		//obj.login_byemail();
	
	    		//obj.add_bike(String BikeName,String RentPrice, String LicenseNo, String Value);
	    		//obj.add_bike("autotest01","150000", "73B1-12345", "30000000","Piaggo","Fly","Manual","100 cc","2015");
	    		//obj.edit_bike("autotest01","FRIDAY_02","120000","73B1-9999", "40000000","Yamaha","Grande","Automatic","150 cc","2010");
	    		//obj.delete_bike("FRIDAY_02");
	    		//obj.delete_bike("ha99999");
 		
	    }

}
