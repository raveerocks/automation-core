package io.raveerocks;

import io.raveerocks.thread.GoogleSearchTest;
import io.raveerocks.util.CapabilityBuilder;
import io.raveerocks.util.TestCase;
import io.raveerocks.util.TestCaseUtil;
import org.openqa.selenium.Capabilities;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

import java.util.List;
import java.util.Map;

public class SingleTest {

    @Test(dataProvider = "dataProvider")
    public void test(Capabilities capabilities, String searchTerm, String expectedTitle){
        Map<String, String> getenv = System.getenv();
        for (String key : getenv.keySet()){
            System.out.println("Property : "+key+" : "+getenv.get(key));
        }

        GoogleSearchTest googleSearchTest = new GoogleSearchTest(capabilities, searchTerm, expectedTitle);
        googleSearchTest.run();
    }

    @DataProvider
    private Object[][] dataProvider(){
        File file = new File("/opt/ravee/Automation Core/src/test/resources/Test Sheet.xlsx");
        List<TestCase> testCases = TestCaseUtil.parse(file, 0);
        Object[][] data = new Object[testCases.size()][3];

        for (int i=0; i<testCases.size(); i++){
            TestCase testCase = testCases.get(i);
            Capabilities capabilities = new CapabilityBuilder()
                    .setTestServiceProvider(testCase.getTestServiceProvider())
                    .setOperatingSystem(testCase.getOperatingSystem())
                    .setOperatingSystemVersion(testCase.getOperatingSystemVersion())
                    .setResolution(testCase.getResolution())
                    .setBrowser(testCase.getBrowser())
                    .setBrowserVersion(testCase.getBrowserVersion())
                    .setBuild(testCase.getBuild())
                    .setName(testCase.getName())
                    .setImplicitWaitTime(testCase.getImplicitWaitTime())
                    .setHeadLess(testCase.getHeadLess())
                    .build();
            data[i][0] =capabilities;
            data[i][1] = testCase.getParams()[0];
            data[i][2] = testCase.getExpectedResult();
        }
        return data;
    }
}
