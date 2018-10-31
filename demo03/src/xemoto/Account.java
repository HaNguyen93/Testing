package xemoto;
// 04-07-2018
/*1. Create account
2. Sign up personal info
3. Sign in 
4. Change personal info
5. Change Password
6. Change bank info
7. Sign-out*/


import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.List;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


public class Account {

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
			
			System.out.println("launch xemoto app is successfully");
			
		}
		
		
		public void create_account(String email,String pass) {
			// Go to Create Account
			driver.findElementById("org.blueotter.xemoto:id/txtCreateAcct").click();
			
			//type email, pass
			driver.findElementById("org.blueotter.xemoto:id/edtEmail").sendKeys(email);
			driver.findElementById("org.blueotter.xemoto:id/edtPass").sendKeys(pass);
			
			//Create button
			driver.findElementById("org.blueotter.xemoto:id/btnRegister").click();
			
			//go to Your Information screen
			System.out.println("create new account xemoto is successfully");
			
		}
		
		
		public void Sign_up_Infor(String FirstName,String LastName,String NameShop, 
				String PhoneNumber,String BankName,String BranchName, String HolderName, String BankNumber) {
			
			//add avatar picture
			driver.findElementById("org.blueotter.xemoto:id/imvAvatar").click();
			driver.findElementByXPath("//android.widget.TextView[@text='Camera']").click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.findElementById("org.codeaurora.snapcam:id/shutter_button").click();
			driver.findElementById("org.codeaurora.snapcam:id/btn_done").click();	
			
			//FirstName, LastName, NameShop, PhoneNumber
			driver.findElementById("org.blueotter.xemoto:id/edtFirstName").sendKeys(FirstName);
			driver.findElementById("org.blueotter.xemoto:id/edtLastName").sendKeys(LastName);
			driver.findElementById("org.blueotter.xemoto:id/edtBusinessName").sendKeys(NameShop);
			driver.findElementById("org.blueotter.xemoto:id/edtPhoneNumber").sendKeys(PhoneNumber);
			
			//select location 
			driver.findElementById("org.blueotter.xemoto:id/txtAddress").click();
			//driver.findElementByXPath("//android.widget.TextView[@index='1']").click();
			//driver.findElementById("com.google.android.gms:id/edit_text").sendKeys(Address);
			//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			//driver.findElementById("com.google.android.gms:id/place_autocomplete_prediction_primary_text").click();
			driver.findElementById("org.blueotter.xemoto:id/txtSaveLocation").click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			
			//select open hour => defaults to test
			
			
			//SWIFTcode, AccountBank
			driver.findElementById("org.blueotter.xemoto:id/edtBankName").sendKeys(BankName);
			driver.hideKeyboard();
			driver.findElementById("org.blueotter.xemoto:id/edtBranchName").sendKeys(BranchName);
			driver.hideKeyboard();
			driver.findElementById("org.blueotter.xemoto:id/edtHolderName").sendKeys(HolderName);
			driver.hideKeyboard();
			driver.findElementById("org.blueotter.xemoto:id/edtBankNumber").sendKeys(BankNumber);
			driver.hideKeyboard();
			
			//Join button
			driver.findElementById("org.blueotter.xemoto:id/btnJoin").click();
			
			
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		    if(driver.findElementById("org.blueotter.xemoto:id/txtMainTitle").getText().equals("MY BIKES")) {
		    	
		    	System.out.println("Sign up information is successfully");
		    }else {
		    	System.out.println("Sign up information is failed");
		    }
		
			
		}

		
		public void update_account(String FirstName,String LastName,String NameShop, String PhoneNumber) {
			
			//go to settings			
			driver.findElementById("org.blueotter.xemoto:id/rlLayoutMenu").click();
			driver.findElementByXPath("//android.widget.TextView[@text='Settings']").click();
			if(driver.findElementById("org.blueotter.xemoto:id/txtMainTitle").getText().equals("SETTINGS")) {
				System.out.println("Go to settings screen successfully");
			}
			
			 // Go to change information
			 driver.findElementById("org.blueotter.xemoto:id/llLayoutPersonalInfo").click();
			 
			 
			//add avatar picture
			driver.findElementById("org.blueotter.xemoto:id/imvAvatar").click();
			driver.findElementByXPath("//android.widget.TextView[@text='Take a photo']").click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.findElementById("org.codeaurora.snapcam:id/shutter_button").click();
			driver.findElementById("org.codeaurora.snapcam:id/btn_done").click();	
			
			//FirstName, LastName, NameShop, PhoneNumber
			driver.findElementById("org.blueotter.xemoto:id/edtFirstName").clear();
			driver.findElementById("org.blueotter.xemoto:id/edtLastName").clear();
			driver.findElementById("org.blueotter.xemoto:id/edtBusinessName").clear();
			driver.findElementById("org.blueotter.xemoto:id/edtPhoneNumber").clear();
			
			driver.findElementById("org.blueotter.xemoto:id/edtFirstName").sendKeys(FirstName);
			driver.findElementById("org.blueotter.xemoto:id/edtLastName").sendKeys(LastName);
			driver.findElementById("org.blueotter.xemoto:id/edtBusinessName").sendKeys(NameShop);
			driver.findElementById("org.blueotter.xemoto:id/edtPhoneNumber").sendKeys(PhoneNumber);
			
			//select location 
			driver.findElementById("org.blueotter.xemoto:id/txtAddress").click();
			driver.findElementById("org.blueotter.xemoto:id/txtSaveLocation").click();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			
			//select open hour => defaults to test
			
			driver.findElementById("org.blueotter.xemoto:id/btnSave").click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			
			if(driver.findElementById("org.blueotter.xemoto:id/txtUsername").getText().equals(FirstName+" "+LastName)){
				System.out.println("username after changing: "+driver.findElementById("org.blueotter.xemoto:id/txtUsername").getText());	
				System.out.println("update information is successfully");
				
		    }else {		    	
		    	System.out.println("update information is failed ");   	
		    }
				
		}
	
		
		public void change_password(String EmailSignin,String pass,String newPass) { 
			 
			 // Go to change pass screen
			 driver.findElementById("org.blueotter.xemoto:id/llLayoutAccountInfo").click();
			 
			 //check display correct account
			 if(driver.findElementById("org.blueotter.xemoto:id/edtEmail").getText().equals(EmailSignin)) {
				 System.out.println("Displaying correct account login:"+EmailSignin);
			 }
			 //Input current pass
			 driver.findElementById("org.blueotter.xemoto:id/edtCurrentPass").sendKeys(pass);
			 System.out.println("current pass:"+pass);
			 //Input New pass
			 driver.findElementById("org.blueotter.xemoto:id/edtNewPass").sendKeys(newPass);
			 System.out.println("new pass:"+newPass);
			 //Input confirm new pass
			 driver.findElementById("org.blueotter.xemoto:id/edtConfirmNewPass").sendKeys(newPass);
			 System.out.println("new pass confirm:"+newPass);
			 
			 //driver.hideKeyboard();
			 //Save	 
			 driver.findElementById("org.blueotter.xemoto:id/btnSave").click();
			 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ;
			 
			 //back to settings 			 
			 if(driver.findElementById("org.blueotter.xemoto:id/txtMainTitle").getText().equals("SETTINGS")) {
					System.out.println("Change pass is successfully");
				}else {
					System.out.println("Change pass is failed");
				}
			 
		 }
		
		
		public void update_BankInfo(String SWIFTcode,String AccountBank) {
			// go to Bank infomation
			driver.findElementById("org.blueotter.xemoto:id/llLayoutBankInfo").click();
			driver.findElementById("org.blueotter.xemoto:id/edtSwiftCode").sendKeys(SWIFTcode);
			driver.findElementById("org.blueotter.xemoto:id/edtBankNumber").sendKeys(AccountBank);
			
			//Save	 
			 driver.findElementById("org.blueotter.xemoto:id/btnSave").click();
			 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS) ;
			 
			 //back to settings 			 
			 if(driver.findElementById("org.blueotter.xemoto:id/txtMainTitle").getText().equals("SETTINGS")) {
					System.out.println("update bank information is successfully");
				}else {
					System.out.println("update bank information is failed");
				}
		}
		
		public void sign_out() {
			
			//go to settings			
			driver.findElementById("org.blueotter.xemoto:id/rlLayoutMenu").click();
			driver.findElementByXPath("//android.widget.TextView[@text='Settings']").click();
			if(driver.findElementById("org.blueotter.xemoto:id/txtMainTitle").getText().equals("SETTINGS")) {
				System.out.println("Go to settings screen successfully");
			}
			
			//click on Signout button
			driver.findElementById("org.blueotter.xemoto:id/btnSignOut").click();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			//info singout successful
			if(driver.findElementById("org.blueotter.xemoto:id/btnSignin").isDisplayed()) {
				System.out.println("signout OK, please login to use app");
			}else {
				System.out.println("signout failed");
			}
			
		}
		
		
		public void login_app(String Email, String Pass) {
			
			driver.findElementById("org.blueotter.xemoto:id/edtEmail").sendKeys(Email);
			driver.findElementById("org.blueotter.xemoto:id/edtPass").sendKeys(Pass);
			driver.hideKeyboard();
			
			driver.findElementById("org.blueotter.xemoto:id/btnSignin").click();	
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			System.out.println("Sign in xemoto is successfully");
			/*if(driver.findElementById("org.blueotter.xemoto:id/txtMainTitle").getText().equals("MY BIKES")) {
			    	
			    	System.out.println("Sign in xemoto is successfully");
			}*/
		}
		
		
		public void quit() {
			
			driver.quit();
			System.out.println("quit app is successfully");
		}
		

		public static void main(String[] args) {
			
			 Account obj=new Account();
			 obj.launch_xemoto();
			 
			 obj.create_account("xm003@gmail.com", "123123");
			 
			// obj.login_app("xm003@gmail.com", "123123");
			 obj.Sign_up_Infor("Ha", "xm001", "Bike for rent at Da Nang -090909090",
		    		 "01678351105","nhap ten ngan hang", "Ten chi nhanh","Toi la chu tai khoan","998997676");
	    	/* obj.login_app("autotest005@gmail.com", "123123");
	    	 obj.Sign_up_Infor("Ha", "autotest005", "Bike for rent at Da Nang -090909090",
	    		 "01678351105","99999AAAAA", "53756735");
	    	 obj.update_account("Ha2", "autotest005_02", "Bike for rent at Da Nang -090909090 edit successfully", "1231231234");
	    	 
	    	 obj.change_password("autotest005@gmail.com", "123123", "123456");
	    	 
	    	 obj.update_BankInfo("123456789AAAAA", "1234123");
			 obj.sign_out();
			 obj.login_app("autotest005@gmail.com", "123456");
			 obj.quit();*/
	    	 
	    }

}

