package com.jayanga.osgi;

import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;
import org.ops4j.pax.exam.testng.listener.PaxExam;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.wso2.carbon.osgi.test.util.CarbonOSGiTestEnvConfigs;
import org.wso2.carbon.osgi.test.util.utils.CarbonOSGiTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by jayanga on 3/3/16.
 */
@Listeners(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class RandomTest {
    @Configuration
    public Option[] createConfiguration() {
        List<Option> customOptions = new ArrayList<>();
        CarbonOSGiTestEnvConfigs configs = new CarbonOSGiTestEnvConfigs();
        configs.setCarbonHome("/home/jayanga/WSO2/Training/c5-sample-osgi-test/target/carbon-home");
        return CarbonOSGiTestUtils.getAllPaxOptions(configs, customOptions);
    }

    @Test
    public void testTest1() throws Exception {
        assertEquals("Jaya", "Jaya", "Jaya != Jaya");
    }
}
