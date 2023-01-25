package Listners;

import org.testng.ITestResult;

public class ExtentionUtil {
    public static TestStatus processStatus(ITestResult result){
        TestStatus status = null;
        if (result.getStatus() == ITestResult.SUCCESS)
            status = TestStatus.PASS;
        else if (result.getStatus() == ITestResult.FAILURE)
            status = TestStatus.FAIL;
        else
            status = TestStatus.SKIP;
        return  status;

    }

    public enum TestStatus {
        FAIL,SKIP,PASS;
    }
}

