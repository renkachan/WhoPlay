package matchmaking.com.whoplay;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.view.View;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * Created by robert.arifin on 07/08/2017.
 */

public class RandomPlayers {
    private String matchType;

    public RandomPlayers(String matchType) {
        this.matchType = matchType;
    }

    public ArrayList<Integer> matchMakingPlayers() {
        Integer numberOfPlayer = 0;
        Random r = new Random();
        ArrayList <Integer> randomNumber = new ArrayList<>();
        int totalPlayer = 0;

        if (matchType == "2x2") {
            totalPlayer = 4;
        }
        if (matchType == "3x3") {
            totalPlayer = 6;
        }
        else if (matchType == "5x5")    {
            totalPlayer = 10;
        }

        while (numberOfPlayer <= totalPlayer - 1) {
            int random = r.nextInt(totalPlayer);
            if (!randomNumber.contains(random)) {
                randomNumber.add(random);
                numberOfPlayer++;
            }
        }
        return  randomNumber;
    }
}
