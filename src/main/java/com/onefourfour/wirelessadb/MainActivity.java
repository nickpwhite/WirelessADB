package com.onefourfour.wirelessadb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ToggleButton;

import java.io.DataOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements AdapterView.OnClickListener {

    ToggleButton button;

    public static void sudo(String...strings) {
        try {
            Process su = Runtime.getRuntime().exec("su");
            DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());

            for(String s : strings) {
                outputStream.writeBytes(s+"\n");
                outputStream.flush();
            }

            outputStream.writeBytes("exit\n");
            outputStream.flush();

            outputStream.close();

            try {
                su.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (ToggleButton) findViewById(R.id.adb_button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(button.isChecked()) {
            sudo(new String[] {
                    "setprop service.adb.tcp.port 5555",
                    "stop adbd",
                    "start adbd"
            });
        } else {
            sudo(new String[] {
                    "setprop service.adb.tcp.port -1",
                    "stop adbd",
                    "start adbd"
            });
        }
    }
}
