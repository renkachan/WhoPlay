package matchmaking.com.whoplay;


public class PlayerData {
    String name;
    int playedTimes;
    int totalPlayedTimes;
    private boolean selected = false;

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

    public int getTotalPlayedTimes ()
    {
        return  totalPlayedTimes;
    }
    public int getPlayedTimes() {
        return playedTimes;
    }

    public boolean isCheckBoxSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Boolean isSelected ()   {
        return selected;
    }
}
