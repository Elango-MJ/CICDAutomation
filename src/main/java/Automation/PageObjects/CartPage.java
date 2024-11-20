package Automation.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.AbstractCompoments.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		//initializing the driver using constructor
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	@FindBy(css=".totalRow button")
	WebElement checkOutButton;
	
	public boolean getProductsAddedInCart(String ProdcutName) {
		boolean match=cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(ProdcutName));
		return match;
	}
	
	public PaymentPage clickCheckOutButton() throws InterruptedException {
		JavascriptExecutor 	js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(2000);
		checkOutButton.click();
		return new PaymentPage(driver);
		
	}
	

}
