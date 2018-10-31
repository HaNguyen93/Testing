package xemoto;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


public class AddBike {

		AppiumDriver<MobileElement> driver = null;
		
		public void launch_xemoto(){

			 
			//Set the Desired Capabilities
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("deviceName", "My Phone");
			caps.setCapability("udid", "LE66A06190140096"); //Give Device ID of your mobile phone
			caps.setCapability("platformName", "Android");
			caps.setCapability("platformVersion", "6.0.1");
		
			caps.setCapability("appPackage", "org.blueotter.xemoto");
			caps.setCapability("appActivity", "org.blueotter.xemoto.feature.ActivitySplash"); 
			
			caps.setCapability("noReset", "true");
			
			//Instantiate Appium Driver
			try {
					 driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);		


			} catch (MalformedURLException e) {
				System.out.println(e.getMessage());
			}
			
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			
			System.out.println("launch xemoto app: Success");
			
			
		}
		
		
		public void add_bike(String BikeName,String LicenseNo,String RentPrice, String Value,
				String BikeMake, String BikeModel, String BikeType, String Size, String Year) {
			
			//go to add bike
			driver.findElementById("org.blueotter.xemoto:id/imvAddNewBike").click();
			
			//add cover picture
			driver.findElementById("org.blueotter.xemoto:id/imvBike0").click();
			driver.findElementById("org.blueotter.xemoto:id/txtTakePhoto").click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.findElementById("org.codeaurora.snapcam:id/shutter_button").click();
			driver.findElementById("org.codeaurora.snapcam:id/btn_done").click();	
			
			// add  normal picture 1
			driver.findElementById("org.blueotter.xemoto:id/imvBike1").click();
			driver.findElementById("org.blueotter.xemoto:id/txtTakePhoto").click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.findElementById("org.codeaurora.snapcam:id/shutter_button").click();
			driver.findElementById("org.codeaurora.snapcam:id/btn_done").click();
			
			//add  normal picture 2
			driver.findElementById("org.blueotter.xemoto:id/imvBike2").click();
			driver.findElementById("org.blueotter.xemoto:id/txtTakePhoto").click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.findElementById("org.codeaurora.snapcam:id/shutter_button").click();
			driver.findElementById("org.codeaurora.snapcam:id/btn_done").click();
			
			//add  normal picture 3
			driver.findElementById("org.blueotter.xemoto:id/imvBike3").click();
			driver.findElementById("org.blueotter.xemoto:id/txtTakePhoto").click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.findElementById("org.codeaurora.snapcam:id/shutter_button").click();
			driver.findElementById("org.codeaurora.snapcam:id/btn_done").click();
			
			//add  normal picture 4
			driver.findElementById("org.blueotter.xemoto:id/imvBike4").click();
			driver.findElementById("org.blueotter.xemoto:id/txtTakePhoto").click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.findElementById("org.codeaurora.snapcam:id/shutter_button").click();
			driver.findElementById("org.codeaurora.snapcam:id/btn_done").click();
			
			
			//Add info bike
			driver.findElementById("org.blueotter.xemoto:id/edtBikeName").sendKeys(BikeName);
			driver.findElementById("org.blueotter.xemoto:id/edtBikeLicense").sendKeys(LicenseNo);
			driver.hideKeyboard();
			
			driver.findElementById("org.blueotter.xemoto:id/edtBikeRent").sendKeys(RentPrice);			
			driver.findElementById("org.blueotter.xemoto:id/edtBikeMarketValue").sendKeys(Value);
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			
			//driver.hideKeyboard();
			
			//driver.navigate().back();
			
			
			driver.findElementById("org.blueotter.xemoto:id/spnBikeMake").click();	
			select_dropdownlist(BikeMake);       
			//model
			driver.findElementById("org.blueotter.xemoto:id/spnBikeModel").click();	
			select_dropdownlist(BikeModel);
			
			//type
			driver.findElementById("org.blueotter.xemoto:id/spnBikeType").click();	
			select_dropdownlist(BikeType);
			
			//cc
			driver.findElementById("org.blueotter.xemoto:id/spnBikeEngine").click();
			select_dropdownlist(Size);
			
			//year 
			driver.findElementById("org.blueotter.xemoto:id/spnBikeYear").click();
			select_dropdownlist(Year);
			
			//condition
			driver.findElementByXPath("//android.widget.ImageView[@index='4']").click();
			
			 //save button
			driver.findElementById("org.blueotter.xemoto:id/btnSave").click();
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			
			//check results
			if(driver.findElementById("org.blueotter.xemoto:id/txtMainTitle").getText().equals("MY BIKES")) {
				System.out.println("Add new bike: Success");	
			}else {
				System.out.println("Add new bike: FAILED");
			}
				
		}
		
		 public void select_dropdownlist(String item) {
			 List dropList = driver.findElements(By.id("org.blueotter.xemoto:id/tv_tinted_spinner"));
			 for(int i=0; i< dropList.size(); i++){
				   MobileElement listItem = (MobileElement) dropList.get(i);   
				   if (listItem.getText().equals(item)) {
					   listItem.click();
					   break;
				   }
				   
				  }  
			 
		 }
		
		 public void Search_bike(String BikeNameSearch) {
			 
			 //go to search
			 driver.findElementById("org.blueotter.xemoto:id/imvSearchBike").click();
			 
			 //search bike
			 driver.findElementByXPath("//android.widget.ImageView[@index='0']").sendKeys(BikeNameSearch);
			 
			 //go to bike details
			 driver.findElementByXPath("//org.blueotter.xemoto:id/txtBikeName[@text='"+BikeNameSearch+"']").click();
			 
		 }

		 public void edit_bike(String BikeNameEdit, String BikeName,String LicenseNo,
				 String RentPrice, String Value,String BikeMake, String BikeModel, 
				 String BikeType, String Size, String Year) {
				
				//go to Bike Details
			 	driver.findElementByXPath("//android.widget.TextView[@text='"+BikeNameEdit+"']").click();
				//go to edit bike
			 	driver.findElementById("org.blueotter.xemoto:id/imvEditBike").click();
			 	
				//edit cover picture
				driver.findElementById("org.blueotter.xemoto:id/imvBike0").click();
				driver.findElementById("org.blueotter.xemoto:id/txtTakePhoto").click();
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				driver.findElementById("org.codeaurora.snapcam:id/shutter_button").click();
				driver.findElementById("org.codeaurora.snapcam:id/btn_done").click();	
				
				// edit  normal picture 1
				driver.findElementById("org.blueotter.xemoto:id/imvBike1").click();
				driver.findElementById("org.blueotter.xemoto:id/txtTakePhoto").click();
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				driver.findElementById("org.codeaurora.snapcam:id/shutter_button").click();
				driver.findElementById("org.codeaurora.snapcam:id/btn_done").click();
				
				//edit  normal picture 2
				driver.findElementById("org.blueotter.xemoto:id/imvBike2").click();
				driver.findElementById("org.blueotter.xemoto:id/txtTakePhoto").click();
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				driver.findElementById("org.codeaurora.snapcam:id/shutter_button").click();
				driver.findElementById("org.codeaurora.snapcam:id/btn_done").click();
				
				//edit  normal picture 3
				driver.findElementById("org.blueotter.xemoto:id/imvBike3").click();
				driver.findElementById("org.blueotter.xemoto:id/txtTakePhoto").click();
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				driver.findElementById("org.codeaurora.snapcam:id/shutter_button").click();
				driver.findElementById("org.codeaurora.snapcam:id/btn_done").click();
				
				//edit  normal picture 4
				driver.findElementById("org.blueotter.xemoto:id/imvBike4").click();
				driver.findElementById("org.blueotter.xemoto:id/txtTakePhoto").click();
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				driver.findElementById("org.codeaurora.snapcam:id/shutter_button").click();
				driver.findElementById("org.codeaurora.snapcam:id/btn_done").click();
				
				
				//edit info bike
				driver.findElementById("org.blueotter.xemoto:id/edtBikeName").clear();
				driver.findElementById("org.blueotter.xemoto:id/edtBikeLicense").clear();
				driver.findElementById("org.blueotter.xemoto:id/edtBikeName").sendKeys(BikeName);
				driver.findElementById("org.blueotter.xemoto:id/edtBikeLicense").sendKeys(LicenseNo);
				driver.hideKeyboard();
				
				driver.findElementById("org.blueotter.xemoto:id/edtBikeRent").clear();			
				driver.findElementById("org.blueotter.xemoto:id/edtBikeMarketValue").clear();
				driver.findElementById("org.blueotter.xemoto:id/edtBikeRent").sendKeys(RentPrice);			
				driver.findElementById("org.blueotter.xemoto:id/edtBikeMarketValue").sendKeys(Value);
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
				//driver.hideKeyboard();
				
				
				driver.findElementById("org.blueotter.xemoto:id/spnBikeMake").click();	
				select_dropdownlist(BikeMake);       
				//model
				driver.findElementById("org.blueotter.xemoto:id/spnBikeModel").click();	
				select_dropdownlist(BikeModel);
				//type
				driver.findElementById("org.blueotter.xemoto:id/spnBikeType").click();	
				select_dropdownlist(BikeType);
				//cc
				driver.findElementById("org.blueotter.xemoto:id/spnBikeEngine").click();
				select_dropdownlist(Size);
				//year 
				driver.findElementById("org.blueotter.xemoto:id/spnBikeYear").click();
				select_dropdownlist(Year);
				
				//condition
				driver.findElementByXPath("//android.widget.ImageView[@index='4']").click();
				
				 //save button
				driver.findElementById("org.blueotter.xemoto:id/btnSave").click();
				driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
				
				//check results
				if(driver.findElementById("org.blueotter.xemoto:id/txtBikeTitle").getText().equals(BikeName)) {
					System.out.println("Edit bike: Success");	
				}else {
					System.out.println("Edit bike: FAILED");
				}
					
			}

		 
		 public void delete_bike(String BikeNameDelete) {
				
				//go to Bike Details
			 	driver.findElementByXPath("//android.widget.TextView[@text='"+BikeNameDelete+"']").click();
				
			    //go to delete bike
			 	driver.findElementById("org.blueotter.xemoto:id/imvDeleteBike").click();
			 	//click on yes button
			 	driver.findElementByXPath("//android.widget.Button[@text='YES']").click();
			 	
			 	
			 	//check results
				if(driver.findElementById("org.blueotter.xemoto:id/txtMainTitle").getText().equals("MY BIKES")) {
					System.out.println("Delete bike: Success");	
				}else {
					System.out.println("Delete bike: FAILED");
				}
			 
			 	
			 	
		 }
		 public static void main(String[] args) {
	    		AddBike obj=new AddBike();
	    		obj.launch_xemoto();
	    		//obj.delete_bike("autotest02");
	    		//obj.delete_bike("autotest01");
	    		obj.add_bike("xe001","73B1-9999","150000","30000000","Yamaha","Acruzo","Manual","100 cc","2015");
	    		obj.edit_bike("xe001", "editbike", "73B1-8888", "185000", "30000000", "Honda", "Click", "Manual", "110 cc", "2018");
	    		obj.delete_bike("editbike");
		 }
			
}
		
		