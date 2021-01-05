package io.raveerocks.google;

import io.raveerocks.util.CapabilityBuilder;
import io.raveerocks.util.TestCase;
import io.raveerocks.util.TestCaseUtil;
import org.openqa.selenium.Capabilities;
import org.testng.annotations.*;

import java.io.File;
import java.util.List;

public class SingleTest {

    private static final String TEST_PATH = "/opt/ravee/Automation Core/src/test/resources";
    File testFile;

    @BeforeSuite
    @Parameters({"test-data"})
    public void setUp(@Optional String testFile) {
        this.testFile = new File(TEST_PATH + "/" + testFile);
    }

    @Test(dataProvider = "dataProvider")
    public void test(Capabilities capabilities, String searchTerm, String expectedTitle) {
        GoogleSearchTest googleSearchTest = new GoogleSearchTest(capabilities, searchTerm, expectedTitle);
        googleSearchTest.run();
    }

    @DataProvider
    private Object[][] dataProvider() {
        List<TestCase> testCases = TestCaseUtil.parse(testFile, 0);
        Object[][] data = new Object[testCases.size()][3];

        for (int i = 0; i < testCases.size(); i++) {
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
            data[i][0] = capabilities;
            data[i][1] = testCase.getParams()[0];
            data[i][2] = testCase.getExpectedResult();
        }
        return data;
    }
}
