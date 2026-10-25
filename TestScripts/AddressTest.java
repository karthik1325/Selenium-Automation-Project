package testScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import generic_Utilities.BaseTest;

public class AddressTest extends BaseTest{
	
	
	@Test
	public void addToAddressTest() 
	{
		driver.findElement(By.linkText("Addresses")).click();
        driver.findElement(By.xpath("//input[@value='Add new']")).click();

        driver.findElement(By.id("Address_FirstName")).sendKeys("John");
        driver.findElement(By.id("Address_LastName")).sendKeys("David");
        driver.findElement(By.id("Address_Email")).sendKeys("john@gmail.com");

        Select country = new Select(driver.findElement(By.id("Address_CountryId")));
        country.selectByVisibleText("India");

        driver.findElement(By.id("Address_City")).sendKeys("Chennai");
        driver.findElement(By.id("Address_Address1")).sendKeys("Anna Nagar");
        driver.findElement(By.id("Address_ZipPostalCode")).sendKeys("600001");
        driver.findElement(By.id("Address_PhoneNumber")).sendKeys("9876543210");

        driver.findElement(By.xpath("//input[@value='Save']")).click();
		
	}
	
	@Test(priority =2,groups ="smoke",enabled = false)
	public void updateAddressTest() {
		
		driver.findElement(By.xpath("//input[@value='Edit']")).click();

        driver.findElement(By.id("Address_City")).clear();
        driver.findElement(By.id("Address_City")).sendKeys("Bangalore");

        driver.findElement(By.xpath("//input[@value='Save']")).click();
	}
	
	@Test(priority = 2)
	public void deleteAddressTest() {
		 driver.findElement(By.linkText("Addresses")).click();
		    driver.findElement(By.xpath("//input[@value='Delete']")).click();
	}
}
