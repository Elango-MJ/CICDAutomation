package Automation.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Automation.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTests {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException {
		
		//Properties class
		Properties properties=new Properties();
		FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Automation//Resources//GlobalData.properties");
		properties.load(file);
		//Java turnery operator System.getProperty will get the browser name from maven comments.
		String browserName=System.getProperty("Browser") !=null ? System.getProperty("Browser") : properties.getProperty("Browser");
		//String browserName=properties.getProperty("Browser");
		
		if(browserName.contains("chrome")) {
		ChromeOptions options = new	ChromeOptions();
		WebDriverManager.chromedriver().setup();
		//to run the tests in headless mode
		if(browserName.contains("headless")) {
		options.addArguments("headless");
		}
		driver = new ChromeDriver(options);
		//driver.manage().window().setSize(new Dimension(1440,900));//full screen for the headless mode
		}
		else if(browserName=="edge") {
			//edge browser
		}
		else if(browserName=="FireFox") {
			//Firefox browser
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.manage().window().maximize();
		return driver;

		
	}
	
	public List<HashMap<String, String>> getJsonDataToHashMap(String filePath) throws IOException {
		
		//read JSON file and convert to String
		String jsonContent=FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
		//String to HashMap using jackson databind
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String, String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){
		});
		return data;
	}

	public String getScreenShot(String testCaseName,WebDriver driver) throws IOException {
		//Screenshot
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public void launchApplication() throws IOException {
		driver=initializeDriver();
		landingPage=new LandingPage(driver);
		landingPage.goTo();
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeBrowser() {
		driver.close();
	}

}
