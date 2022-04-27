package selenium.test;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import github.pages.LoginTestPage;
import github.pages.RepositoryTestPage;
import selenium.util.DriverHelper;

import static selenium.util.CommonConfigurations.LOGGER;

/**
 * @author saurabh_gupta
 *
 *         RepositoryTest class
 */
public class RepositoryTest {
	WebDriver driver;
	LoginTestPage loginTestPage;
	RepositoryTestPage repositoryTestPage;

	@BeforeMethod
	public void setUp() {
		new DriverHelper().initializeDriver();
		this.driver = DriverHelper.driver;
		loginTestPage = new LoginTestPage(DriverHelper.driver);
		repositoryTestPage = new RepositoryTestPage(DriverHelper.driver);
	}

	/**
	 * Case 3.1 :: Showcase interaction with the Static elements
	 */
	@Test
	public void test_getStaticElementSummary() throws Exception {
		loginTestPage.navigateToWebsite();
		loginTestPage.loginToApplication();
		List<WebElement> listOfStaticSummaryItems = repositoryTestPage.getStaticElementSummaryList();
		int cnt = 1;
		for (WebElement webElement : listOfStaticSummaryItems) {
			Reporter.log(cnt++ + ": Static Summary Item -> " + webElement.getText());
		}
	}

	/**
	 * Case 3.2 & Case 4 & Case 7.1:: Showcase interaction with the Dynamic elements
	 */
	@Test
	public void test_getDynamicElementRepositoryList() throws Exception {
		loginTestPage.navigateToWebsite();
		loginTestPage.loginToApplication();
		repositoryTestPage.getRepositoryCountUsingXpath();
		List<WebElement> listOfRepositories = repositoryTestPage.getDynamicElementRepositoryList();
		int cnt = 1;
		for (WebElement webElement : listOfRepositories) {
			Reporter.log(cnt++ + ": Dynamic Rep Item -> " + webElement.getText());
		}
	}

	/**
	 * //case5.1 and case6 :: Usage of locator types, whether to use CSS selector or
	 * Xpath How above variation effects performance of the testing
	 */
	@Test
	public void test_getRepositoryCountXpath() throws Exception {
		loginTestPage.navigateToWebsite();
		loginTestPage.loginToApplication();
		Instant start = Instant.now();
		String counter = repositoryTestPage.getRepositoryCountUsingXpath();
		Instant end = Instant.now();
		Duration elapedTime = Duration.between(start, end);
		Reporter.log("Total repository count = " + counter);
		Reporter.log("time elapsed " + elapedTime);
		LOGGER.info(elapedTime.toString());
	}

	/**
	 * //case5.2 and case6 :: Usage of locator types, whether to use CSS selector or
	 * Xpath How above variation effects performance of the testing
	 */
	@Test
	public void test_getRepositoryCountCssSelector() throws Exception {
		loginTestPage.navigateToWebsite();
		loginTestPage.loginToApplication();
		Instant start = Instant.now();
		String counter = repositoryTestPage.getRepositoryCountUsingCssSelector();
		Instant end = Instant.now();
		Duration elapedTime = Duration.between(start, end);
		Reporter.log("Total repository count = " + counter);
		Reporter.log("time elapsed " + elapedTime);
		LOGGER.info(elapedTime.toString());
	}

	/**
	 * Case 7.2 ::Usage of Dynamic Xpath or tags vs Static Xpath
	 */
	@Test
	public void test_getRepositoryCountXpathAbs() throws Exception {
		loginTestPage.navigateToWebsite();
		loginTestPage.loginToApplication();
		String counter = repositoryTestPage.getRepositoryCountUsingAbsXpath();
		Reporter.log("Total repository count = " + counter);
	}

	@Test
	public void test_createNewRepository() throws Exception {
		loginTestPage.navigateToWebsite();
		loginTestPage.loginToApplication();
		repositoryTestPage.getRepositoryCountUsingXpath();
		String counter = repositoryTestPage.createNewRepository();
		Reporter.log("Total repository count = " + counter);
	}

	@AfterMethod
	public void tearDown() {
		 driver.quit();
	}
}
