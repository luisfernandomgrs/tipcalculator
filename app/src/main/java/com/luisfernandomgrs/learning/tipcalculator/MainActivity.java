package com.luisfernandomgrs.learning.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private EditText txtValueToPay;
    private TextView txtSelectionSeekBar;
    private EditText editTxtTipValueResult;
    private EditText editTxtSumValueResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // personal initialize objects
        seekBar = findViewById(R.id.seekBar);
        txtValueToPay = findViewById(R.id.txtValueToPay);
        txtSelectionSeekBar = findViewById(R.id.txtSelectionSeekBar);
        editTxtTipValueResult = findViewById(R.id.editTxtTipValueResult);
        editTxtSumValueResult = findViewById(R.id.editTxtSumValueResult);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtSelectionSeekBar.setText(progress + "%");
                calculateTipValue();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        txtValueToPay.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                calculateTipValue();
                return false;
            }
        });

        // personal settings
        setTitle(R.string.titleAplication);
        txtValueToPay.setText("");
        editTxtTipValueResult.setEnabled(false);
        editTxtSumValueResult.setEnabled(false);
    }

    private void calculateTipValue() {
        int progress;

        progress = seekBar.getProgress();
        if ((progress > 0) && (txtValueToPay.getText().length() > 0)) {
            double dValueToPay;
            double dTipValue;
            double dPercentTipCalcultor;

            dValueToPay = Double.parseDouble(txtValueToPay.getText().toString());
            dPercentTipCalcultor = Double.parseDouble(String.valueOf(progress));
            dTipValue = dValueToPay * (0 + (dPercentTipCalcultor/100));
            editTxtTipValueResult.setText("R$ " + Double.toString(dTipValue));
            editTxtSumValueResult.setText("R$ " + Double.toString(dValueToPay + dTipValue));
        }
        else {
            editTxtTipValueResult.setText("R$ 0.00");
            editTxtSumValueResult.setText("R$ 0.00");
        }
    }
}