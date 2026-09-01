package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

    private int count = 0;
    private static final int maxRetry = 3;

    @Override
    public boolean retry(ITestResult result) {
        if (count < maxRetry) {
            count++;
            System.out.println("Retrying " + result.getName() + " again: Attempt " + count);
            return true; // Retry the test
        }
        return false; // No more retries
    }
}