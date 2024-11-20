package Automation.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.AbstractCompoments.AbstractComponent;

public class PaymentPage extends AbstractComponent {
	
	WebDriver driver;
	
	public PaymentPage(WebDriver driver) {
		//initializing the driver using constructor
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement countryBox;
	@FindBy(xpath="//span[text()=' India']")
	WebElement indiaCountry;
	@FindBy(css=".action__submit")
	WebElement submitButton;
	
	public void selectCountry(String countryName) {
		countryBox.sendKeys(countryName);
		indiaCountry.click();
	}
	public OrderConfirmationPage clickSubmitButton() {
		submitButton.click();
		return new OrderConfirmationPage(driver);
	}

}
