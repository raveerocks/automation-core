package io.raveerocks;

import io.raveerocks.util.TestCase;
import io.raveerocks.util.TestCaseUtil;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

public class TestTestSheet {

    @Test
    public void  test(){

        File file = new File("/opt/ravee/Automation Core/src/test/resources/Test Cases.xlsx");
        List<TestCase> testCases = TestCaseUtil.parse(file, 0);
        System.out.println(testCases);
    }
}
