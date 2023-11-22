package com.example.simplemvpcalculator;

public class MockCalculatorListener implements CalculatorListener {

    int result;
    public int getResult() {
        return result;
    }

    @Override
    public void onResultCalculated(Integer operand1) {
        result = operand1;
    }
}
