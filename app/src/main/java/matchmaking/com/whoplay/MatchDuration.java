package matchmaking.com.whoplay;

import android.app.Activity;
import android.content.Intent;
import android.icu.util.TimeUnit;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by robert.arifin on 02/08/2017.
 */

public class MatchDuration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_match_duration);
        addTextChangeListener();
    }

    public void addTextChangeListener() {
        final EditText second = (EditText) findViewById(R.id.editTextSecond);
        second.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() != 0) {
                    if (Integer.parseInt(second.getText().toString()) >= 60) {
                        second.removeTextChangedListener(this);
                        second.setText("");
                        second.addTextChangedListener(this);
                    }
                }
            }
        });
    }

    public void resetTime(View v) {
        EditText minute = (EditText) findViewById(R.id.editTextMinute);
        EditText second = (EditText) findViewById(R.id.editTextSecond);
        minute.setText("");
        second.setText("");
    }

    public void previousActivity(View v) {
        Intent i = new Intent(this, MatchType.class);
        startActivity(i);
    }

    public void nextActivity(View v) {
        EditText minute = (EditText) findViewById(R.id.editTextMinute);
        EditText second = (EditText) findViewById(R.id.editTextSecond);

        if (minute.getText().toString().equalsIgnoreCase("00") || minute.getText().toString().equals("")) {
            if (second.getText().toString().equalsIgnoreCase("00") || second.getText().toString().equals("")) {
                Toast.makeText(this, "Time Cannot Be Empty", Toast.LENGTH_LONG).show();
            }
        }
        else {
            if (second.getText().toString().equals("")) {
                second.setText("00");
            }

            int left = Integer.parseInt(String.valueOf(DataManager.getInstance().matchType.charAt(0)));
            int right = Integer.parseInt(String.valueOf(DataManager.getInstance().matchType.charAt(2)));
            if (DataManager.getInstance().playerData.size() < (left + right))   {
                Toast.makeText(this, "Not Enough Players", Toast.LENGTH_LONG).show();
            }
            else {
                DataManager.getInstance().matchDurationInMinute = minute.getText().toString();
                DataManager.getInstance().matchDurationInSeconds = second.getText().toString();
                Intent i = new Intent(this, MatchStart.class);
                startActivity(i);
            }
        }
    }
}
