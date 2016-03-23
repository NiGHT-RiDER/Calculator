package com.example.stefan.calculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button decimal ;
    private Operation operation ;
    private double firstNumber;
    private TextView display;
    private boolean startOver = true;

    private enum Operation{
        ADD , SUBSTRACT , MULTIPLY , DIVIDE , STARTOVER , NONE
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //tells the activity which layout to use
        setContentView(R.layout.activity_main);
        display = (TextView) findViewById(R.id.display);
        decimal = (Button) findViewById(R.id.decimal);
        operation = Operation.STARTOVER;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // tells the activity which menu to use.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.clear_display) {
            display.setText("0");
            operation = Operation.STARTOVER;
            decimal.setEnabled(true);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void equalButtonClick(View view){
        decimal.setEnabled(true);
        double secondNumber = Double.parseDouble(display.getText().toString());

        switch (operation){
            case ADD :
                firstNumber += secondNumber;
                break;
            case MULTIPLY:
                firstNumber *= secondNumber;
                break;
            case SUBSTRACT:
                firstNumber -= secondNumber;
                break;
            case DIVIDE:
                firstNumber /= secondNumber;
                break;
        }
        display.setText(firstNumber + "");
        operation = Operation.STARTOVER;
    }

    public void operationButtonClick(View view){
        firstNumber = Double.parseDouble(display.getText().toString());
        decimal.setEnabled(true);
        display.setText("");
        switch (view.getId()) {
            case R.id.add:
                operation = Operation.ADD;
                break;
            case R.id.subtract:
                operation = Operation.SUBSTRACT;
                break;
            case R.id.divide:
                operation = Operation.DIVIDE;
                break;
            case R.id.multiply:
                operation = Operation.MULTIPLY;
                break;
        }
    }

    public void numberButtonClick(View view){
        Button button = (Button) view;
        if(operation == Operation.STARTOVER){
            operation = Operation.NONE;
            display.setText("");
        }
        if(button.getText().equals(".") || display.getText().toString().contains(".")){
            decimal.setEnabled(true);
        }
        display.append(button.getText());
    }
}
