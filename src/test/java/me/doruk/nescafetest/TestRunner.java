package me.doruk.nescafetest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.junit.platform.engine.Constants;
import me.doruk.nescafetest.base.TestBase;
import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@ConfigurationParameters({
        @ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty,html:target/cucumber-reports/Cucumber.html"),
        @ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "me.doruk.nescafetest"),
        @ConfigurationParameter(key = Constants.FEATURES_PROPERTY_NAME, value = "classpath:features")
})
public class TestRunner {

    @Before
    public void before(Scenario scenario) {
        System.out.println("Senaryo başlatılıyor: " + scenario.getName());
        System.out.println("Tarayıcı başlatılıyor...");
        TestBase.setDriver();
        System.out.println("Tarayıcı başlatıldı.");
    }

    @After
    public void after(Scenario scenario) {
        System.out.println("Senaryo kapatılıyor: " + scenario.getName());
        System.out.println("Tarayıcı kapatılıyor...");
        TestBase.destroyDriver();
        System.out.println("Tarayıcı kapatıldı.");
    }
}
