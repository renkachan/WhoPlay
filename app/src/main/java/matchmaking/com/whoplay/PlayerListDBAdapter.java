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
import java.util.List;

/**
 * Created by robert.arifin on 28/08/2017.
 */

public class PlayerListDBAdapter extends  ArrayAdapter<PlayerData> {
    private  ArrayList<PlayerData> playerList = new ArrayList<PlayerData>();

    public PlayerListDBAdapter(Context context, int resource, ArrayList<PlayerData> list) {
        super(context, resource, list);
        playerList = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder viewHolder;
        //final PlayerData playerData = getItem(position);
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.blueprint_player_list_db, null);
            viewHolder = new ViewHolder();
            viewHolder.checkBox = (CheckBox) view.findViewById(R.id.checkBox);
            viewHolder.playerName = (TextView)view.findViewById(R.id.blueprint_playerName);
            viewHolder.playedTimes = (TextView) view.findViewById(R.id.blueprint_totalPlayedTimes);

           viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int innerPosition = (Integer) buttonView.getTag();
                    playerList.get(innerPosition).setSelected(buttonView.isChecked());
                    if (playerList.get(innerPosition).isSelected()) {
                        listener.onAddPlayer(playerList.get(innerPosition));
                    } else {
                        listener.onRemovePlayer(playerList.get(innerPosition));
                    }
                   // listener.onInsertRemovePlayer(playerList.get(getPosition), buttonView.isChecked());
                }
            });

//            playerList.get(position).setBooleanListener(new  PlayerData.BooleanListener()   {
//
//                @Override
//                public void onStateChange (Boolean result) {
//                    if ( playerList.get(position).isSelected() == true) {
//                        listener.onInsertPlayer(playerList.get(getPosition));
//                    } else {
//                        listener.onRemovePlayer(playerList.get(getPosition));
//                    }
//                }
//            }
//            );

            view.setTag(viewHolder);
            view.setTag(R.id.checkBox, viewHolder.checkBox);
            view.setTag(R.id.blueprint_player_name, viewHolder.playerName);
            view.setTag(R.id.blueprint_totalPlayedTimes, viewHolder.playedTimes);

        }
        else    {
             viewHolder = (ViewHolder) view.getTag();
        }

       // final PlayerData playerData = getItem(position);
     //   final int fPosition = position;


//        if (playerData != null) {
//
//            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//
//                    if ( viewHolder.getCheckBox().isChecked()) {
//                        listener.onInsertPlayer(playerData);
//                    } else {
//                        listener.onRemovePlayer(playerData);
//                    }
//                }
//            });

//        }
        viewHolder.checkBox.setTag(Integer.valueOf(position));
        viewHolder.playerName.setText(playerList.get(position).getName());
        viewHolder.playedTimes.setText(String.format("%d", playerList.get(position).getTotalPlayedTimes()));
        viewHolder.checkBox.setChecked(playerList.get(position).isSelected());
//        if(!playerList.get(position).isSelected()) {
//            listener.onRemovePlayer(playerList.get(position));
//        }
//        else    {
//            listener.onInsertPlayer(playerList.get(position));
//        }



        return view;
    }
    private static class ViewHolder {
        private CheckBox checkBox;
        private TextView playerName, playedTimes;
    }



    /**
     * Listener
     */
    public interface InsertRemovePlayerListener {
        void onRemovePlayer(PlayerData data);
        void onAddPlayer(PlayerData data);
    }
    private InsertRemovePlayerListener listener;

    public void setInsertPlayerListener(InsertRemovePlayerListener listener) {
        this.listener = listener;
    }

    /**
     * boolean listener
     */


}
