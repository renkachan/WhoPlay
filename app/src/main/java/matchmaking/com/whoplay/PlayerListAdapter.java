package matchmaking.com.whoplay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Hubert on 7/28/2017.
 */

public class PlayerListAdapter extends ArrayAdapter<PlayerData> {

    public PlayerListAdapter(Context context, int resource, ArrayList<PlayerData> item) {
        super(context, resource, item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.blue_print, null);
        }

        PlayerData playerData = getItem(position);
        final int fPosition = position;

        if (playerData != null) {
        	TextView txt;

            txt = (TextView) view.findViewById(R.id.blueprint_player_name);
            txt.setText(playerData.getName());

            txt = (TextView) view.findViewById(R.id.blueprint_played_times);
            txt.setText(String.format("%d", playerData.getPlayedTimes()));

            Button btn = (Button) view.findViewById(R.id.blueprint_delete);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataManager.getInstance().playerData.remove(fPosition);
                    deletePlayerListener.onDeletePlayerSuccess();
                }
            });
        }

        return view;
    }

    /**
     * Listener
     */
    public interface DeletePlayerListener {
        void onDeletePlayerSuccess();
    }

    private DeletePlayerListener deletePlayerListener;

    public void setDeletePlayerListener(DeletePlayerListener listener) {
        this.deletePlayerListener = listener;
    }
}
