package solutions2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utilities.TestBase;

public class Question2 extends TestBase {

	int counter = 0;

	@BeforeMethod
	@Parameters("browser")
	public void initiateDriver(String browser) throws InterruptedException {
		init(browser, "https://exammay2020.agiletestingalliance.org/");
		waitForElementToVisible(d.findElement(By.className("eicon-close")));
		d.findElement(By.className("eicon-close")).click();
		checkPageReady();

	}

	@Test(dataProvider = "passData")
	public void f(String FullName, String Email, String PhoneNumber, String Remarks, String Status) throws Throwable {
		WebElement ele1 = d.findElement(By.xpath("//nav[@id='site-navigation']//a[contains(text(),'Forms')]"));
		W_MovetoElement(ele1);
		WebElement ele2 = d.findElement(By.xpath("//nav[@id='site-navigation']//a[contains(text(),'Form 1')]"));
		W_MovetoElement(ele2);
		ScreenShot("Question2", "Forms1");
		wait(2);
		jsClick(ele2);
		d.findElement(By.xpath("//input[@placeholder='Full Name']")).sendKeys(FullName);
		d.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys(Email);
		d.findElement(By.xpath("//input[@placeholder='Mobile Number']")).sendKeys(PhoneNumber);
		d.findElement(By.xpath("//textarea[@placeholder='Comment or Message']")).sendKeys(Remarks);
		d.findElement(By.xpath("//button[@type='submit']")).click();

		counter++;
		List<WebElement> errors = d.findElements(By.xpath("//label[contains(@id,'error')]"));
		setData(path, "Sheet1", counter, 0, FullName);
		setData(path, "Sheet1", counter, 1, Email);
		setData(path, "Sheet1", counter, 2, PhoneNumber);
		setData(path, "Sheet1", counter, 3, Remarks);
		if (errors.size() > 0) {

			setData(path, "Sheet1", counter, 4, "FAIL");

		} else {
			setData(path, "Sheet1", counter, 4, "PASS");
		}

	}

	@DataProvider
	public Object[][] passData() throws Exception {
		Object[][] data = readData(path, "Sheet1");
		return data;
	}

	@AfterMethod
	public void AfterTest() {
		terminate();
	}

}
