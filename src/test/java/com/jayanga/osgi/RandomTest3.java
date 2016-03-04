package com.jayanga.osgi;

import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;
import org.ops4j.pax.exam.testng.listener.PaxExam;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by jayanga on 3/3/16.
 */
@Listeners(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class RandomTest3 {

    @Test
    public void testTest5() throws Exception {
        assertEquals("Jaya", "Jaya", "Jaya != Jaya");
    }
}
