package Automation.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.AbstractCompoments.AbstractComponent;

public class OrdersPage extends AbstractComponent {
	
	WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		//initializing the driver using constructor
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//tr/td[2]")
	List<WebElement> orderedProducts;
	
	public boolean getOrderedProducts(String ProdcutName) {
		boolean match=orderedProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(ProdcutName));
		return match;
	}
	
	

}
