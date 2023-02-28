package com.example.simplecalc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(JUnit4.class)
public class CalculatorTest {

    private Calculator mCalculator;

    /**
     * Set up the environment for testing
     */
    @Before
    public void setUp() {

        mCalculator = new Calculator();
    }

    @Test
    public void testNegativePow(){
        double resultPow = mCalculator.power(-2d, 2d);
        assertThat(resultPow, is(equalTo(4d)));
    }

    @Test
    public void testNegativePow2(){
        double resultPow = mCalculator.power(2d, -2d);
        assertThat(resultPow, is(equalTo(0.25d)));
    }

    @Test
    public void testZeroAndPositivePow(){
        double resultPow = mCalculator.power(0d, 5d);
        assertThat(resultPow, is(equalTo(0d)));
    }

    @Test
    public void testZeroSecondOperandPow(){
        double resultPow = mCalculator.power(5d, 0d);
        assertThat(resultPow, is(equalTo(1d)));
    }

    @Test
    public void testZeroNegativeOnePow(){
        double resultPow = mCalculator.power(0d, -1d);
        assertThat(resultPow, is(equalTo(Double.POSITIVE_INFINITY)));
    }

    @Test
    public void tesNegativeZeroNegativeNumberPow(){
        double resultPow = mCalculator.power(-0d, -2d);
        assertThat(resultPow, is(equalTo(Double.POSITIVE_INFINITY)));
    }

}