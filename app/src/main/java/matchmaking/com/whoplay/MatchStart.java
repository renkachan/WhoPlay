package matchmaking.com.whoplay;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Renka on 8/5/2017.
 */

public class MatchStart extends AppCompatActivity {
    Handler handler = new Handler();
    int totalTime;
    ArrayList<PlayerData> playerList = playerPool();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_match_start);

        TextView timeInMinute = (TextView) findViewById(R.id.editTextMinute);
        TextView timeInSecond = (TextView) findViewById(R.id.editTextSecond);
        timeInMinute.setText(DataManager.getInstance().matchDurationInMinute);
        timeInSecond.setText(DataManager.getInstance().matchDurationInSeconds);
        totalTime = Integer.parseInt(DataManager.getInstance().matchDurationInMinute) * 60 + Integer.parseInt(DataManager.getInstance().matchDurationInSeconds);
        setPlayerPosition();
    }

    private  ArrayList<PlayerData> playerPool ()    {
        MatchPlayers playersPool = new MatchPlayers();
        ArrayList<PlayerData> playersList = new ArrayList<>();
        playersList.addAll(DataManager.getInstance().playerData);
        ArrayList<Integer> playedTimes = playersPool.whoPlayedMin();
        ArrayList<PlayerData> players = new ArrayList<>();
        Log.i("tag","" + playedTimes);
        Log.i("tag","" + DataManager.getInstance().playerData);
        int totalPlayer = 0;
        if (DataManager.getInstance().matchType == "2x2")   {
            totalPlayer = 4;
        }
        else if (DataManager.getInstance().matchType == "3x3")   {
            totalPlayer = 6;
        }
        else if (DataManager.getInstance().matchType == "5x5")  {
            totalPlayer = 10;
        }
        for ( int i = 0; i < totalPlayer; i++ )
            {
                for (int j = 0; j < playersList.size();j++) {
                    if (playersList.get(j).playedTimes == playedTimes.get(i)) {
                        players.add(playersList.get(j));
                        playersList.remove(playersList.get(j));
                        break;
                    }
                }
            }
        return players;
    }

    private void setPlayerPosition ()    {
        RandomPlayers random = new RandomPlayers(DataManager.getInstance().matchType);
        ArrayList<Integer> randomList = random.matchMakingPlayers();
        int[] playerPosition = {
                R.id.player1,
                R.id.player2,
                R.id.player3,
                R.id.player4,
                R.id.player5,
                R.id.player6,
                R.id.player7,
                R.id.player8,
                R.id.player9,
                R.id.player10,
        };

        for (int i = 0; i < randomList.size(); i++)   {
            ((TextView)findViewById(playerPosition[i])).setText((playerList.get(randomList.get(i))).getName().toString());
            DataManager.getInstance().playerData.get(DataManager.getInstance().playerData.indexOf(playerList.get(randomList.get(i)))).addPlayedTimes();
            findViewById(playerPosition[i]).setVisibility(View.VISIBLE);
        }
    }

    public void  onNextActivity (View v) {
        Intent i = new  Intent(this,PlayerList.class);
        startActivity(i);
    }

    public void timerCountDown(View v) {
        setCountDown();
    }

    public void setCountDown () {
        final Button startButton = (Button) findViewById(R.id.startBtn);
        Button stopButton = (Button)findViewById(R.id.stopBtn);
        final TextView timeInMinute = (TextView) findViewById(R.id.editTextMinute);
        final TextView timeInSecond = (TextView) findViewById(R.id.editTextSecond);
        startButton.setVisibility(View.GONE);
        stopButton.setVisibility(View.VISIBLE);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                totalTime--;
                int minuteLeft = totalTime / 60;
                int secondLeft = totalTime % 60;

                Log.i("time", totalTime + " - " + minuteLeft + ":" + secondLeft);
                timeInMinute.setText((minuteLeft < 10) ? "0" + minuteLeft : "" + minuteLeft);
                timeInSecond.setText((secondLeft < 10) ? "0" + secondLeft : "" + secondLeft);

                if (totalTime > 0) {
                    setCountDown();
                }

            }
        }, 1000);
    }

    public  void timerPause (View v) {
        handler.removeCallbacksAndMessages(null);
        Button startButton = (Button) findViewById(R.id.startBtn);
        Button stopButton = (Button)findViewById(R.id.stopBtn);
        stopButton.setVisibility(View.GONE);
        startButton.setVisibility(View.VISIBLE);
    }
    public  void nextActivity (View v)  {
        Intent i = new Intent(this,SetPayment.class);
        startActivity(i);
    }


  public void previousActivity (View v)   {
      for (int i = 0; i < playerList.size(); i++)   {
          DataManager.getInstance().playerData.get(DataManager.getInstance().playerData.indexOf(playerList.get(i))).minPlayedTimes();
      }
      Intent i = new Intent(this,MatchDuration.class);
      startActivity(i);
  }
}
