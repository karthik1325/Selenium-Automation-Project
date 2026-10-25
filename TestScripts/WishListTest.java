package testScripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic_Utilities.BaseTest;
import generic_Utilities.File_Utility;

public class WishListTest extends BaseTest {

	@Test(priority = 1, groups = { "smoke", "functional" })
	public void addToWishList() {

		driver.findElement(By.linkText("Gift Cards")).click();

		driver.findElement(By.linkText("$25 Virtual Gift Card")).click();

		driver.findElement(By.id("giftcard_2_RecipientName")).sendKeys("Test User");
		driver.findElement(By.id("giftcard_2_RecipientEmail")).sendKeys("test@gmail.com");

		driver.findElement(By.id("add-to-wishlist-button-2")).click();

		Reporter.log("\t\tAdded into wishlist Successfully...", true);

	}

	@Test(priority = 2, dependsOnMethods = "addToWishList", groups = "functional")
	public void updateProductCountTest() {

		driver.findElement(By.linkText("Wishlist")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement qty = driver.findElement(By.className("qty-input"));
		qty.clear();
		qty.sendKeys("3");

		driver.findElement(By.name("updatecart")).click();
		WebElement updatedQty = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("qty-input")));

		String value = updatedQty.getAttribute("value");

		Assert.assertEquals(value, "3", "Quantity not updated");

		Reporter.log("\t\tWishlist product count updated successfully...", true);

	}

	@Test(priority = 3, dependsOnMethods = "addToWishList", groups = "adhoc")
	public void removeFromewishlistTest() {

		driver.findElement(By.linkText("Wishlist")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement removeCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.name("removefromcart")));
		removeCheckbox.click();

		driver.findElement(By.name("updatecart")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("wishlist-content")));
		String text = driver.findElement(By.className("wishlist-content")).getText();

		Assert.assertTrue(text.contains("empty"), "Product not removed from wishlist");

		Reporter.log("\t\tRemoved from wishlist successfully...", true);

	}

	@Test(priority = 4, enabled = false)
	public void addCartFromwishlisTest() throws InterruptedException {

		driver.findElement(By.cssSelector("input[value='Add to wishlist']")).click();

		Thread.sleep(2000);

		driver.findElement(By.linkText("Wishlist")).click();

		driver.findElement(By.cssSelector("input[name^='addtocart']")).click();

		driver.findElement(By.name("addtocartbutton")).click();

		Thread.sleep(2000);

		String qty = driver.findElement(By.className("cart-qty")).getText();

		Assert.assertTrue(qty.contains("("));

		Reporter.log("\t\tProduct moved from Wishlist to Cart successfully...", true);

	}

}
