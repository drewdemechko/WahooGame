package mobileapp.wahoogame;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Activity for Options Screen
 */
public class OptionsActivity extends Activity {
    private TextView title;

    //open game activity
    public void openGameActivity2(View v) {
        //Opens a new gaem Activity
        Intent GameActivity = new Intent(OptionsActivity.this, GameActivity.class);
        startActivity(GameActivity);
    }

    //open AI game activity
    public void openAIGameActivity(View v){
        Intent AIGameActivity = new Intent(OptionsActivity.this, AIGameActivity.class);
        startActivity(AIGameActivity);
    }





    //***********************************************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        //create title text box
        title = (TextView) findViewById(R.id.options_title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options, menu);
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
