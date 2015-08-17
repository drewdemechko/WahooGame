package mobileapp.wahoogame;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

    public void openGameActivity(View v) {
        //Opens a new Game Activity
        Intent GameActivity = new Intent(MainActivity.this, GameActivity.class);
        startActivity(GameActivity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Controls actions when menu items are pressed
        switch (item.getItemId()) {
            case R.id.action_exit:
                moveTaskToBack(true);
                return true;
            case R.id.action_settings:
                //Should open up a new settings activity
                return true;
            default:
                return false;
        }
    }
}
