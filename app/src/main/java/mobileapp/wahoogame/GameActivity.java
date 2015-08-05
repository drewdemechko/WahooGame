package mobileapp.wahoogame;

import android.app.Activity;
import android.graphics.drawable.Drawable;
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
    private boolean hasRolled = false;
    private boolean gameOver = false;       //True if game is over
    private boolean choseMarble = false;
    private boolean choseDestination = false;
    private int chosenMarbleLocation;
    private Drawable savedImage; //Copies marble image so that it can redraw to a new location


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
            Tiles[x].setOnClickListener(new HoleImageClickListener(x));
        }

        //Draw all 4 players marbles to the board
        for (int x: currentBoard.player1.startingLocations)
            Tiles[x].setImageResource(findMarbleColor(currentBoard.player1));

        for (int x: currentBoard.player2.startingLocations)
            Tiles[x].setImageResource(findMarbleColor(currentBoard.player2));

        for (int x: currentBoard.player3.startingLocations)
            Tiles[x].setImageResource(findMarbleColor(currentBoard.player3));

        for (int x: currentBoard.player4.startingLocations)
            Tiles[x].setImageResource(findMarbleColor(currentBoard.player4));
    }

    public int findMarbleColor(Player p)
    {
        if(p.getColor() == "blue")
            return R.drawable.bluemarble;
        else if(p.getColor() == "red")
            return R.drawable.redmarble;
        else if(p.getColor() == "yellow")
            return R.drawable.yellowmarble;
        else if(p.getColor() == "green")
            return R.drawable.greenmarble;
        else
            return R.drawable.placeholder;
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
        if(hasRolled == false) {
            int roll;
            currentBoard.setDiceRoll(); //rolls dice once
            roll = currentBoard.getCurrentRoll(); //stores dice roll value
            String stringRoll = Integer.toString(roll);

            diceThrow.setText(stringRoll);
            hasRolled = true;
        }
        else
        {
            //fail to roll dice, send error message to user
        }
    }

    public void move()
    {

    }
    //Listens for clicks on holes
    private class HoleImageClickListener implements View.OnClickListener {

        int location;

        public HoleImageClickListener(int location) {
            this.location = location;
        }

        public void onClick(View view) {
                    try { //safety net
                        if(hasRolled)
                            move();
                        //else
                            //print error, please roll the dice first
                } catch (Exception e) {
                    //Spit error
                }
    }
        public void move()
        {
            //Selects current players marble and stores it as well as the marble image
            if ((location == currentBoard.current.marble1 || location == currentBoard.current.marble2
                    || location == currentBoard.current.marble3 || location == currentBoard.current.marble4) && (choseMarble == false)) {
                currentBoard.setOldRequested(location);
                choseMarble = true;
                chosenMarbleLocation = location;
                savedImage = Tiles[location].getDrawable(); //Keeps current image saved
                Tiles[location].setImageResource(R.drawable.placeholder);
            } else if (location == chosenMarbleLocation) {
                choseMarble = false;
                Tiles[location].setImageDrawable(savedImage);
            } else if (choseMarble == true && choseDestination == false) {
                choseDestination = true;
                currentBoard.setNewRequested(location);
                //CHECKS IF ISLEGAL move
                if(currentBoard.isLegalMove()) {
                    //
                    Tiles[location].setImageDrawable(savedImage);
                    Tiles[chosenMarbleLocation].setImageResource(R.drawable.emptyhole);
                    choseDestination = false;
                    choseMarble = false;

                    hasRolled = false;
                    currentBoard.nextTurn();
                }
                else
                {
                    //Move not legal
                    //print out error message and reset values to original
                    return;
                }
            }
        }

            /*if(pieceSelected == false){
                Tiles[location].setImageResource(R.drawable.placeholder);
                pieceSelected = true;
            }
            else
            {
                //return error and try another location
            }*/
        }
        public void waitOnMove() {

        }
    }

