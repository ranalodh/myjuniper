package com.ibm.testautomation.testrunner;

import cucumber.api.CucumberOptions;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
//import io.github.bonigarcia.wdm.WebDriverManager;
import cucumber.api.testng.*;


@RunWith(Cucumber.class)
@CucumberOptions (
        features = "src/test/java/Features/MyJuniper.feature"
        ,glue = {"com.ibm.testautomation.stepdef"}
        ,tags = {"@MyJuniper"}
        ) 
		
public class TestRunner {	
	
    private TestNGCucumberRunner testNGCucumberRunner;   
    
    /**
     * 
     * @throws Exception
     */
    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {  
    	//WebDriverManager.chromedriver().setup();  
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }
    /**
     * 
     * @param eventwrapper
     * @param cucumberFeature
     * @throws Throwable
     */
    @Test(dataProvider = "features")    
    public void feature(PickleEventWrapper eventwrapper,CucumberFeatureWrapper cucumberFeature) throws Throwable {
    	testNGCucumberRunner.runScenario(eventwrapper.getPickleEvent());
    	
    }
    /**
     * 
     * @return
     */
    @DataProvider//(parallel=true)
    public Object[][] features() {           	
    	 return testNGCucumberRunner.provideScenarios();
    }
    /**
     * 
     * @throws Exception
     */
    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {    	
        testNGCucumberRunner.finish();        
    }
 
}