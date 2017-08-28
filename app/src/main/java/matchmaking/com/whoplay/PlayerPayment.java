package matchmaking.com.whoplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by robert.arifin on 15/08/2017.
 */

public class PlayerPayment extends AppCompatActivity implements  PlayerPaymentAdapter.AddOrRemovedPayment {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_payment);
        PlayerPaymentAdapter adapter = new PlayerPaymentAdapter(this,R.layout.blue_print_payment,DataManager.getInstance().playerData);
        adapter.setAddOrRemovedPayment(this);
        ListView listView = (ListView)findViewById(R.id.playerToPay);
        listView.setAdapter(adapter);
        TextView expPayment = (TextView)findViewById(R.id.expPayment);
        String  payment = String.valueOf((DataManager.getInstance().playerData.size() * DataManager.getInstance().setPayment));
        expPayment.setText(payment);
    }

    public  void updateTotalPayment ()  {
        TextView total = (TextView) findViewById(R.id.totalPayment);
        String  payment = String.valueOf(DataManager.getInstance().payed * DataManager.getInstance().setPayment);
        total.setText(payment);

    }
    @Override
    public  void OnSuccessAddOrRemovePayment ()    {
        updateTotalPayment();
   }
}
