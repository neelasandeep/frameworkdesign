package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShadowDompages {
	WebDriver driver;
	//Generic generic;

	public ShadowDompages(WebDriver driver) {

		this.driver = driver;
	//	generic = new Generic(driver);
		PageFactory.initElements(driver, this);
	}
	@FindBy(tagName="book-app")
	WebElement mainroot;
	
	public void findshadowelemet() {
		WebElement shadowroot = getShadowDom(mainroot);
		WebElement header=shadowroot.findElement(By.tagName("app-header"));
		WebElement header2=header.findElement(By.tagName("book-input-decorator"));
		header2.findElement(By.cssSelector("input#input")).sendKeys("testing");
		
	}
	public void multipleshadowElements() {
		WebElement mainelement=driver.findElement(By.tagName("shop-app"));
		
	    WebElement rootelement1=getShadowDom(mainelement);
	    WebElement subelement1=rootelement1.findElement(By.tagName("iron-pages"));
	    WebElement subelement2=subelement1.findElement(By.tagName("shop-home"));
	    WebElement rootelement2=getShadowDom(subelement2);
	   
	    rootelement2.findElement(By.cssSelector("div:nth-child(3)>shop-button>a")).click();
	    
	}

	private WebElement getShadowDom(WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		WebElement shadowroot=(WebElement)js.executeScript("return arguments[0].shadowRoot", element);
		return shadowroot;
	}
}
