package com.example.klechak.hamradiotools2;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void stringConcatenation() {
        String callsign = "DC6MT";
        String curDate = "2019-10-04";
        String stringFreq = "14.074";
        String stringPower = "20";
        String stringMode = "PSK31";
        String stringComments = "A1";

        String outputString1 = callsign + " - " + curDate + " - " +  " - " + stringFreq + " - " + stringPower + " Watts  - " + stringMode + " " + stringComments + "\n__________________________________________________\n"; // i bet \n doesn't do anything Haha.
        String outputString2= String.format("%s - %s - %s - %s Watts - %s %s\n__________________________________________________\n", callsign, curDate, stringFreq, stringPower, stringMode, stringComments) ;
        assertEquals(outputString1, outputString2);
    }
}