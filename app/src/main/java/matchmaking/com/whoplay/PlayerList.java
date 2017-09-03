package matchmaking.com.whoplay;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by Hubert on 7/28/2017.
 */

public class PlayerList extends AppCompatActivity implements PlayerListAdapter.DeletePlayerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_player);
        updateUI();
    }

    public void updateUI() {
        PlayerListAdapter adapter = new PlayerListAdapter(this, R.layout.blue_print, DataManager.getInstance().playerData);
        adapter.setDeletePlayerListener(this);
        ListView listView = (ListView) findViewById(R.id.playerList);
        listView.setAdapter(adapter);
    }

    public void onAdd(View v) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_player_dialog);
        dialog.setCanceledOnTouchOutside(false);

        Button btn;

        btn = (Button) dialog.findViewById(R.id.btn_dialog_cancel);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                v.setOnClickListener(null);
            }
        });

        btn = (Button)  dialog.findViewById(R.id.btn_dialog_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPlayer(dialog);
                //v.setOnClickListener(null);
            }
        });

        dialog.show();
    }

    public void addPlayer (Dialog dialog) {
        EditText playerName = (EditText) dialog.findViewById(R.id.etxt_player_name);
        String strPlayerName = playerName.getText().toString();
        PlayerData newData = new PlayerData(playerName.getText().toString(), 0);
        if (TextUtils.isEmpty(strPlayerName)) {
            playerName.setError("The field cannot be empty");
        }
        else    {
            DataManager.getInstance().playerData.add(newData);
            DBHandler handler =  new DBHandler(this);
            DBContract.TABLE_USERS.insertData(handler.getWritableDatabase(), strPlayerName, 0);
            handler.close();
            updateUI();
            dialog.dismiss();
        }
    }

    public void goToDBActivity (View v)
    {
        Intent i = new Intent(this , PlayerListDB.class);
        startActivity(i);
    }
//
//    public  void updateDB (String newPlayerData)
//    {
//        DBHandler handler = new DBHandler(this);
//        DBContract.TABLE_USERS.insertData(
//                handler.getWritableDatabase() , newPlayerData, "0");
//        handler.close();
//    }

    public void onDelete(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (DataManager.getInstance().playerData.size() > 0) {
            builder.setMessage("Deleting all player in this session, are you sure?");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    DataManager.getInstance().playerData.clear();
                    updateUI();
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
        } else {
            builder.setMessage("There is no player in the list.");

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

    public void changeActivity (View v)   {
        Intent i = new Intent(this , MatchType.class);
        startActivity(i);
    }

    @Override
    public void onDeletePlayerSuccess() {
        updateUI();
    }
}
