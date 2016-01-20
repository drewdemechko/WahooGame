package mobileapp.wahoogame;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;

/**
 * Activity for AI Game
 */
public class AIGameActivity extends Activity {

    private ImageView Tiles[] = new ImageView[225];  //Array of ImageView objects to display the entire game board
    private GameBoard currentBoard;                  //Object that holds the data and can communicates with all of the others
    private TextView status;              //Used to display the number rolled by the dice
    private TextView playerTurn;             //Used to display which players turn it is
    private boolean hasRolled = false;       //To check if the dice has been rolled for the current player
    private boolean isGameOver = false;
    private int roll;                        //Holds the value of current roll
    private String winner;
    private Context thisActivity = this;
    private AlertDialog.Builder EndofGame;


    //Sets up new blank Wahoo board---------------------------------------------------------------
    public void startGame() {

        //Create new GameBoard object to store all data
        currentBoard = new GameBoard();

        //Draws blank 15x15 grid that will represent the board
        GridLayout Board = (GridLayout) findViewById(R.id.GridLayout);

        //Add new imageview objects to layout a blank Board
        for (int i = 0; i < Tiles.length; i++) {
            Tiles[i] = new ImageView(this); // keep a reference of the new ImageViews in array Tiles[]
            Board.addView(Tiles[i]);
        }

        //allLocations holds the order of each hole location
        int[] allLocations = {//start of all
                0,16,32,48, //starting (0)
                14,28,42,56,
                224,208,192,176,
                210,196,182,168, //end of starting (15)
                75, 76, 77, 78, 79, 80, 65, 50, 35, 20, 5, 6, 7, 8, 9, 24, //main track (16)
                39, 54, 69, 84, 85, 86, 87, 88, 89, 104, 119, 134, 149, 148, 147,
                146, 145, 144, 159, 174, 189, 204, 219, 218, 217, 216, 215, 200, 185,
                170, 155, 140, 139, 138, 137, 136, 135, 120, 105, 90, //end of main track (71)
                22,37,52,67, //home (72)
                118,117,116,115,
                202,187,172,157,
                106,107,108,109  //end of home (87)
        };//end of all*/};


        //Draw necessary hole images on the board
        for (int x : allLocations) {
            Tiles[x].setImageResource(R.drawable.emptyhole);
        }

        //Draw all 16 marbles on the board
        for (int x: currentBoard.player1.startingLocations)
            //Tiles[x].setImageResource(findMarbleColor(currentBoard.player1));

        //Add player turn to text box
        playerTurn.setText(currentBoard.findPlayer());
    }

    //-------------------------------------------------------------------------------------
    //dice roll stuff
    ImageView dice_picture;		//reference to dice picture
    Random rng=new Random();	//generate random numbers
    SoundPool dice_sound = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
    int sound_id;		//Used to control sound stream return by SoundPool
    Handler handler;	//Post message to start roll
    Timer timer=new Timer();	//Used to implement feedback to user
    //-----------------------------------------------------------------------------------


//Creation and menu stuff
//----------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_game);

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
