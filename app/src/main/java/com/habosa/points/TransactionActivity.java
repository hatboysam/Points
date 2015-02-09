package com.habosa.points;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

public class TransactionActivity extends ActionBarActivity implements View.OnClickListener {

    private EditText mAmountField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        // Amount to send
        mAmountField = (EditText) findViewById(R.id.edit_amount);

        // Buttons
        findViewById(R.id.button_0).setOnClickListener(this);
        findViewById(R.id.button_1).setOnClickListener(this);
        findViewById(R.id.button_2).setOnClickListener(this);
        findViewById(R.id.button_3).setOnClickListener(this);
        findViewById(R.id.button_4).setOnClickListener(this);
        findViewById(R.id.button_5).setOnClickListener(this);
        findViewById(R.id.button_6).setOnClickListener(this);
        findViewById(R.id.button_7).setOnClickListener(this);
        findViewById(R.id.button_8).setOnClickListener(this);
        findViewById(R.id.button_9).setOnClickListener(this);
        findViewById(R.id.button_decimal).setOnClickListener(this);
        findViewById(R.id.button_del).setOnClickListener(this);
    }

    private void enterKey(String val) {
        mAmountField.append(val);
    }

    private void enterDelete() {
        String currentText = mAmountField.getText().toString();
        String nextText = "";

        // Only delete if there is something there
        if (currentText.length() > 0) {
            nextText = currentText.substring(0, currentText.length() - 1);
        }

        // Delete trailing decimals
        if (nextText.endsWith(".")) {
            nextText = nextText.substring(0, nextText.length() - 1);
        }

        mAmountField.setText(nextText);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_0:
                enterKey("0");
                break;
            case R.id.button_1:
                enterKey("1");
                break;
            case R.id.button_2:
                enterKey("2");
                break;
            case R.id.button_3:
                enterKey("3");
                break;
            case R.id.button_4:
                enterKey("4");
                break;
            case R.id.button_5:
                enterKey("5");
                break;
            case R.id.button_6:
                enterKey("6");
                break;
            case R.id.button_7:
                enterKey("7");
                break;
            case R.id.button_8:
                enterKey("8");
                break;
            case R.id.button_9:
                enterKey("9");
                break;
            case R.id.button_decimal:
                enterKey(".");
                break;
            case R.id.button_del:
                enterDelete();
                break;
        }
    }
}
