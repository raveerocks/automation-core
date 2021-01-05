package io.raveerocks;

import io.raveerocks.driver.DriverUtil;
import io.raveerocks.driver.TestService;
import io.raveerocks.google.HomePage;
import io.raveerocks.services.GoogleService;
import io.raveerocks.thread.GoogleSearchTest;
import io.raveerocks.util.CapabilityBuilder;
import io.raveerocks.util.TestCase;
import io.raveerocks.util.TestCaseUtil;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GoogleServiceTest {

    private GoogleService googleService;

    @BeforeClass
    public void beforeClass(){
        googleService = GoogleService.getDefaultInstance();
    }


    @Test(dataProvider = "dataProvider")
    private void searchByEnterTest(Capabilities capabilities, String searchTerm,String expectedTitle){
        TestService testService = DriverUtil.getDriver(capabilities);
        WebDriver webDriver = testService.startTest(capabilities);
        HomePage homePage = googleService.searchByEnter(webDriver, searchTerm);
        Assert.assertEquals(homePage.getTitle(),expectedTitle);
        testService.endTest();
        homePage.getWebDriver().quit();
    }

    @Test(dataProvider = "dataProvider")
    private void searchByClickTest(Capabilities capabilities, String searchTerm, String expectedTitle){
        TestService testService = DriverUtil.getDriver(capabilities);
        WebDriver webDriver = testService.startTest(capabilities);
        HomePage homePage = googleService.searchBySubmit(webDriver, searchTerm);
        Assert.assertEquals(homePage.getTitle(),expectedTitle);
        homePage.getWebDriver().quit();
    }


    @Test(dataProvider = "dataProvider2")
    private void parallelThreadTest(List<GoogleSearchTest> googleSearchTests){
        List<Thread> testThreads = googleSearchTests.stream().map(test -> {
            Thread thread = new Thread(test);
            thread.start();
            return thread;
        }).collect(Collectors.toList());
        testThreads.stream().forEach(testThread -> {
            testThread.start();
            try {
                testThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Test(dataProvider = "dataProvider2")
    private void executorTest(List<GoogleSearchTest> googleSearchTests) {

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
            data[i][0] = capabilities;
            data[i][1] = testCase.getParams()[0];
            data[i][2] = testCase.getExpectedResult();
        }
        return data;
    }


    @DataProvider
    private Object[][] dataProvider2(){
        File file = new File("/opt/ravee/Automation Core/src/test/resources/Test Sheet.xlsx");
        List<TestCase> testCases = TestCaseUtil.parse(file, 0);
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
