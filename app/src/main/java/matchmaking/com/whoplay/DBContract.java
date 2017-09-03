package matchmaking.com.whoplay;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by robert.arifin on 28/08/2017.
 */

public final class DBContract {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "whoPlay.db";

    private DBContract() {
    }

    public static final class TABLE_USERS implements BaseColumns {
        public static final String TABLE_NAME = "Players";
        public static final String COLUMN_PLAYER_NAME = "playerName";
        public static final String COLUMN_PLAYED_TIMES = "totalPlayedTimes";

        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_PLAYER_NAME + " TEXT, " +
                        COLUMN_PLAYED_TIMES + " TEXT )";

        public static final String DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;


        public static long insertData(SQLiteDatabase db,
                                      String playerName,
                                      int playedTimes) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_PLAYER_NAME, playerName);
            values.put(COLUMN_PLAYED_TIMES, playedTimes);
            return db.insert(TABLE_NAME, null, values);
        }

        public static void deleteData(SQLiteDatabase db, String playerName) {
            String selection = COLUMN_PLAYER_NAME + "=?";
            String[] selectionArgs = {playerName};
            db.delete(TABLE_NAME, selection, selectionArgs);
            //db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_PLAYER_NAME + " = '" + playerName + "'" );
        }

        public static ArrayList<PlayerData> getData(SQLiteDatabase db) {
            Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
            ArrayList<PlayerData> items = new ArrayList<>();
            while (cursor.moveToNext())
            {
                items.add(addItem(cursor));
            }
            cursor.close();
            return items;
        }

        public static PlayerData addItem(Cursor cursor) {
           PlayerData playerData = new PlayerData(
                   cursor.getString(cursor.getColumnIndexOrThrow(TABLE_USERS.COLUMN_PLAYER_NAME)),
                   cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PLAYED_TIMES)));
            return playerData;
        }
    }
}
