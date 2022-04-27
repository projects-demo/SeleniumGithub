package selenium.util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author saurabh_gupta
 *
 *         DriverHelper class
 */
public class DriverHelper {
	public static WebDriver driver;
	static String DRIVER_CHROME_LOCATION = "C:\\software\\chromedriver.exe";
	static String DRIVER_FIREFOX_LOCATION = "C:\\software\\geckodriver.exe";
	static String DRIVER_IE_LOCATION = "C:\\software\\IEDriverServer.exe";

	public void initializeDriver() {
		System.setProperty("webdriver.chrome.driver", DRIVER_CHROME_LOCATION);
		driver = new ChromeDriver();
		// System.setProperty("webdriver.gecko.driver", DRIVER_FIREFOX_LOCATION);
		// driver = new FirefoxDriver();
		// System.setProperty("webdriver.ie.driver", DRIVER_IE_LOCATION);
		// driver = new InternetExplorerDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

}
