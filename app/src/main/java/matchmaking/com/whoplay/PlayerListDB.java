package matchmaking.com.whoplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * Created by robert.arifin on 28/08/2017.
 */

public class PlayerListDB extends  AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_list_players_db);
    }

    Button confirmBtn = (Button)findViewById(R.id.confirmBtn);
    public  void retriveTheList ()
    {
        DBHandler handler = new DBHandler(this);
        DataManager.getInstance().playerDataInDb = DBContract.TABLE_USERS.getData(handler.getWritableDatabase());


    }
}
