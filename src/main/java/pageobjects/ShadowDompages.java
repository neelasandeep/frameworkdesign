package pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

import baseframework.ExtentFactory;
import uiactions.GenericActions;

public class ShadowDompages {
	private static Logger log = LogManager.getLogger();
	WebDriver driver;
	GenericActions genericActions;
	ExtentTest logger;

	public ShadowDompages(WebDriver driver) {
		logger = ExtentFactory.getInstance().getExtent();
		this.driver = driver;
		genericActions = new GenericActions();
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "body > shop-app")
	WebElement mainelement;

	public void multipleshadowElements() {
		logger.info("inside the method shadow");
		WebElement rootelement1 = getShadowDom(mainelement);
		WebElement subelement1 = rootelement1.findElement(By.tagName("iron-pages"));
		WebElement subelement2 = subelement1.findElement(By.tagName("shop-home"));
		WebElement rootelement2 = getShadowDom(subelement2);
		genericActions.click(rootelement2.findElement(By.cssSelector("div:nth-child(3)>shop-button>a")), "button");
		
		log.info("build succeded");
	}

	private WebElement getShadowDom(WebElement element) {
		logger.info("inside the method getshadow");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (WebElement) js.executeScript("return arguments[0].shadowRoot", element);
	}
}
