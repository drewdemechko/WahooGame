package mobileapp.wahoogame;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class GameActivity extends Activity {

    //The skeleton class, used to display graphics

    public void startGame() {

        //Create new GameBoard object to store data
        GameBoard currentBoard = new GameBoard();

        //TextView diceThrow = (TextView)findViewById(R.id.diceThrow);

        //Draws blank 15x15 grid that will represent the board
        GridLayout Board = (GridLayout) findViewById(R.id.GridLayout);
        ImageView Tiles[] = new ImageView[225];

        //Add new imageview objects to Board and set default resource to blank wood tiles
        for (int i = 0; i < Tiles.length; i++) {
            Tiles[i] = new ImageView(this); // keep reference of the new ImageViews in Tiles[]
            Board.addView(Tiles[i]);
            Tiles[i].setImageResource(R.drawable.woodtile);
            //currentBoard.holes[i].Location
        }

        //Draw hole images on board
            for(int x: currentBoard.getallHoleLocations())
            {
                Tiles[x].setImageResource(R.drawable.hole);
            }
        currentBoard.drawBoard();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //Draw Game Board
        startGame();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
