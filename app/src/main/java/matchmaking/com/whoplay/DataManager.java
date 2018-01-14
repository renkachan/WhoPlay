package matchmaking.com.whoplay;

import java.util.ArrayList;

/**
 * Created by Hubert on 7/29/2017.
 */

public class DataManager {
    public static ArrayList<PlayerData> playerData  = new ArrayList<PlayerData>();
    public static ArrayList<PlayerData> playerDataInDb = new ArrayList<PlayerData>();
    public static int payed = 0;
    public static int setPayment = 0;
    public static String matchType = "";
    public static String matchDurationInMinute = "";
    public static String matchDurationInSeconds = "";
}
