package solutions;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utilities.TestBase;

public class Question1 extends TestBase {

	@BeforeMethod
	@Parameters("browser")
	public void initiateDriver(String browser) throws InterruptedException {
		init(browser, "https://exammay2020.agiletestingalliance.org/");
		waitForElementToVisible(d.findElement(By.className("eicon-close")));
		d.findElement(By.className("eicon-close")).click();
		checkPageReady();

	}

	@Test
	public void testCase1() throws Exception {
		d.findElement(By.xpath("//div[@id='primary-menu']//ul//li//a[text()='Venue'][1]")).click();
		checkPageReady();
		WebElement mailFromBody = d.findElement(By.xpath("//span[contains(text(),'EMAIL US')]//parent::h3//following-sibling::p//a"));
		String email_Body = mailFromBody.getText().trim();
		WebElement phoneFromBody = d.findElement(By.xpath("//span[contains(text(),'CALL US')]//parent::h3//following-sibling::p//a"));
		String phone_Body = phoneFromBody.getText().trim();
		System.out.println("Email :" + email_Body + "Phone Number : " + phone_Body);
		String temp = phone_Body.replace("+91-", "").trim();
		long phoneDigits = Long.parseLong(temp);

		System.out.println("Phone Number after converting into Integer " + phoneDigits);
		WebElement footer = d.findElement(By.xpath("//div[@class='inside-footer-widgets']"));
		scrollIntoView(footer);
		WebElement foo_phone = d.findElement(By.xpath("(//div[@class='info_area']//p)[1]"));
		long foo_PhoneDigits = Long.parseLong(foo_phone.getText().replace("+91 ", ""));
		assertEquals(phoneDigits, foo_PhoneDigits);
		WebElement foo_email = d.findElement(By.xpath("(//div[@class='info_area']//p)[2]"));
		String foo_eamilString = foo_email.getText().trim();
		assertEquals(foo_eamilString, email_Body);
		List<WebElement> socialLinks = d.findElements(By.xpath("//div[@class='social_icon']//a"));
		String parentWindow = d.getWindowHandle();
		for (WebElement sicon : socialLinks) {
			System.out.println(sicon.getAttribute("href"));
			sicon.click();

			Set<String> childwindow = d.getWindowHandles();
			Iterator<String> winItr = childwindow.iterator();
			while (winItr.hasNext()) {
				String chid = winItr.next();
				if (!chid.equals(parentWindow)) {
					d.switchTo().window(chid);
					checkPageReady();
					System.out.println("Page Title is: " + d.getTitle());
					d.close();
				}

				d.switchTo().window(parentWindow);

			}

		}
		
		
		
	}

	@AfterMethod
	public void AfterTest() {
		terminate();
	}

}
