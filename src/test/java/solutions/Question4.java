package solutions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utilities.TestBase;

public class Question4 extends TestBase {

	@BeforeMethod
	@Parameters("browser")
	public void initiateDriver(String browser) throws InterruptedException {
		init(browser, "https://exammay2020.agiletestingalliance.org/news/");
		checkPageReady();

	}

	@Test
	public void test() throws Exception {

		int size = Integer
				.parseInt(d.findElement(By.xpath("//a[@class='page-numbers next']/preceding-sibling::a[1]")).getText());

		List<WebElement> hyperlinks = d.findElements(By.xpath("//a[@class='elementor-post__thumbnail__link']"));
		for (int i = 1; i < hyperlinks.size(); i++) {
			System.out.println(hyperlinks.get(i - 1).getAttribute("href"));
		}
		for (int j = 1; j < size; j++) {
			jsClick(d.findElement(By.xpath("//a[text()='Next »']")));
			hyperlinks = d.findElements(By.xpath("//a[@class='elementor-post__thumbnail__link']"));
			for (int i = 1; i < hyperlinks.size(); i++) {
				System.out.println(hyperlinks.get(i - 1).getAttribute("href"));
			}

		}
		jsClick(d.findElement(By.xpath("(//ul[@id='menu-main-1']/li/a[text()='Registration'])[1]")));
		String button = d.findElement(By.xpath("(//button[text()='Sold Out'])[1]")).getAttribute("class");
		if (button.contains("disabled")) {
			System.out.println("button 1 is not clickable");
		}
		button = d.findElement(By.xpath("(//button[text()='Sold Out'])[2]")).getAttribute("class");
		if (button.contains("disabled")) {
			System.out.println("button 2 is not clickable");
		}
		jsClick(d.findElement(By.xpath("(//button[text()='Register Now'])[1]")));
		d.switchTo().frame(d.findElement(By.xpath("//iframe[@id='tsiframe']")));

		int n = d.findElements(By.xpath("//div[@class='pill__item__text ng-binding']")).size();
		String a, b, c;
		System.out.println(n);
		for (int i = 1; i <= n; i++) {
			a = d.findElement(By.xpath("(//div[@class='pill__item__text ng-binding'])[" + i + "]")).getText();
			b = d.findElement(
					By.xpath("(//div[@class='pill__item__count--large-2']/span[@class='ng-binding'])[" + i + "]"))
					.getText();
			c = d.findElement(By.xpath("(//div[@class='pill__item__info fixed ng-binding text-danger'])[" + i + "]"))
					.getText();
			System.out.println(a + "|" + b + "|" + c);
		}
	}

	@AfterMethod
	public void AfterTest() {
		terminate();
	}

}
