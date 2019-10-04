	#########################################################################################################################
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~SETUP AUTOMATION FOR MAC~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	#########################################################################################################################
	//How to setup iOS Automation testing on MacOS
	https://medium.com/2359media/tutorial-automated-testing-on-ios-with-appium-test-ng-and-java-on-mac-bc115d0ec881
	
	*********Step by Step
                
		1. Java (JDK)
		2. Appium + Appium Doctor
		3. Xcode
		4. Xcode command line tools
		5. WebDriverAgent
		6. Real Devices OR Simulators
		7. IDE (eclipse)
		8. Homebrew
		9. Carthage //cai nay dung de build duoc file WebDriverAgent len ios ne`
		10. authorize-ios
		11. ios-deploy
		12. ideviceinstaller
		13. ios_webkit_debug_proxy
		14. Maven
		15. Eclipse // Cai neu muon development script java
		16. TestNg // k nhat thiet phai cai
		17. Node & NPM
		
	+ Step 1: Install Homebrew
		/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
		https://brew.sh/
	
	+ Step 2: Install Carthage
		brew install carthage
		//In order to launch WebDriverAgent, your macOS will need to have Carthage installed
		//(Not needed for automation on android)
		
	+ Step 3: Install Node & NPM
		brew install node
	
	+ Step 4: Install Java JDK and set JAVA_HOME
		//Download the JDK, jdk-8u181-macosx-x64.dmg, from this link:
		http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html.
		java -version //test java
		
		//Set environment variables
		vim ~/.bash_profile
		export JAVA_HOME=[path of your java home] //"/Library/Java/JavaVirtualMachines/jdk1.8.0_171.jdk/Contents/Home"
		export PATH=$JAVA_HOME/bin:$PATH
		echo $JAVA_HOME //Check path and java_home
		
	+ Step 5: Install Appium : 
		npm install -g appium@1.13.0 //neu k set version thi tu dong download ban moi nhat 
		Install Appium Desktop (optional)

	+ Step 6: Get XCode
		********Xcode + MacOS upgrade to last version
		//Xcode is the development and debug environment on Mac.
		//Provides required tools, files for dev/automation for Mac and iOS apps
	
	+ Step 7: Install authorize-ios
		npm install -g authorize-ios
	
	+ Step 8: Install ios-deploy
		brew install ios-deploy
	
	+ Step 9: Install XCode command line tools
		//Open terminal and run 
		xcode-select --install
		????sudo xcode-select -r
	
	+ Step 10: Install ideviceinstaller
		brew install ideviceinstaller
		
	+ Step 11: Install ios_webkit_debug_proxy
		brew install ios-webkit-debug-proxy

	+ Step 12: Install Appium Doctor
		npm install -g appium-doctor
		appium-doctor // check all install for automation need for appium
		
	+ Step 13: Install Maven 
		brew install maven
		mvn -version
		vim ~/.bash_profile
		export PATH=/usr/local/Cellar/maven/3.5.4/bin:$PATH 	//Be sure to set the version number as per the version you installed
	
	+ Step 14: Install Eclipse //not requirement
	Download and install Eclipse IDE for Java EE Developers from https://www.eclipse.org/downloads/packages/
	
	+ Step 15: Create Apple ID
		Goto Xcode - preferences - account
		Add Apple ID
		
	+ Step 16: Initialize WebDriverAgent project
	
		//On terminal cd to WebDriverAgent folder
		WebDriverAgent driver will be found in /path/where/installed/lib/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent 
		
		//For Appium Desktop
		/Applications/Appium.app/Contents/Resources/app/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent 
		
		//For Appium Installed through node
		/usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver/WedDriverAgent

		mkdir -p Resources/WebDriverAgent.bundle
		./Scripts/bootstrap.sh -d

		********Note: this command needs Carthage to be installed

	+ Step 17: Open WebDriverAgent.xcodeproj in Xcode 
    
		Project name : WebDriverAgent.xcodeproj
		Location : 

		//For Appium Desktop
		/Applications/Appium.app/Contents/Resources/app/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent 
		
		//For Appium Installed through node
		/usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver/WedDriverAgent

	+ Step 18: Under the project in Xcode
		For WebDriverAgentLib and WebDriverAgentRunner targets, go to general tab and select "Automatically manage signing", and then select your Development Team 
		
		*********Xcode may fail to create a provisioning profile for the WebDriverAgentRunner target: 
			Failed to create provisioning profile
			No profile for ‘…..’ were found 
			Solution:
			manually change the bundle id for the target by going into the "Build Settings" tab, and changing the "Product Bundle Identifier" from com.facebook.WebDriverAgentRunner to something that Xcode will accept: 
			Going back to the "General" tab for the WebDriverAgentRunner target, you should now see that it has created a provisioning profile and all is well: 
			Clean - Build - Run

	+ Finally, you can verify that everything works. Build the project: 
		xcodebuild -project WebDriverAgent.xcodeproj -scheme WebDriverAgentRunner -destination 'id=udid' test 
		udid = device UDID
	
		//To get the udid of the real device connected 
		A9F18306-6B22-442B-99F4-F217A5EE8D38
		xcodebuild -project WebDriverAgent.xcodeproj -scheme WebDriverAgentRunner -destination 'id=A9F18306-6B22-442B-99F4-F217A5EE8D38' test 
 
		npm install -g ios-deploy
		ios-deploy -c 
		OR
		instruments -s devices //xem udid cua thiet bi dang ket noi tren mac

	+ Step 19: Create a project and add desired capabilities for automation on iOS
		deviceName
		platformName
		platformVersion
		app

	+ Open Eclipse
		Create a new maven project or use any existing maven project
		Add maven dependencies 
		appium 
		selenium
		{
		  DesiredCapabilities cap = new DesiredCapabilities();
		  cap.setCapability("platformName", "iOS");
		  cap.setCapability("platformVersion", "11.4");
		  cap.setCapability("deviceName", "iPhone 8");
		  cap.setCapability(CapabilityType.BROWSER_NAME, "safari");
		  cap.setCapability("app", “location of .app or .ipa file“);
		 
		  URL url = new URL("http://127.0.0.1:4723/wd/hub");
		  
		  IOSDriver driver = new IOSDriver(url, cap);
		} 
	+ External tools/utilities may be required
		
		//Install authorize-ios
		npm install -g authorize-ios
		
		//Install libimobiledevice 
		brew install libimobiledevice 

		//Install ideviceinstaller / ios-deploy
			To deploy our apps to our devices. We can install and debug apps from the command line without using Xcode
			sudo xcode-select -r
			brew install ideviceinstaller
			ideviceinstaller doesn't work with iOS 10 yet. So we need to install ios-deploy
			npm install -g ios-deploy

		//Install  ios_webkit_debug_proxy
			brew install ios-webkit-debug-proxy