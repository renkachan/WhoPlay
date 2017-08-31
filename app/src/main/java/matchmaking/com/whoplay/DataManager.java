package matchmaking.com.whoplay;

import java.util.ArrayList;

/**
 * Created by Hubert on 7/29/2017.
 */

public class DataManager {
    private static DataManager mInstance;
    public static DataManager getInstance() {
        if (mInstance == null) {
            mInstance = new DataManager();
        }
        return mInstance;
    }
    ArrayList<PlayerData> playerData;
    ArrayList<PlayerData> playerDataInDb;
    int payed = 0;
    int setPayment = 0;
    String matchType = "";
    String matchDurationInMinute = "";
    String matchDurationInSeconds = "";

    private DataManager() {
        playerData = new ArrayList<PlayerData>();
        playerDataInDb = new ArrayList<PlayerData>();
    }

}
