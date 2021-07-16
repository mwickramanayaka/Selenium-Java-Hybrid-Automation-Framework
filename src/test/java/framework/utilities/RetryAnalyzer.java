/**
 * 
 */
package framework.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * @author manul.wickramanayaka
 *
 */

public class RetryAnalyzer implements IRetryAnalyzer {

	private int retryCount = 0;
	private static final int maxRetryCount = 3;

	public boolean retry(ITestResult result) {
		if (retryCount < maxRetryCount) {
			retryCount++;
			return true;
		}
		return false;
	}
}


