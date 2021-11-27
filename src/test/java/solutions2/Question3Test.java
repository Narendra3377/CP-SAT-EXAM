package solutions2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import utilities.TestBase;

public class Question3Test extends TestBase{
	
	@Before
	public void initiateDriver() throws InterruptedException {
		init("chrome", "https://exammay2020.agiletestingalliance.org/");
		waitForElementToVisible(d.findElement(By.className("eicon-close")));
		d.findElement(By.className("eicon-close")).click();
		checkPageReady();

	}

	@Test
	public void testCase1() throws Exception {
		
		W_MovetoElement(d.findElement(By.xpath("//ul[@id='menu-main-1']/li[3]/a")));
		d.findElement(By.xpath("//ul[@id='menu-main-1']//li[3]/ul/li[2]/a")).click();
		
		Question3POM obj=new Question3POM();
		System.out.println("Page Title: "+obj.GetPageTitle());
		
		obj.getSpeakerDetails("Masa K Maeda");
		obj.getSpeakerDetails("Khimanand Upreti");
		obj.getSpeakerDetails("Brijesh Deb");	

	}

	@After
	public void AfterTest() {
		terminate();
	}

}
