package com.example.simplemvpcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity implements CalculatorView {
    Presenter presenter;
    EditText txtNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new Presenter(this);
        txtNumber = findViewById(R.id.txtNumber);
        TableLayout table = findViewById(R.id.numpad);

        for (int i = 0; i < table.getChildCount(); i++){
            TableRow row = (TableRow) table.getChildAt(i);
            for(int j = 0; j < row.getChildCount(); j++){
                Button btn = (Button) row.getChildAt(j);
                btn.setOnClickListener(presenter);
            }
        }
    }

    @Override
    public void setResult(String number) {
        txtNumber.setText(number);
    }

    @Override
    public String getOperand() {
        return txtNumber.getText().toString();
    }

}