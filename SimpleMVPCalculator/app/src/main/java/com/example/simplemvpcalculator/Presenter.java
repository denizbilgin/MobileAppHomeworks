package com.example.simplemvpcalculator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
public class Presenter implements CalculatorListener, View.OnClickListener {

    SimpleCalculator calculator;
    CalculatorView calculatorView;

    boolean operatorExpected;
    boolean newNumber = true;
    public Presenter(CalculatorView view){
        this.calculatorView = view;
        calculator = new SimpleCalculator(this);
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        String symbol = btn.getText().toString();
        Log.d("Presenter", symbol);


        if(symbol.equals("+") || symbol.equals("-")|| symbol.equals("=")){
            calculator.setOperand(Integer.parseInt(
                    calculatorView.getOperand()));
            calculator.setOperator(symbol);
            newNumber = true;
            return;
        }else if (symbol.toLowerCase().equals("clear")){
            calculatorView.setResult("");
            calculator.reset();
            newNumber = true;
        }else{
            if(newNumber){
                newNumber = false;
                calculatorView.setResult("");
            }
            Log.d("Presenter",calculatorView.getOperand()+symbol);
            calculatorView.setResult(calculatorView.getOperand()+symbol);
        }


    }

    @Override
    public void onResultCalculated(Integer result) {
        calculatorView.setResult(result + "");
    }
}

