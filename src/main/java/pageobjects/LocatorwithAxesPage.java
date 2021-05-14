package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LocatorwithAxesPage {
	WebDriver driver;
	

	public LocatorwithAxesPage(WebDriver driver) {

		this.driver = driver;
		
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(xpath = "//label[text()='Email']/following-sibling::input[1]")
	WebElement email;
	@FindBy(xpath = "//div[@class='container']/child::label")
	List<WebElement> labelsusingchild;
	@FindBy(xpath = "//td[contains(text(),'Maria')]/preceding-sibling::td/input[@type='checkbox']")
	WebElement precedingchild;
	@FindBy(xpath = "//div[@class='container']/descendant::button")
	List<WebElement> descendantchild;

	public void checkfollowingsiblingbyemailExample() {

		
		email.sendKeys("abc@gamil.com");

	}

	public int labelscountusingchildMethod() {
	
		return labelsusingchild.size();
	}

	public void precedingsiblingcheckbox() {
	
		precedingchild.click();
	}

	public void descendantchildExample() {
	
		descendantchild.size();
	}

}
