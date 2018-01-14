package matchmaking.com.whoplay;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by robert.arifin on 28/08/2017.
 */

public class PlayerListDB extends AppCompatActivity implements PlayerListDBAdapter.InsertPlayerListener {
    ArrayList<PlayerData> playerFromDb = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list_players_db);
        retriveTheList();
        updateUI();
    }
     private  void updateUI()
     {
        PlayerListDBAdapter adapter = new PlayerListDBAdapter(
                this, R.layout.blueprint_player_list_db, DataManager.playerDataInDb);
        adapter.setInsertPlayerListener(this);
        ListView listView = (ListView) findViewById(R.id.listViewPlayerDb);
        listView.setAdapter(adapter);
    }

    public  void retriveTheList ()
    {
        DBHandler handler = new DBHandler(this);
        DataManager.playerDataInDb = DBContract.TABLE_USERS.getData(handler.getWritableDatabase());
        handler.close();
    }

    public void onConfirmationAddPlayersToSingleTonePlayerData (View v)
    {
        for (int i=0; i<playerFromDb.size(); i++) {
            DataManager.playerData.add(playerFromDb.get(i));
        }

        Intent i = new Intent(this,PlayerList.class);
        startActivity(i);
    }

    public void onDelete (View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(playerFromDb.size() > 0)
        {
            builder.setMessage("Deleting all player from regular player list, are you sure?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                for (int i = 0; i < playerFromDb.size(); i++) {
                    dialog.cancel();
                    DataManager.playerDataInDb.remove(DataManager.playerDataInDb.indexOf(playerFromDb.get(i)));
                    playerFromDb.clear();
                    updateUI();
                }
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
        }
        else
        {
            builder.setMessage("No player is selected");

            builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
        }
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onInsertPlayer(PlayerData data) {
        playerFromDb.add(data);
    }

    @Override
    public void onRemovePlayer(PlayerData data) {
        playerFromDb.remove(data);
    }
}
