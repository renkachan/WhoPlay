package matchmaking.com.whoplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Renka on 8/4/2017.
 */

public class MatchType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_match_type);
    }

    public void matchTwoVsTwo(View v) {
        DataManager.getInstance().matchType = "2x2";
        changeActivity();

    }

    public void matchThreeVsThree(View v) {
        DataManager.getInstance().matchType = "3x3";
        changeActivity();
    }

    public void matchFiveVsFive(View v) {
        DataManager.getInstance().matchType = "5x5";
        changeActivity();
    }

    public void changeActivity ()   {
        Intent i = new Intent(this,MatchDuration.class);
        startActivity(i);
    }

    public void previousActivity (View v) {
        Intent i = new Intent(this, PlayerList.class);
        startActivity(i);
    }
}
