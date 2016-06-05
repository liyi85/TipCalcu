package com.example.andrearodriguez.tipcalcu.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.andrearodriguez.tipcalcu.R;
import com.example.andrearodriguez.tipcalcu.TipCalcApp;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.inputBill)
    EditText inputBill;
    @Bind(R.id.btn_cal)
    Button btnCal;
    @Bind(R.id.et_percentage)
    EditText etPercentage;
    @Bind(R.id.btn_increase)
    Button btnIncrease;
    @Bind(R.id.btn_decrease)
    Button btnDecrease;
    @Bind(R.id.btn_borrar)
    Button btnBorrar;
    @Bind(R.id.txtTip)
    TextView txtTip;

    private final static int TIP_STEP_CHANGE = 1;
    private final static int DEFAULT_TIP_PERCENTAGE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            about();
        }
        return super.onOptionsItemSelected(item);
    }

    private void about() {

        TipCalcApp app = (TipCalcApp) getApplication();
        String strUrl = app.getAboutUrl();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(strUrl));
        startActivity(intent);
    }

    @OnClick(R.id.btn_cal)
    public void handleClickSubmit() {
        hideKeyboard();

        String strInputTotal = inputBill.getText().toString().trim();
        if (!strInputTotal.isEmpty()) {
            double total = Double.parseDouble(strInputTotal);
            int tipPercentage = getTipPercentage();
            double tip = total * (tipPercentage / 100d);

            String strTip = String.format(getString(R.string.global_message_tip), tip);
            txtTip.setVisibility(View.VISIBLE);
            txtTip.setText(strTip);
        }
    }

    private int getTipPercentage() {
        int tipPercentage = DEFAULT_TIP_PERCENTAGE;
        String strInputTipPercentage = etPercentage.getText().toString().trim();
        if(!strInputTipPercentage.isEmpty()){
            tipPercentage=Integer.parseInt(strInputTipPercentage);
        }else{
            etPercentage.setText(String.valueOf(tipPercentage));
        }
        return tipPercentage;
    }

    @OnClick(R.id.btn_increase)
    public void handleClickIncrease() {
        hideKeyboard();
        handleTipChange(TIP_STEP_CHANGE);

    }

    private void handleTipChange(int change) {
        int tipPercentage = getTipPercentage();
        tipPercentage += change;
        if (tipPercentage>0){
            etPercentage.setText(String.valueOf(tipPercentage));
        }

    }

    @OnClick(R.id.btn_decrease)
    public void handleClickDecrease() {
        hideKeyboard();
        handleTipChange(-TIP_STEP_CHANGE);
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (NullPointerException npe) {
            Log.e(getLocalClassName(), Log.getStackTraceString(npe));
        }
    }
}


