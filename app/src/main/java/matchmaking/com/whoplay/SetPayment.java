package matchmaking.com.whoplay;

import android.content.Intent;
import  java.text.NumberFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Created by robert.arifin on 15/08/2017.
 */

public class SetPayment extends AppCompatActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_set_payment);
        getFormattedNumber();
    }


    public void nextActivity(View view) {
        EditText payment = (EditText) findViewById(R.id.setPayment);
        DataManager.getInstance().setPayment = Integer.parseInt(payment.getText().toString().replaceAll(",",""));
        Intent i = new Intent(this, PlayerPayment.class);
        startActivity(i);
    }

    private void getFormattedNumber() {
        final EditText amount = (EditText) findViewById(R.id.setPayment);
        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                amount.removeTextChangedListener(this);
//                if(amount.getText().toString().contains(","))   {
//                    amount.getText().toString().replaceAll(",","");
//                }
                //Double amountInDouble = Double.parseDouble(amount.getText().toString());
                DecimalFormat formatter = new DecimalFormat("#,###,###");
                if(amount.getText().toString().contains(","))   {
                    amount.setText(amount.getText().toString().replace(",",""));
                }
                String amountFormatted = formatter.format(Integer.parseInt(amount.getText().toString()));
               // String amountFormatted = NumberFormat.getNumberInstance(Locale.US).format(amountInDouble);
                amount.setText(amountFormatted);
                amount.setSelection(amount.getText().length());
                amount.addTextChangedListener(this);
                }
            });
        }


}


