package matchmaking.com.whoplay;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by robert.arifin on 08/08/2017.
 */

public class MatchPlayers {

    public ArrayList<Integer>  whoPlayedMin ()   {
        ArrayList<Integer> playedTimes = new ArrayList<>();
        int totalOfPlayer = DataManager.playerData.size();

        for ( int i = 0; i < totalOfPlayer; i++) {
                   playedTimes.add(DataManager.playerData.get(i).getPlayedTimes());
                }

        Collections.sort(playedTimes);
        return playedTimes;
    }
}
