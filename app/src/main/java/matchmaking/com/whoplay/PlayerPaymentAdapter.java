package matchmaking.com.whoplay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by robert.arifin on 15/08/2017.
 */

public class PlayerPaymentAdapter extends ArrayAdapter<PlayerData> {
    public PlayerPaymentAdapter(Context context, int resource, ArrayList<PlayerData> item) {
        super(context, resource, item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.blue_print_payment, null);
        }
   PlayerData playerData = getItem(position);

        if (playerData != null)    {
            TextView txt = (TextView) view.findViewById(R.id.playerName);
            txt.setText(playerData.getName());
            final CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (checkBox.isChecked()) {
                        DataManager.getInstance().payed++;
                    } else {
                        if (DataManager.getInstance().payed > 0) {
                            DataManager.getInstance().payed--;
                        }
                    }
                    paymentListener.OnSuccessAddOrRemovePayment();
                }
        });
        }
    return view;
    }
    // Listener to set the total payment according to the number of checkbox ticked
    public interface AddOrRemovedPayment  {
        void OnSuccessAddOrRemovePayment ();
    }

    private AddOrRemovedPayment paymentListener;

    public void  setAddOrRemovedPayment(AddOrRemovedPayment listener) {
        this.paymentListener = listener;

    }
}

