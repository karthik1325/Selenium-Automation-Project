package testScripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import generic_Utilities.BaseTest;

public class PurchaseTest extends BaseTest {

	@Test(priority = 1,groups = "integration")
	public void purchaseProductViaCOD() throws InterruptedException {
		driver.findElement(By.name("q")).sendKeys("laptop", Keys.ENTER);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath("//input[@value='Add to cart']")).click();

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("bar-notification success")));
		Thread.sleep(2000);

        
        driver.findElement(By.linkText("Shopping cart")).click();

       
        driver.findElement(By.id("termsofservice")).click();

        
        driver.findElement(By.id("checkout")).click();

        System.out.println("COD flow executed");
	}
	
	@Test(priority = 2,groups = "system")
	public void purchaseProductViaCC() throws InterruptedException {
		driver.findElement(By.name("q")).sendKeys("laptop", Keys.ENTER);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.xpath("//input[@value='Add to cart']")).click();

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("bar-notification success")));
		Thread.sleep(2000);

        
        driver.findElement(By.linkText("Shopping cart")).click();

       
        driver.findElement(By.id("termsofservice")).click();

       
        driver.findElement(By.id("checkout")).click();

        System.out.println("CC flow executed");

	}
}
