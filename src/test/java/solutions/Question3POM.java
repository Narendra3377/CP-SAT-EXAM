package solutions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import utilities.TestBase;

public class Question3POM extends TestBase {

	public Question3POM() {
		PageFactory.initElements(d, this);
	}

	By SpeakerNames = By.xpath("//table[@data-id='66af78a']//tr/td[2]");
	By SpeakerTopic = By.xpath("//table[@data-id='66af78a']//tr/td[3]");
	By SpeakerStory = By.xpath("//table[@data-id='66af78a']//tr/td[4]/a");
	By SpeakerInterview = By.xpath("//table[@data-id='66af78a']//tr/td[5]/a");

	public void getSpeakerDetails(String speakerName) {
		int counter = 1;

		String speakTopic = "";
		String SpeakStory = "";
		List<WebElement> spkNames = d.findElements(SpeakerNames);
		int videosize = 0;
		String videoInterview = "";
		for (WebElement name : spkNames) {
			if (name.getText().equalsIgnoreCase(speakerName)) {
				speakTopic = d.findElements(SpeakerTopic).get(counter - 1).getText();
				SpeakStory = d.findElements(SpeakerStory).get(counter - 1).getAttribute("href");
				try {
					videosize = d.findElements(By.xpath("//table[@data-id='66af78a']//tr[' + counter + ']/td[5]/a"))
							.size();
				} catch (Exception e) {
					System.out.println("Video link is not found");
					videosize = 0;

				}

			}
			if (videosize != 0) {

				videoInterview = d.findElement(By.xpath("//table[@data-id='66af78a']//tr[' + counter + ']/td[5]/a"))
						.getAttribute("href");
			}
			counter++;
		}

		if (speakTopic == "") {
			System.out.println("Speaker not present");
		} else {
			System.out.println(
					"Topic: " + speakTopic + " | ATAMyStory: " + SpeakStory + " | Video Interview :" + videoInterview);
		}

	}

	public String GetPageTitle() {
		return d.getTitle();

	}

}
