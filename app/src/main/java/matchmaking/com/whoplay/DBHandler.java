package matchmaking.com.whoplay;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by robert.arifin on 28/08/2017.
 */

public class DBHandler extends SQLiteOpenHelper {

    public  DBHandler (Context context)
    {
        super(context,DBContract.DATABASE_NAME, null , DBContract.DATABASE_VERSION);
    }

    @Override
    public  void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DBContract.TABLE_USERS.CREATE_TABLE);
    }

    @Override
    public  void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(DBContract.TABLE_USERS.DELETE_TABLE);
        onCreate(db);
    }
}
