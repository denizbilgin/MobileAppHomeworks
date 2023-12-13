package com.example.simplemvpcalculator;

public class SimpleCalculator {

    CalculatorListener listener;
    Integer operand1;
    Integer operand2;

    String operator;

    public SimpleCalculator(CalculatorListener listener) {
        this.listener = listener;
    }

    public void setOperand(int operand) {
        if(operand1 == null) {
            operand1 = operand;
        }
        else {
            operand2 = operand;
        }

        if(operator == null) {
            return;
        }

        if(operator.equals("+")) {
            operand1 += operand2;
        }
        else if (operator.equals("-")) {
            operand1 -= operand2;
        }

        listener.onResultCalculated(operand1);
    }

    public void setOperator(String operator) {
        if(operator.equals("=")) {
            operator = null;
            operand2 = null;
        }else
            this.operator = operator;
    }

    public void reset() {
        operator = null;
        operand1 = null;
        operand2 = null;
    }
}