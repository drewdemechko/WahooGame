package mobileapp.wahoogame;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;


public class GameActivity extends Activity {

    //The skeleton class, used to display graphics only


    ImageView Tiles[] = new ImageView[225]; //Array of ImageVies displaying holes and marbles
    private boolean pieceSelected = false;  //True if piece is selected
    GameBoard currentBoard;                 //GameBoard object
    private TextView diceThrow;             //TextView displaying number on dice
    private boolean gameOver = false;       //True if game is over





    //StartGame sets up new game
    public void startGame() {

        //Create new GameBoard object to store data
        currentBoard = new GameBoard();

        //TextView diceThrow = (TextView)findViewById(R.id.diceThrow);

        //Draws blank 15x15 grid that will represent the board
        GridLayout Board = (GridLayout) findViewById(R.id.GridLayout);


        //Add new imageview objects to Board and set default resource to blank wood tiles
        for (int i = 0; i < Tiles.length; i++) {
            Tiles[i] = new ImageView(this); // keep reference of the new ImageViews in Tiles[]
            Board.addView(Tiles[i]);
            //Tiles[i].setImageResource(R.drawable.woodtile);
            //currentBoard.holes[i].Location
        }

        //Draw hole images on board
        for (int x : currentBoard.getallHoleLocations()) {
            Tiles[x].setImageResource(R.drawable.emptyhole);


        }

        //Test of new images
        Tiles[168].setImageResource(R.drawable.bluemarble);
        Tiles[168].setOnClickListener(new ImageClickListener(168));
        Tiles[182].setImageResource(R.drawable.bluemarble);
        Tiles[182].setOnClickListener(new ImageClickListener(182));
        Tiles[196].setImageResource(R.drawable.bluemarble);
        Tiles[196].setOnClickListener(new ImageClickListener(196));
        Tiles[210].setImageResource(R.drawable.bluemarble);
        Tiles[210].setOnClickListener(new ImageClickListener(210));

        //currentBoard.drawBoard();
    }

    //currentGame runs until game ends
    private void currentGame(){

        //Loops until a player wins
        while(gameOver == false){



        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        diceThrow = (TextView) findViewById(R.id.diceThrow);
        //Draw Game Board
        startGame();
        //currentGame();
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

    public void rollDice(View v){
        int roll;
        roll = currentBoard.getCurrentRoll();
        String stringRoll = Integer.toString(roll);

        diceThrow.setText(stringRoll);
    }



    private class ImageClickListener implements View.OnClickListener {

        int location;

        public ImageClickListener(int location){this.location = location;}

        public void onClick(View view){

            if(pieceSelected == false){
                Tiles[location].setImageResource(R.drawable.selectedbluemarble);
                pieceSelected = true;
            }
            else
            {
                Tiles[location].setImageResource(R.drawable.bluemarble);
                pieceSelected = false;
            }


        }

    }

}

