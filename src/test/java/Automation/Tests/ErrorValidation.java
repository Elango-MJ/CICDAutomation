package Automation.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Automation.PageObjects.CartPage;
import Automation.PageObjects.ProductCatalougePage;
import Automation.TestComponents.BaseTests;
import Automation.TestComponents.Retry;

public class ErrorValidation extends BaseTests {
	@Test(groups= {"ErrorHandeling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() {
		landingPage.loginToApplication("mj240@gmail.com","17BmD");
		Assert.assertEquals("Incorrect email or pass.",landingPage.getLoginErrorMessage());
	}
	
	@Test
	public void productErrorValidation() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		String ProdcutName ="ZARA COAT 3";
		ProductCatalougePage productCatalougePage =landingPage.loginToApplication("mj240@gmail.com","17BmD0090@");
		productCatalougePage.addProductToCart(ProdcutName);
		CartPage cartPage=productCatalougePage.clickCartButton();
		cartPage.getProductsAddedInCart(ProdcutName);
		Assert.assertFalse(cartPage.getProductsAddedInCart("ZARA COAT 332"));
	}

}
