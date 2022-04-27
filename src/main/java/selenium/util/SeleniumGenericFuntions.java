package selenium.util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author saurabh_gupta
 * 
 *         This is generic helper class for selenium related functions
 *
 */
public class SeleniumGenericFuntions {

	static WebDriver driver;

	public SeleniumGenericFuntions(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * @param xpath
	 * @param value
	 */
	public void sendKeys(By xpath, String value) {
		driver.findElement(xpath).sendKeys(value);
	}

	/**
	 * @param xpath
	 */
	public void click(By xpath) {
		driver.findElement(xpath).click();
	}

	/**
	 * @param xpath
	 * @return
	 */
	public String getText(By xpath) {
		String xpathTxt;
		xpathTxt = driver.findElement(xpath).getText();
		return xpathTxt;
	}

	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void getUrl(String url) {
		driver.get(url);
	}

}
