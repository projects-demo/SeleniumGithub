package github.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import selenium.util.CommonConfigurations;
import selenium.util.SeleniumGenericFuntions;
import selenium.util.Utils;

/**
 * @author saurabh_gupta
 * 
 *         LoginTestPage class
 */
public class LoginTestPage implements CommonConfigurations {
	WebDriver driver;
	static String GIT_URL = "https://github.com";
	static By XPATH_SIGNIN = By.xpath("//a[contains(text(),'Sign in')]");
	static By TXT_LOGIN = By.name("login");
	static By TXT_PASSWORD = By.name("password");
	static By BTN_COMMIT = By.name("commit");

	public SeleniumGenericFuntions genericFunctions;

	public LoginTestPage(WebDriver driver) {
		this.driver = driver;
		genericFunctions = new SeleniumGenericFuntions(driver);
	}

	/**
	 * case1 :: Navigate to the website <br>
	 * case8 :: Need of thread.sleep() functionality, in case of loading page or
	 * waiting for search results
	 */
	public void navigateToWebsite() throws InterruptedException {
		LOGGER.info("started navigateToWebsite ->" + GIT_URL);
		driver.get(GIT_URL);
		Thread.sleep(WAIT_DURATION_GENERIC);
	}

	/**
	 * case2 :: Login to the portal <br>
	 * case9: Exception handling in case webpage doesn’t load properly as expected
	 * 
	 */
	public void loginToApplication() throws Exception {
		String decryptedPassword = new Utils().getDecryptedPassword();

		try {
			genericFunctions.click(XPATH_SIGNIN);
		} catch (Exception e) {
			LOGGER.severe(e.getMessage());
		}

		genericFunctions.sendKeys(TXT_LOGIN, USER_NAME);
		genericFunctions.sendKeys(TXT_PASSWORD, decryptedPassword);

		Thread.sleep(WAIT_DURATION_GENERIC);
		genericFunctions.click(BTN_COMMIT);
	}

}
