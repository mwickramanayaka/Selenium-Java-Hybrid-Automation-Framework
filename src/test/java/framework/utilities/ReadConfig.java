/**
 * 
 */
package framework.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author manul.wickramanayaka
 *
 */
public class ReadConfig {

	Properties pro;

	public ReadConfig() {

		File src = new File("./Configuration/config.properties");

		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
			
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		
		}
	}

	public String getApplicationURL() {

		String url=pro.getProperty("baseURL");
		return url;
	}

	public String getUserName() {

		String userName=pro.getProperty("userName");
		return userName;
	}

	public String getPassword() {

		String password=pro.getProperty("password");
		return password;
	}

	public String getChromePath() {

		String chromePath=pro.getProperty("chromePath");
		return chromePath;
	}

	public String getEdgePath() {

		String edgePath=pro.getProperty("edgePath");
		return edgePath;
	}

	public String getFirefoxPath() {

		String firefoxPath=pro.getProperty("firefoxPath");
		return firefoxPath;
	}
	
	public String getOperaPath() {

		String operaPath=pro.getProperty("operaPath");
		return operaPath;
	}

}
