package selenium.test;

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
 *         LoginTest class
 */
public class LoginTest {
	LoginTestPage loginTest;
	RepositoryTestPage repositoryTestPage;

	@BeforeMethod
	public void setUp() {
		new DriverHelper().initializeDriver();
		loginTest = new LoginTestPage(DriverHelper.driver);
		repositoryTestPage = new RepositoryTestPage(DriverHelper.driver);
	}

	// case 1 :: Navigate to the WebSite
	@Test
	public void test_navigateToWebsite() throws InterruptedException {
		loginTest.navigateToWebsite();
	}

	// case 2 :: Login to the portal
	@Test
	public void test_loginToApplication() throws Exception {
		loginTest.navigateToWebsite();
		loginTest.loginToApplication();
		LOGGER.info("Application logged in successfully!");
	}

	@AfterMethod
	public void tearDown() {
		DriverHelper.driver.quit();
	}
}
