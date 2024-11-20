package Automation.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Automation.PageObjects.CartPage;
import Automation.PageObjects.LandingPage;
import Automation.PageObjects.OrderConfirmationPage;
import Automation.PageObjects.OrdersPage;
import Automation.PageObjects.PaymentPage;
import Automation.PageObjects.ProductCatalougePage;
import Automation.TestComponents.BaseTests;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrder extends BaseTests{
	//public String ProdcutName ="ADIDAS ORIGINAL";
	
	@Test(dataProvider="getdata")
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		ProductCatalougePage productCatalougePage =landingPage.loginToApplication(input.get("email"),input.get("password"));
		//List<WebElement> products=productCatalougePage.getTheListOfProducts();
		productCatalougePage.addProductToCart(input.get("productName"));
		CartPage cartPage=productCatalougePage.clickCartButton();
		cartPage.getProductsAddedInCart(input.get("productName"));
		Assert.assertTrue(cartPage.getProductsAddedInCart(input.get("productName")));
		PaymentPage paymentPage=cartPage.clickCheckOutButton();
		paymentPage.selectCountry("ind");
		OrderConfirmationPage orderConfirmationPage=paymentPage.clickSubmitButton();
		Assert.assertTrue(orderConfirmationPage.orderConfirmationMeaasge().equalsIgnoreCase("Thankyou for the order."));
				
		//driver.close();

	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistry() {
		String ProdcutName ="ZARA COAT 3";
		ProductCatalougePage productCatalougePage =landingPage.loginToApplication("mj240@gmail.com","17BmD0090@");
		OrdersPage ordersPage =productCatalougePage.clickOrdersButton();
		boolean match=ordersPage.getOrderedProducts(ProdcutName);
		Assert.assertTrue(match);
	}
	
	/*@DataProvider
	public Object[][] getdata(){
		return new Object[][]{{"mj240@gmail.com","17BmD0090@","ZARA COAT 3"},{"mj241@gmail.com","17BmD0090@","ADIDAS ORIGINAL"}};
	}*/
	@DataProvider
	public Object[][] getdata() throws IOException{
		/*
		 * HashMap<String,String> map=new HashMap<String,String>(); map.put("email",
		 * "mj240@gmail.com"); map.put("password", "17BmD0090@");
		 * map.put("productName","ZARA COAT 3"); HashMap<String,String> map1=new
		 * HashMap<String,String>(); map1.put("email", "mj241@gmail.com");
		 * map1.put("password", "17BmD0090@");
		 * map1.put("productName","ADIDAS ORIGINAL");
		 * return new Object[][]{{map1},{map2}};
		 */
		
		
		List<HashMap<String, String>> data= getJsonDataToHashMap(System.getProperty("user.dir")+"//src//test//java//Automation//Data//PurchaseOrder.json");
		return new Object[][]{{data.get(0)},{data.get(1)}};
		
	}

}
