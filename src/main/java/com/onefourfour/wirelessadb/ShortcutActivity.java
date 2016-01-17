package com.onefourfour.wirelessadb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ShortcutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent shortcutIntent = new Intent("com.onefourfour.wirelessadb.ShortcutActivity");
        Intent.ShortcutIconResource iconResource = Intent.ShortcutIconResource.fromContext(this, R.mipmap.ic_launcher);
        Log.d("SHORCTUT ACTIVITY:", "IN ACTIVITY");
        // The result we are passing back from this activity
        Intent intent = new Intent(this, ToggleActivity.class);
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "Toggle");
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconResource);
        setResult(RESULT_OK, intent);

        finish(); // Must call finish for result to be returned immediately
    }
}
