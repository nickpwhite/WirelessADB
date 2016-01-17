package com.onefourfour.wirelessadb;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.ToggleButton;

import static com.onefourfour.wirelessadb.MainActivity.sudo;

/**
 * Created by nick on 13/01/16.
 */
public class ToggleActivity extends AppCompatActivity {

    ToggleButton button;
    TextView text;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        Log.d("ACTIVITY:", "In activity");
        button = (ToggleButton) findViewById(R.id.adb_button);
        text = (TextView) findViewById(R.id.statusText);

        text.setText("Shorcut Clicked");

        if(button.isChecked()) {
            Log.d("BUTTON CHECKED:", "TRUE");
            sudo(new String[]{
                    "setprop service.adb.tcp.port 5555",
                    "stop adbd",
                    "start adbd"
            });
            button.setChecked(false);
        } else {
            Log.d("BUTTON CHECKED:", "FALSE");
            sudo(new String[]{
                    "setprop service.adb.tcp.port -1",
                    "stop adbd",
                    "start adbd"
            });
            button.setChecked(true);
        }
    }
}
