package matchmaking.com.whoplay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * Created by robert.arifin on 15/08/2017.
 */

public class PaymentGenerator extends AppCompatActivity implements  PlayerPaymentAdapter.AddOrRemovedPayment {
    private View view1, view2;
    String amountFormatted = "";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view1 = getLayoutInflater().inflate(R.layout.layout_payment_generator,null);
        view2 = getLayoutInflater().inflate(R.layout.layout_payment, null);
        setContentView(view1);
        getFormattedNumber();
    }


    public void setPayment(View view) {
        EditText payment = (EditText) findViewById(R.id.setPayment);
        if (!payment.getText().toString().equals("")) {
            DataManager.getInstance().setPayment = Integer.parseInt(payment.getText().toString().replaceAll(",", ""));
        }
        else    {
            Toast.makeText(this, "Fee cannot be empty",Toast.LENGTH_LONG).show();
        }
    }

    public void seePayment(View view)
    {
        if(DataManager.getInstance().setPayment != 0 ) {
            view1.setVisibility(View.GONE);
            setContentView(view2);
            PlayerPaymentAdapter adapter = new PlayerPaymentAdapter(this, R.layout.blue_print_payment, DataManager.getInstance().playerData);
            adapter.setAddOrRemovedPayment(this);
            ListView listView = (ListView) findViewById(R.id.playerToPay);
            listView.setAdapter(adapter);
            TextView expPayment = (TextView) findViewById(R.id.expPayment);
            String payment = String.valueOf((DataManager.getInstance().playerData.size() * DataManager.getInstance().setPayment));
            expPayment.setText(payment);
        }
        else    {
            Toast.makeText(this, "Fee cannot be empty",Toast.LENGTH_LONG).show();
    }


    }

        public  void updateTotalPayment ()  {
            TextView total = (TextView) findViewById(R.id.totalPayment);
            String  payment = String.valueOf(DataManager.getInstance().payed * DataManager.getInstance().setPayment);
            total.setText(payment);

        }

    public void backToPreviousLayout (View view)
    {
        view2.setVisibility(View.GONE);
        view1.setVisibility(View.VISIBLE);
        setContentView(view1);
    }

    public void backToMatchStart(View view) {
        Intent i = new  Intent(this,MatchStart.class);
        startActivity(i);
    }

        @Override
        public  void OnSuccessAddOrRemovePayment ()    {
            updateTotalPayment();
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
                DecimalFormat formatter = new DecimalFormat("#,###,###");

                if(amount.getText().toString().contains(","))   {
                    amount.setText(amount.getText().toString().replace(",",""));
                }
                if (amount.length() != 0 || amount.equals("")) {
                     amountFormatted = formatter.format(Integer.parseInt(amount.getText().toString()));
                }
                else   {
                    amountFormatted = "";
                }
                amount.setText(amountFormatted);
                amount.setSelection(amount.getText().length());
                amount.addTextChangedListener(this);
                }
            });
    }
}


