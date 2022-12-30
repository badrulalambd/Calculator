package com.badrulacademy.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result_tv, solution_tv;
    MaterialButton button_c, button_openBracket, button_closBracket;
    MaterialButton button_divide, button_multiply, button_plus, button_minus;
    MaterialButton button_equal, button_dot, button_ac;
    MaterialButton button_zero, button_one, button_two, button_three, button_four,
    button_five, button_six, button_seven, button_eight, button_nine;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Easy Calculator");

        result_tv = findViewById(R.id.result_tv_id);
        solution_tv = findViewById(R.id.solution_tv_id);


        asignId(button_c, R.id.button_C_id);
        asignId(button_openBracket, R.id.button_openBracket_id);
        asignId(button_closBracket, R.id.button_closingBracket_id);
        asignId(button_divide, R.id.button_divide_id);
        asignId(button_multiply, R.id.button_multiply_id);
        asignId(button_plus, R.id.button_plus_id);
        asignId(button_minus, R.id.button_minus_id);
        asignId(button_equal, R.id.button_equal_id);
        asignId(button_dot, R.id.button_dot_id);
        asignId(button_ac, R.id.button_AC_id);
        asignId(button_zero, R.id.button_zero_id);
        asignId(button_one, R.id.button_one_id);
        asignId(button_two, R.id.button_two_id);
        asignId(button_three, R.id.button_three_id);
        asignId(button_four, R.id.button_four_id);
        asignId(button_five, R.id.button_five_id);
        asignId(button_six, R.id.button_six_id);
        asignId(button_seven, R.id.button_seven_id);
        asignId(button_eight, R.id.button_eight_id);
        asignId(button_nine, R.id.button_nine_id);


    }

    private void asignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        MaterialButton button = (MaterialButton) v;
        String buttonText = button.getText().toString();
        String dataToCalculate = solution_tv.getText().toString();

        if(buttonText.equals("AC")){
            solution_tv.setText("");
            result_tv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solution_tv.setText(result_tv.getText().toString());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length()-1);
        }
        else {
            dataToCalculate = dataToCalculate + buttonText;
        }

        solution_tv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            result_tv.setText(finalResult);
        }

    }

    //Method to Calculate the data
    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();

            //Condition to remove dot_zero end of the result for integer result
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }

            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }
}