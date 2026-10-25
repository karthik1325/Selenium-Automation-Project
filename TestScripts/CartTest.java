package testScripts;

import org.testng.annotations.Test;

import generic_Utilities.BaseTest;

import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;

import java.awt.RenderingHints.Key;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

	// WebDriver driver;
	@Test(priority = 1,groups = {"smoke","functional"})
	public void addToCartTest() throws InterruptedException {



		driver.findElement(By.name("q")).sendKeys("laptop", Keys.ENTER);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath("//input[@value='Add to cart']")).click();

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("bar-notification success")));
		driver.findElement(By.linkText("Shopping cart")).click();

		String quantity = driver.findElement(By.xpath("//span[@class='cart-qty']")).getText();
		String quantityNumber = quantity.replace("(", "").replace(")", "");

		Assert.assertEquals(Integer.parseInt(quantityNumber) > 0, true, "Product not added to cart");
		Reporter.log("\t\tAdd into cart Successfully...", true);

	}

	@Test(priority = 2,groups = "integration")
	public void updateProductCountTest() {

		

		driver.findElement(By.linkText("Shopping cart")).click();
		WebElement quantity = driver.findElement(By.xpath("//input[contains(@name,'itemquantity')]"));
		quantity.clear();
		quantity.sendKeys("5");
		Assert.assertEquals(quantity.getAttribute("value"), "5", "product count mismatch");
		driver.findElement(By.name("updatecart")).click();
		Reporter.log("\t\tProduct count Updated Successfully... ", true);

	}

	@Test(priority = 3,groups = "adhoc")
	public void removeFromCartTest() {

		driver.findElement(By.linkText("Shopping cart")).click();
		driver.findElement(By.cssSelector("input[name^='removefromcart']")).click();
		driver.findElement(By.name("updatecart")).click();
		try {
	        Thread.sleep(2000);
	    } catch (Exception e) {}

	   
	    String text = driver.findElement(By.cssSelector(".order-summary-content")).getText();
	    System.out.println("ACTUAL TEXT: " + text); 

	    Assert.assertTrue(text.contains("empty"));

	    Reporter.log("\t\t Product removed successfully...", true);
	
	}

}
