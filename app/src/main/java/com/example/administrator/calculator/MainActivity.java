package com.example.administrator.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText et_input;
    private Button btn_0;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_clear;
    private Button btn_del;
    private Button btn_divide;
    private Button btn_multiply;
    private Button btn_subtract;
    private Button btn_plus;
    private Button btn_dot;
    private Button btn_equal;
    private boolean clear_flag = false;  //用于判断 运算结束      即 按等号后按数字 会清空结果
    private boolean resultop = false;    //用于判断连续运算的标志  即 按等号后按运算符会继续运算

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //实例化控件
        et_input = (EditText) findViewById(R.id.et_input);
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_dot = (Button) findViewById(R.id.btn_dot);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_subtract = (Button) findViewById(R.id.btn_subtract);
        btn_multiply = (Button) findViewById(R.id.btn_multiply);
        btn_divide = (Button) findViewById(R.id.btn_divide);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_equal = (Button) findViewById(R.id.btn_equal);


        //设置监听事件
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_dot.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_subtract.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_equal.setOnClickListener(this);


    }

    private boolean opflag = false;

    @Override
    public void onClick(View v) {
        String str = et_input.getText().toString();
        switch (v.getId()) {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_dot:
                if (clear_flag) {
                    if (!resultop) {

                        clear_flag = false;
                        str = "";
                        et_input.setText("");
                    }
                }
                resultop = false;
                et_input.setText(str + ((Button) v).getText());

                break;
            case R.id.btn_plus:
            case R.id.btn_subtract:
            case R.id.btn_multiply:
            case R.id.btn_divide:

                if (clear_flag) {


                    clear_flag = false;
                    resultop = true;

                }
                et_input.setText(str + " " + ((Button) v).getText() + " ");
                break;
            case R.id.btn_clear:
                et_input.setText("");
                break;
            case R.id.btn_del:
                if (!str.isEmpty()) {
                    et_input.setText(str.substring(0, str.length() - 1));
                }
                break;
            case R.id.btn_equal:
                Log.d("xyz", "equal is pressed");
                if (!clear_flag) {
                    clear_flag = true;
                }
                getResult();
                break;
            default:
                break;
        }


    }

    private void getResult() {
        //获得表达式
        String exp = et_input.getText().toString();
        //内容为空
        if (exp.isEmpty()) {
            Log.d("xyz", "exp is empty");
            return;
        }
        //表达式中不含运算符
        if (!exp.contains(" ")) {
            Log.d("xyz", "not contain op");
            return;
        }
        double result = 0;
        String s1 = exp.substring(0, exp.indexOf(" "));//运算符前面的字符串
        Log.d("xyz", "exp.indexof  is " + exp.indexOf(" "));
        Log.d("xyz", "s1 is " + s1);
        String op = exp.substring(exp.indexOf(" ") + 1, exp.indexOf(" ") + 2);
        Log.d("xyz", op);
        String s2 = exp.substring(exp.indexOf(" ") + 3);
        Log.d("xyz", s2);

        if (!s1.isEmpty() && !s2.isEmpty()) {
            double d1 = Double.parseDouble(s1);
            double d2 = Double.parseDouble(s2);
            if (op.equals("+")) {
                result = d1 + d2;
            } else if (op.equals("-")) {
                result = d1 - d2;
            } else if (op.equals("×")) {
                result = d1 * d2;
            } else if (op.equals("÷")) {
                if (d2 == 0) {
                    result = 0;
                } else {
                    result = d1 / d2;
                }
            }
            if (!s1.contains(".") && !s2.contains(".") && !op.equals("÷")) {
                int r = (int) result;
                et_input.setText(r + "");
            } else {
                et_input.setText(result + "");
            }
            if (!s1.contains("") && s2.contains("")) {
                et_input.setText(exp);
            }

        }

    }
}
