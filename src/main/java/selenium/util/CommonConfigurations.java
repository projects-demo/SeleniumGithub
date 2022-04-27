package selenium.util;

import java.util.logging.Logger;

/**
 * @author saurabh_gupta
 * 
 *         CommonConfigurations class
 */
public interface CommonConfigurations {
	static String USER_NAME = "jdk-world";
	static String USER_ENCRYPTED_PASSWORD_TEXT = "WjjgrSn5HyoUmvfFKbj5iw==";
	static String SECRET_FILE_NAME = "secret.ser";
	static String REPOSITORY_NAME_PREFIX = "test-";
	static long WAIT_DURATION_GENERIC = 1000;

	static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
}
