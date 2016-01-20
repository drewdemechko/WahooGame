package mobileapp.wahoogame;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

//---------------  Title Screen -----------------------------------------------

public class TitleActivity extends Activity {

    public void openOptionsActivity(View v) {
        //Opens a new Options Activity
        Intent OptionsActivity = new Intent(TitleActivity.this, OptionsActivity.class);
        startActivity(OptionsActivity);
    }

    public void openRulesActivity(View v){
        //Opens the rules activity
        Intent RulesActivity = new Intent(TitleActivity.this, RulesActivity.class);
        startActivity(RulesActivity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
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
