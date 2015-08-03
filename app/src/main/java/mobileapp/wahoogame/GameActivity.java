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
    private TextView playerTurn;            //Display current player
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

        //Blue Marbles
        Tiles[168].setImageResource(R.drawable.bluemarble);
        Tiles[168].setOnClickListener(new BlueImageClickListener(168));
        Tiles[182].setImageResource(R.drawable.bluemarble);
        Tiles[182].setOnClickListener(new BlueImageClickListener(182));
        Tiles[196].setImageResource(R.drawable.bluemarble);
        Tiles[196].setOnClickListener(new BlueImageClickListener(196));
        Tiles[210].setImageResource(R.drawable.bluemarble);
        Tiles[210].setOnClickListener(new BlueImageClickListener(210));

        //Red Marbles
        Tiles[0].setImageResource(R.drawable.redmarble);
        Tiles[0].setOnClickListener(new RedImageClickListener(0));
        Tiles[16].setImageResource(R.drawable.redmarble);
        Tiles[16].setOnClickListener(new RedImageClickListener(16));
        Tiles[32].setImageResource(R.drawable.redmarble);
        Tiles[32].setOnClickListener(new RedImageClickListener(32));
        Tiles[48].setImageResource(R.drawable.redmarble);
        Tiles[48].setOnClickListener(new RedImageClickListener(48));

        //Green Marbles
        Tiles[176].setImageResource(R.drawable.greenmarble);
        Tiles[176].setOnClickListener(new GreenImageClickListener(176));
        Tiles[192].setImageResource(R.drawable.greenmarble);
        Tiles[192].setOnClickListener(new GreenImageClickListener(192));
        Tiles[208].setImageResource(R.drawable.greenmarble);
        Tiles[208].setOnClickListener(new GreenImageClickListener(208));
        Tiles[224].setImageResource(R.drawable.greenmarble);
        Tiles[224].setOnClickListener(new GreenImageClickListener(224));

        //Yellow Marbles
        Tiles[56].setImageResource(R.drawable.yellowmarble);
        Tiles[56].setOnClickListener(new YellowImageClickListener(56));
        Tiles[42].setImageResource(R.drawable.yellowmarble);
        Tiles[42].setOnClickListener(new YellowImageClickListener(42));
        Tiles[28].setImageResource(R.drawable.yellowmarble);
        Tiles[28].setOnClickListener(new YellowImageClickListener(28));
        Tiles[14].setImageResource(R.drawable.yellowmarble);
        Tiles[14].setOnClickListener(new YellowImageClickListener(14));


    }

    //currentGame runs until game ends
    private void currentGame(){
        currentTurn();

        //Loops until a player wins
        //while(gameOver == false){


        //}
    }

    private void currentTurn(){
        playerTurn.setText("Drew Sucks!");
        gameOver = true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        diceThrow = (TextView) findViewById(R.id.diceThrow);
        playerTurn = (TextView) findViewById(R.id.playerturn);
        //Draw Game Board

        startGame();
        currentGame();
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


    //Listener for Blue Marbles
    private class BlueImageClickListener implements View.OnClickListener {

        int location;

        public BlueImageClickListener(int location){this.location = location;}

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

    //Listener for red marbles
    private class RedImageClickListener implements View.OnClickListener {

        int location;

        public RedImageClickListener(int location){this.location = location;}

        public void onClick(View view){

            if(pieceSelected == false){
                Tiles[location].setImageResource(R.drawable.selectedredmarble);
                pieceSelected = true;
            }
            else
            {
                Tiles[location].setImageResource(R.drawable.redmarble);
                pieceSelected = false;
            }


        }

    }

    //Listener for green marbles
    private class GreenImageClickListener implements View.OnClickListener {

        int location;

        public GreenImageClickListener(int location){this.location = location;}

        public void onClick(View view){

            if(pieceSelected == false){
                Tiles[location].setImageResource(R.drawable.selectedgreenmarble);
                pieceSelected = true;
            }
            else
            {
                Tiles[location].setImageResource(R.drawable.greenmarble);
                pieceSelected = false;
            }


        }

    }

    //Listener for yellow marbles
    private class YellowImageClickListener implements View.OnClickListener {

        int location;

        public YellowImageClickListener(int location){this.location = location;}

        public void onClick(View view){

            if(pieceSelected == false){
                Tiles[location].setImageResource(R.drawable.selectedyellowmarble);
                pieceSelected = true;
            }
            else
            {
                Tiles[location].setImageResource(R.drawable.yellowmarble);
                pieceSelected = false;
            }


        }

    }

}

