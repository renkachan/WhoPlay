package matchmaking.com.whoplay;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by robert.arifin on 28/08/2017.
 */

public class PlayerListDBAdapter extends  ArrayAdapter<PlayerData> {
    public PlayerListDBAdapter(Context context, int resource, ArrayList<PlayerData> item) {
        super(context, resource, item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.blueprint_player_list_db, null);

        }

        final PlayerData playerData = getItem(position);
        final int fPosition = position;


        if (playerData != null) {


            TextView txt;
            final CheckBox checkBox;
            checkBox = (CheckBox)view.findViewById(R.id.checkBox);

            txt = (TextView) view.findViewById(R.id.blueprint_playerName);
            txt.setText(playerData.getName());

            txt = (TextView) view.findViewById(R.id.blueprint_totalPlayedTimes);
            txt.setText(String.format("%d", playerData.getTotalPlayedTimes()));;

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    int getPosition = (Integer) compoundButton.getTag();
                    if (checkBox.isChecked()) {
                        listener.onInsertPlayer(playerData);
                    } else {
                        listener.onRemovePlayer(playerData);
                    }
                }
            });
            view.setTag(checkBox);
            checkBox.setTag(position);
        }

        return view;
    }

    /**
     * Listener
     */
    public interface InsertPlayerListener {
        void onInsertPlayer(PlayerData data);
        void onRemovePlayer(PlayerData data);
    }
    private InsertPlayerListener listener;

    public void setInsertPlayerListener(InsertPlayerListener listener) {
        this.listener = listener;
    }
}
