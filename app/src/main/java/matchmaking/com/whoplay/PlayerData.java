package matchmaking.com.whoplay;

/**
 * Created by Hubert on 7/29/2017.
 */

public class PlayerData {
    String name;
    int playedTimes;
    int totalPlayedTimes;

    public PlayerData(String name,int totalPlayedTimes) {
        this.name = name;
        playedTimes = 0;
        this.totalPlayedTimes = totalPlayedTimes;

    }

    public void addPlayedTimes() {
        playedTimes++;
    }

    public  void minPlayedTimes()   {
        playedTimes--;
    }

    public String getName() {
        return name;
    }

    public int getPlayedTimes() {
        return playedTimes;
    }
}
