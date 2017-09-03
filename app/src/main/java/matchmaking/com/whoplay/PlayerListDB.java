package matchmaking.com.whoplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

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
        retriveTheList ();
        PlayerListDBAdapter adapter = new PlayerListDBAdapter(
                this, R.layout.blueprint_player_list_db, DataManager.getInstance().playerDataInDb);
        adapter.setInsertPlayerListener(this);
        ListView listView = (ListView) findViewById(R.id.listViewPlayerDb);
        listView.setAdapter(adapter);
    }

    public  void retriveTheList ()
    {
        DBHandler handler = new DBHandler(this);
        DataManager.getInstance().playerDataInDb = DBContract.TABLE_USERS.getData(handler.getWritableDatabase());
        handler.close();
    }

    public void onConfirmation (View v)
    {
        for (int i=0; i<playerFromDb.size(); i++) {
            DataManager.getInstance().playerData.add(playerFromDb.get(i));
        }
        Intent i = new Intent(this,PlayerList.class);
        startActivity(i);
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
