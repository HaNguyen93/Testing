package motone;

/*
1. Login
2. Logout
3. Create account
4. Update account
5. Change password*/

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.List;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class account_motone {
	
		AppiumDriver<MobileElement> driver = null;
		
		public void Launch_app() {
		//Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "My Phone");
		caps.setCapability("udid", "LE66A06190140096"); //Give Device ID of your mobile phone
		
		/* caps.setCapability("udid", "emulator-5556"); //Give Device ID of your mobile phone
		 caps.setCapability("app", "C:\\Users\\Blue Otter HP2\\Downloads\\xemoto_staging_v1.0.0.apk"); 
		 caps.setCapability("appWaitActivity", "*");
		 caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");*/
		
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "6.0");
	
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
		
		
		public void create_motone(String FirstName, String LastName, String PhoneNumber, String Email, String Pass) {
			//go to Settings
			//driver.findElementByXPath("//android.widget.TextView[@text='Settings']").click();
			
			/*// go to settings
			List<MobileElement> elements = driver.findElementsByClassName("android.widget.TextView");
			for(MobileElement element : elements) {
				if(element.getAttribute("text").equals("Settings")) {
					element.click(); 
					break;
				}
			}*/
			

			driver.findElementById("org.blueotter.motone:id/btnCreateAcct").click();
			driver.findElementById("org.blueotter.motone:id/imvAvatar").click();
			//take avatar lan 1
			driver.findElementById("org.blueotter.motone:id/txtTakePhoto").click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
			driver.findElementById("org.codeaurora.snapcam:id/shutter_button").click();
			driver.findElementById("org.codeaurora.snapcam:id/btn_done").click();
			//avatar lan 2
			driver.findElementById("org.blueotter.motone:id/imvAvatar").click();
			driver.findElementById("org.blueotter.motone:id/txtTakePhoto").click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
			driver.findElementById("org.codeaurora.snapcam:id/shutter_button").click();
			driver.findElementById("org.codeaurora.snapcam:id/btn_done").click();
			//finish avatar
			
			
			driver.findElementById("org.blueotter.motone:id/edtFirstName").sendKeys(FirstName);
			driver.findElementById("org.blueotter.motone:id/edtLastName").sendKeys(LastName);
			driver.findElementById("org.blueotter.motone:id/edtPhoneNumber").sendKeys(PhoneNumber);
			
			driver.findElementById("org.blueotter.motone:id/edtEmail").sendKeys(Email);
			driver.findElementById("org.blueotter.motone:id/edtPass").sendKeys(Pass);
			driver.hideKeyboard();
			
			
			//submit
			driver.findElementById("org.blueotter.motone:id/btnContinue").click();
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ;
			
			//Go to Credit Card Information=> Skip 
			driver.findElementById("org.blueotter.motone:id/txtSkip").click();
			
			//check results
			if(driver.findElementById("org.blueotter.motone:id/txtTitle").getText().equals("SETTINGS")) {
				System.out.println("Create account: Success");	
			}else {
				System.out.println("Create account: FAILED");
			}
			
			
			
		}
		
		//edit account 
		public void update_account(String FirstName, String LastName, String PhoneNumber) {
			//go to setting 
			//driver.findElementByXPath("//android.widget.TextView[@text='Settings']").click();
			driver.findElementById("org.blueotter.motone:id/llLayoutPersonalSettings").click();
			
			//change avatar
			driver.findElementById("org.blueotter.motone:id/imvAvatar").click();
			driver.findElementByXPath("//android.widget.TextView[@text='Take a photo']").click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.findElementById("org.codeaurora.snapcam:id/shutter_button").click();
			driver.findElementById("org.codeaurora.snapcam:id/btn_done").click();	
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			//clear & edit  information
			driver.findElementById("org.blueotter.motone:id/edtFirstName").clear();
			driver.findElementById("org.blueotter.motone:id/edtFirstName").sendKeys(FirstName);
			
			driver.findElementById("org.blueotter.motone:id/edtLastName").clear();
			driver.findElementById("org.blueotter.motone:id/edtLastName").sendKeys(LastName);
			
			driver.findElementById("org.blueotter.motone:id/edtPhoneNumber").clear();
			driver.findElementById("org.blueotter.motone:id/edtPhoneNumber").sendKeys(PhoneNumber);
			driver.hideKeyboard();
			
			//change
			driver.findElementById("org.blueotter.motone:id/btnChangePersonal").click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			
			//check results
			if(driver.findElementById("org.blueotter.motone:id/txtTitle").getText().equals("SETTINGS")) {
				System.out.println("edit acount: Success");	
			}else {
				System.out.println("edit acount: FAILED");
			}
			
			
		}
		
		//change password
		public void change_password(String pass,String newPass) { 
			 
			 // Go to settings screen
			// driver.findElementByXPath("//android.widget.TextView[@text='Settings']").click();
			
			 //1. Go to change pass screen
			 driver.findElementByXPath("//android.widget.LinearLayout[@index='2']").click();
			 //2. Input current pass
			 driver.findElementById("org.blueotter.motone:id/edtCurrentPass").sendKeys(pass);
			 //3. Input New pass
			 driver.findElementById("org.blueotter.motone:id/edtNewPass").sendKeys(newPass);
			 //4. Input confirm new pass
			 driver.findElementById("org.blueotter.motone:id/edtConfirmNewPass").sendKeys(newPass);
			 driver.hideKeyboard();
			 //5. Click on Change button
			 
			 driver.findElementById("org.blueotter.motone:id/btnChangeAccount").click();
			 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ;
			 
			//check results
				if(driver.findElementById("org.blueotter.motone:id/txtTitle").getText().equals("SETTINGS")) {
					System.out.println("change_password: Success");	
				}else {
					System.out.println("change_password: FAILED");
				}
		 }
		
				
		//start test
		public void login_app(String email, String pass) {
			//driver.findElementByXPath("//android.widget.TextView[@text='Settings']").click();
			
			List<MobileElement> elements = driver.findElementsByClassName("android.widget.TextView");
			for(MobileElement element : elements) {
				if(element.getAttribute("text").equals("Settings")) {
					element.click(); 
					break;
				}
				
			//go to login
			driver.findElementById("org.blueotter.motone:id/btnSignin").click();
			driver.findElementById("org.blueotter.motone:id/edtEmail").sendKeys(email);
			driver.findElementById("org.blueotter.motone:id/edtPass").sendKeys(pass);
			driver.hideKeyboard();
			driver.findElementById("org.blueotter.motone:id/btnSignin").click();
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ;
			
			driver.findElementById("org.blueotter.motone:id/txtTitle").getText();
			//check results
			
			if(driver.findElementById("org.blueotter.motone:id/txtTitle").getText().equals("SETTINGS")) {
				System.out.println("Login: Success");	
			}else {
				System.out.println("Login: FAILED");
			}
			
			//go to Home
			//driver.findElementByXPath("//android.widget.TextView[@text='Home']").click();
			}
		}
		
		
		public void logout_app() {
			//Logout
			driver.findElementById("org.blueotter.motone:id/btnSignOut").click();
		}
		
		
	
		public static void main(String[] args) {
			account_motone obj = new account_motone();
			obj.Launch_app();
			//obj.login_app("test003@gmail.com","123123");
			/*obj.logout_app();
			obj.create_motone("Nguyen","Ha","01678351105","autotest001@gmail.com","123123");			
			obj.update_account("Hary","Nguyen","0909009099");
			obj.change_password("123123", "123456");*/
			
			
		}
}


