package Automation.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.AbstractCompoments.AbstractComponent;

public class OrderConfirmationPage extends AbstractComponent {
	
	WebDriver driver;
	
	public OrderConfirmationPage(WebDriver driver) {
		//initializing the driver using constructor
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".hero-primary")
	WebElement message;
	
	public String orderConfirmationMeaasge() {
		String successMessage=message.getText();
		return successMessage;
	}
	
}
