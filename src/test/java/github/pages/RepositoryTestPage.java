package github.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.util.CommonConfigurations;
import selenium.util.SeleniumGenericFuntions;
import selenium.util.Utils;

/**
 * @author saurabh_gupta
 * 
 *         RepositoryTestPage class
 *
 */
public class RepositoryTestPage implements CommonConfigurations {
	WebDriver driver;
	private SeleniumGenericFuntions genericFunctions;

	static By XPATH_SUMMARY = By.xpath("//summary//img[@alt='@" + USER_NAME + "']");
	static By CSS_LOCATOR_SUMMARY = By.cssSelector("summary img[alt='@" + USER_NAME + "']");

	static By XPATH_STATIC_ELEMENT_SUMMARY = By.xpath("//div[@class='Header-item position-relative mr-0 d-none d-md-flex']//details-menu/a");
	static By XPATH_REPOSITORIES = By.xpath("//a[contains(text(),'Your repositories')]");
	static By CSS_LOCATOR_REPOSITORIES = By
			.cssSelector("a.dropdown-item[data-ga-click=\"Header, go to repositories, text:your repositories\"] ");
	
	static By XPATH_ABS_REPOSITORIES_COUNTER = By.xpath("/html/body/div[5]/main/div[1]/div/div/div[2]/div/nav/a[2]/span");
	static By XPATH_REPOSITORIES_COUNTER = By.xpath("(//a[@aria-current='page']//span)[1]");
	static By CSS_LOCATOR__REPOSITORIES_COUNTER = By.cssSelector("div.mt-4 a[aria-current='page'] span");

	static By XPATH_NEW_REPOSITORY = By.xpath("//form//following-sibling::div//a[contains(@href,'new')]");
	static By XPATH_NEW_REPOSITORY_SUBMIT = By.xpath("//button[contains(text(),'Create repository')]");
	static By XPATH_REPOSITORIES_LIST = By.xpath("//a[@itemprop='name codeRepository']");
	static By XPATH_REPOSITORY_NAME = By.id("repository_name");

	public RepositoryTestPage(WebDriver driver) {
		this.driver = driver;
		genericFunctions = new SeleniumGenericFuntions(driver);
	}

	public String getRepositoryCountUsingXpath() throws Exception {
		genericFunctions.click(XPATH_SUMMARY);
		Thread.sleep(WAIT_DURATION_GENERIC);
		genericFunctions.click(XPATH_REPOSITORIES);
		String counter = genericFunctions.getText(XPATH_REPOSITORIES_COUNTER);
		return counter;
	}

	public String getRepositoryCountUsingCssSelector() throws Exception {
		genericFunctions.click(CSS_LOCATOR_SUMMARY);
		Thread.sleep(WAIT_DURATION_GENERIC);
		genericFunctions.click(CSS_LOCATOR_REPOSITORIES);
		String counter = genericFunctions.getText(CSS_LOCATOR__REPOSITORIES_COUNTER);
		return counter;
	}

	public String getRepositoryCountUsingAbsXpath() throws Exception {
		genericFunctions.click(XPATH_SUMMARY);
		Thread.sleep(WAIT_DURATION_GENERIC);
		genericFunctions.click(XPATH_REPOSITORIES);
		String counter = genericFunctions.getText(XPATH_ABS_REPOSITORIES_COUNTER);
		return counter;
	}

	public String createNewRepository() throws Exception {
		genericFunctions.click(XPATH_SUMMARY);
		Thread.sleep(WAIT_DURATION_GENERIC);
		genericFunctions.click(XPATH_REPOSITORIES);
		Thread.sleep(WAIT_DURATION_GENERIC);
		genericFunctions.click(XPATH_NEW_REPOSITORY);
		String newRepositoryName = REPOSITORY_NAME_PREFIX + new Utils().getRandomString();

		genericFunctions.sendKeys(XPATH_REPOSITORY_NAME, newRepositoryName);

		Thread.sleep(WAIT_DURATION_GENERIC);
		genericFunctions.click(XPATH_NEW_REPOSITORY_SUBMIT);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//Strong//a[contains(@href,'" + USER_NAME + "/" + newRepositoryName + "')]")));

		genericFunctions.click(XPATH_SUMMARY);
		genericFunctions.click(XPATH_REPOSITORIES);

		Thread.sleep(WAIT_DURATION_GENERIC);
		driver.navigate().refresh();
		String counter = genericFunctions.getText(XPATH_REPOSITORIES_COUNTER);
		return counter;
	}

	public List<WebElement> getDynamicElementRepositoryList() throws Exception {
		genericFunctions.click(XPATH_SUMMARY);
		genericFunctions.click(XPATH_REPOSITORIES);
		List<WebElement> listOfRepositories = driver.findElements(XPATH_REPOSITORIES_LIST);
		return listOfRepositories;
	}
	
	public List<WebElement> getStaticElementSummaryList() throws Exception {
		genericFunctions.click(XPATH_SUMMARY);
		List<WebElement> listOfStaticSummaryItems = driver.findElements(XPATH_STATIC_ELEMENT_SUMMARY);
		return listOfStaticSummaryItems;
	}	
}
