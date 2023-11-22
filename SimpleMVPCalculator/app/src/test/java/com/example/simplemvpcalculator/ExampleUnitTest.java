package com.example.simplemvpcalculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    // To run the test, right-click here and run it individually
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testCalculator() {
        CalculatorListener listener = new MockCalculatorListener();

        SimpleCalculator calc = new SimpleCalculator(listener);
        calc.setOperand(72);
        calc.setOperator("+");
        calc.setOperand(6);
        calc.setOperator("-");
        calc.setOperand(3);
        calc.setOperator("=");

        assertEquals(75, ((MockCalculatorListener) listener).getResult());
    }
}