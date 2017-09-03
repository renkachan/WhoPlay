package matchmaking.com.whoplay;

import android.content.Context;
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
            checkBox.setText(playerData.getName());

            txt = (TextView) view.findViewById(R.id.blueprint_playerName);
            txt.setText(playerData.getName());

//            txt = (TextView) view.findViewById(R.id.blueprint_played_times);
//            txt.setText(String.format("%d", playerData.getPlayedTimes()));

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                    PlayerData choosenPlayer = new PlayerData(checkBox.getText().toString(), DataManager.getInstance().playerDataInDb.get(DataManager.getInstance().playerDataInDb.indexOf(checkBox.getText().toString())).totalPlayedTimes);
                    if (checkBox.isChecked()) {
                        listener.onInsertPlayer(playerData);
                    } else {
                        listener.onRemovePlayer(playerData);
                    }
                }
            });
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
