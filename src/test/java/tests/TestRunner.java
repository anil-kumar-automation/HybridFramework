package tests;



import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

import static utils.ExcelUtilsDynamicTest.getTestData;

public class TestRunner {
    @DataProvider(name = "TestData")
    public Object[][] getData() throws IOException {
        Object[][] data = getTestData("C:\\Users\\SK66921\\Documents\\Git\\HybridFramework\\src\\test\\resources\\TestData\\DynamicTests.xlsx",
                "Sheet1");
        return data;
    }

    @Test(dataProvider = "TestData")
    public void runTest(String className) throws Exception {
        Class<?> cls = Class.forName(className);
        Object obj = cls.newInstance();
        Method method = cls.getMethod("runTest", String.class);
        method.invoke(obj);
    }
}

