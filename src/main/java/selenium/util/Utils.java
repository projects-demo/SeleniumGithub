package selenium.util;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;

import static selenium.util.CommonConfigurations.LOGGER;

/**
 * @author saurabh_gupta
 *
 *         Generic Util service class
 */
public class Utils {

	static By XPATH_SUMMARY = By.xpath("//summary//img[@alt='@jdk-world']");
	static By XPATH_REPOSITORIES = By.xpath("//a[contains(text(),'Your repositories')]");
	static By XPATH_REPOSITORIES_COUNTER = By.xpath("(//a[@aria-current='page']//span)[1]");
	static By XPATH_NEW_REPOSITORY = By.xpath("//form//following-sibling::div//a[contains(@href,'new')]");
	static By XPATH_NEW_REPOSITORY_SUBMIT = By.xpath("//button[contains(text(),'Create repository')]");
	static By XPATH_REPOSITORIES_LIST = By.xpath("//a[@itemprop='name codeRepository']");

	public String getRandomString() {
		int length = 6;
		boolean useLetters = true;
		boolean useNumbers = false;
		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
		return generatedString.toLowerCase();
	}

	public String getDecryptedPassword() throws Exception {
		String decryptedText = decrypt(CommonConfigurations.USER_ENCRYPTED_PASSWORD_TEXT, getSecretKeyFromFile());
		return decryptedText;
	}

	private SecretKey getSecretKeyFromFile() {
		ObjectInputStream ois = null;
		SecretKey secretKey = null;
		try {
			FileInputStream fis = new FileInputStream("secret.ser");
			ois = new ObjectInputStream(fis);
			secretKey = (SecretKey) ois.readObject();
		} catch (Exception e) {
			LOGGER.severe(e.toString());
		}
		return secretKey;
	}

	public String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] encryptedTextByte = decoder.decode(encryptedText);
		Cipher cipher = Cipher.getInstance("AES"); // SunJCE provider AES algorithm, mode(optional) and padding
													// schema(optional)

		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
		String decryptedText = new String(decryptedByte);
		return decryptedText;
	}

}
