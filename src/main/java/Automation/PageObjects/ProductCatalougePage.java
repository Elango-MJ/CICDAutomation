package Automation.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.AbstractCompoments.AbstractComponent;

public class ProductCatalougePage extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductCatalougePage(WebDriver driver) {
		//initializing the driver using constructor
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	By productsBy=By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toast=By.cssSelector("#toast-container");
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	public List<WebElement> getTheListOfProducts() {
		waitforElementToVisible(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String ProdcutName) {
		WebElement prod=getTheListOfProducts().stream().filter(s->
		s.findElement(By.cssSelector("b")).getText().equals(ProdcutName)).findFirst().orElse(null);
		return prod;
		/*WebElement prod = null;
		for(int i=0;i<getTheListOfProducts().size();i++) {
			String ProdName=getTheListOfProducts().get(i).findElement(By.tagName("b")).getText();
			System.out.println(ProdName);
			if(ProdName.equals(ProdcutName)) {
				prod=getTheListOfProducts().get(i);
				System.out.println("AABC"+getTheListOfProducts().get(i).findElement(By.tagName("b")).getText());
				break;
			}
		}
		return prod;*/
	}
	
	public void addProductToCart(String ProdcutName) {
		WebElement prod=getProductByName(ProdcutName);
		prod.findElement(addToCart).click();
		waitforElementToVisible(toast);
		waitforElementToInvisible(spinner);
	}
	
}
