package com.jayanga.osgi;

import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;
import org.ops4j.pax.exam.testng.listener.PaxExam;
import org.osgi.service.jndi.JNDIContextManager;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.wso2.carbon.osgi.test.util.CarbonOSGiTestEnvConfigs;
import org.wso2.carbon.osgi.test.util.utils.CarbonOSGiTestUtils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;

import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.testng.Assert.assertEquals;

/**
 * Created by jayanga on 3/3/16.
 */
@Listeners(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class RandomTest2 {
    @Inject
    private JNDIContextManager jndiContextManager;

    @Configuration
    public Option[] createConfiguration() {
        List<Option> customOptions = new ArrayList<>();
        customOptions.add(mavenBundle().artifactId("org.wso2.carbon.jndi").groupId("org.wso2.carbon.jndi")
                .versionAsInProject());

        CarbonOSGiTestEnvConfigs configs = new CarbonOSGiTestEnvConfigs();
        configs.setCarbonHome("/home/jayanga/WSO2/Training/c5-sample-osgi-test/target/carbon-home");
        return CarbonOSGiTestUtils.getAllPaxOptions(configs, customOptions);
    }

    @Test
    public void testJNDITest1() throws Exception {
        InitialContext initialContext = new InitialContext();
        initialContext.createSubcontext("java:comp");
        initialContext.bind("java:comp/name", "sameera");

        InitialContext context = new InitialContext();
        String name = (String) context.lookup("java:comp/name");
        assertEquals(name, "sameera", "Value not found in JNDI");


        NamingEnumeration namingEnumeration = context.list("java:comp");
        namingEnumeration.hasMore();
        namingEnumeration.next();


        namingEnumeration = context.listBindings("java:comp");
        namingEnumeration.hasMore();
        namingEnumeration.next();

        context.rebind("java:comp/name", "jayasoma");
        name = (String) context.lookup("java:comp/name");
        assertEquals(name, "jayasoma", "Value not found in JNDI");

        context.rename("java:comp", "java:comp1");
        name = (String) context.lookup("java:comp1/name");
        assertEquals(name, "jayasoma", "Value not found in JNDI");

        context.rename("java:comp1", "java:comp");
    }

    @Test
    public void testJNDITest2() throws Exception {

        Hashtable<String, String> environment = new Hashtable<>(1);
        environment.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY,
                "org.wso2.carbon.jndi.java.javaURLContextFactory");


        InitialContext initialContext = new InitialContext(environment);

        initialContext.createSubcontext("java:comp/env1");
        initialContext.bind("java:comp/env1/name", "sameera");

        InitialContext context = new InitialContext(environment);
        String name = (String) context.lookup("java:comp/env1/name");

        assertEquals(name, "sameera", "Value not found in JNDI");
    }

    @Test
    public void testJNDITest3() throws Exception {
        String name = null;

        Context initialContext = jndiContextManager.newInitialContext();

        initialContext.createSubcontext("java:comp/env2");
        initialContext.bind("java:comp/env2/name", "jayasoma");

        Context context = jndiContextManager.newInitialContext();

        name = (String) context.lookup("java:comp/env2/name");

        assertEquals(name, "jayasoma", "Value not found in JNDI");
    }
}
