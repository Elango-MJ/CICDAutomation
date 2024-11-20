package Automation.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.AbstractCompoments.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		//initializing the driver using constructor
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//WebElement emailID= driver.findElement(By.id("userEmail"));
	//WebElement password=driver.findElement(By.id("userPassword"));
	//WebElement submitButton=driver.findElement(By.id("login"));
	
	@FindBy(id="userEmail")
	WebElement emailID;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submitButton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement loginErrorMessage;
	
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatalougePage loginToApplication(String userMailId, String userPassword) {
		emailID.sendKeys(userMailId);
		password.sendKeys(userPassword);
		submitButton.click();
		ProductCatalougePage productCatalougePage =new ProductCatalougePage(driver);
		return productCatalougePage;
	}
	
	public String getLoginErrorMessage() {
		waitforWebElementToVisible(loginErrorMessage);
		return loginErrorMessage.getText();
	}

}
