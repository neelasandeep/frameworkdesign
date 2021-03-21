package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonUIActions.GenericActions;

public class ShadowDompages {
	WebDriver driver;
	GenericActions genericActions;

	public ShadowDompages(WebDriver driver) {

		this.driver = driver;
		genericActions = new GenericActions();
		PageFactory.initElements(driver, this);
	}

	@FindBy(tagName = "shop-app")
	WebElement mainelement;

	public void multipleshadowElements() {
		WebElement rootelement1 = getShadowDom(mainelement);
		WebElement subelement1 = rootelement1.findElement(By.tagName("iron-pages"));
		WebElement subelement2 = subelement1.findElement(By.tagName("shop-home"));
		WebElement rootelement2 = getShadowDom(subelement2);
		genericActions.click(rootelement2.findElement(By.cssSelector("div:nth-child(3)>shop-button>a")),"button");
		// rootelement2.findElement(By.cssSelector("div:nth-child(3)>shop-button>a")).click();

	}

	private WebElement getShadowDom(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement shadowroot = (WebElement) js.executeScript("return arguments[0].shadowRoot", element);
		return shadowroot;
	}
}
