package io.raveerocks.google;

import io.raveerocks.google.GoogleSearchTest;
import io.raveerocks.util.CapabilityBuilder;
import io.raveerocks.util.TestCase;
import io.raveerocks.util.TestCaseUtil;
import org.openqa.selenium.Capabilities;
import org.testng.annotations.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ExecutorThreadTest {

    private static final String TEST_PATH = "/opt/ravee/Automation Core/src/test/resources";
    File testFile;

    @BeforeSuite
    @Parameters({"test-data"})
    public void setUp(@Optional String testFile) {
        this.testFile = new File(TEST_PATH+"/"+testFile);
    }

    @Test(dataProvider = "dataProvider")
    private void test(List<GoogleSearchTest> googleSearchTests) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Set<Callable<String>> callables = googleSearchTests.stream()
                .map(googleSearchTest -> {
                    Callable<String> callable = () -> {
                        googleSearchTest.run();
                        return "Completed ";
                    };
                    return callable;
                })
                .collect(Collectors.toSet());

        List<Future<String>> futures = null;

        try {
            futures = executorService.invokeAll(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for ( Future<String> future : futures){
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    @DataProvider
    private Object[][] dataProvider(){
        List<TestCase> testCases = TestCaseUtil.parse(testFile, 0);
        Object[][] data = new Object[1][1];
        List<GoogleSearchTest> tests = new ArrayList<>();

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
            tests.add(new GoogleSearchTest(capabilities,testCase.getParams()[0],testCase.getExpectedResult()));

        }
        data[0][0] = tests;
        return data;
    }

}
