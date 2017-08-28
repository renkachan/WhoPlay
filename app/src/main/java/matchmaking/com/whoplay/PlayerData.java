package matchmaking.com.whoplay;

/**
 * Created by Hubert on 7/29/2017.
 */

public class PlayerData {
    String name;
    int playedTimes;

    public PlayerData(String name) {
        this.name = name;
        playedTimes = 0;
    }

    public void setName(String name) {
        this.name = name;
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
