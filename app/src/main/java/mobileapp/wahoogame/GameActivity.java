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
        //will have very little data to keep track of

    ImageView Tiles[] = new ImageView[225];  //Array of ImageView objects to display the entire game board
    GameBoard currentBoard;                  //Object that holds the data and can communicates with all of the others
    private TextView diceThrow;              //Used to display the number rolled by the dice
    private TextView playerTurn;             //Used to display which players turn it is
    private boolean hasRolled = false;       //To check if the dice has been rolled for the current player
    private boolean isGameOver = false;
    private boolean hasChosenMarble = false; //To check if user has chosen a marble they want to see the possible moves for

    private int marbleLocation;              //Copies the marbles location that the user clicked on
    private int newMarbleLocation;           //Where to draw the new marble

    private Drawable savedCurrentMarbleImage;//Copies marble image depending on players color
    private Drawable savedFutureImage;       //Copies image of newMarbleLocation in case user decides to NOT move

    private int roll;                       //Holds the value of current roll

    //Sets up new blank Wahoo board
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

        //Draw necessary hole images on the board
        for (int x : currentBoard.getallHoleLocations()) {
            Tiles[x].setImageResource(R.drawable.emptyhole);
            Tiles[x].setOnClickListener(new HoleImageClickListener(x));
        }

        //Draw all 16 marbles on the board
        for (int x: currentBoard.player1.startingLocations)
            Tiles[x].setImageResource(findMarbleColor(currentBoard.player1));
        for (int x: currentBoard.player2.startingLocations)
            Tiles[x].setImageResource(findMarbleColor(currentBoard.player2));
        for (int x: currentBoard.player3.startingLocations)
            Tiles[x].setImageResource(findMarbleColor(currentBoard.player3));
        for (int x: currentBoard.player4.startingLocations)
            Tiles[x].setImageResource(findMarbleColor(currentBoard.player4));
    }

    //Returns image of player's marble
    public int findMarbleColor(Player p)
    {
        switch(p.getColor()) {
            case "blue":
                return R.drawable.bluemarble;
            case "red":
                return R.drawable.redmarble;
            case "yellow":
                return R.drawable.yellowmarble;
            case "green":
                return R.drawable.greenmarble;
            default:
                return R.drawable.placeholder;
        }
    }

    //Returns image of player's selected marble
    public int findSelectedMarbleImage(Player p) {
        switch (p.getColor()) {
            case "blue":
                return R.drawable.selectedbluemarble;
            case "red":
                return R.drawable.selectedredmarble;
            case "yellow":
                return R.drawable.selectedyellowmarble;
            case "green":
                return R.drawable.selectedgreenmarble;
            default:
                return R.drawable.placeholder;
        }
    }

    //Initiated at start up of activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        diceThrow = (TextView) findViewById(R.id.diceThrow);
        playerTurn = (TextView) findViewById(R.id.playerturn);
        startGame();
    }

    //Creates menu bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    //Adds listener for menu bar
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

    //Used when roll dice button is clicked by user
    public void rollDice(View v) {

        //if user has not rolled for his/her turn yet
        try {
            if (!hasRolled) {
                currentBoard.setDiceRoll(); //rolls the dice
                roll = currentBoard.getCurrentRoll(); //stores value of roll
                String stringRoll = Integer.toString(roll);

                diceThrow.setText(stringRoll);
                hasRolled = true;
            }
        }catch(Exception e)
        {
            //fail to roll dice (Safety net for program error and debugging purposes), send error message to user
        }
    }

    //Listens for hole images being clicked on
    private class HoleImageClickListener implements View.OnClickListener {

        private int location;   //Holds hole location that has been clicked

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
                    //fail to click on screen (Safety net for program error and debugging purposes), send error message to user
                }
    }

        //Communicates with GameBoard class make a move
        public void move() {

            //Selects current players marble
            if ((location == currentBoard.current.marble1 || location == currentBoard.current.marble2
                    || location == currentBoard.current.marble3 || location == currentBoard.current.marble4)
                    && (hasChosenMarble == false)) {

                hasChosenMarble = true;
                marbleLocation = location; //Stores location of marble selected
                savedCurrentMarbleImage = Tiles[location].getDrawable(); //Keeps current image saved
                Tiles[location].setImageResource(findSelectedMarbleImage(currentBoard.current)); //Highlight the current marble

                //Shows available move
                newMarbleLocation = currentBoard.requestMove(location);
                savedFutureImage = Tiles[newMarbleLocation].getDrawable();
                Tiles[newMarbleLocation].setImageResource(R.drawable.placeholder); //THIS WILL BE REPLACED WITH A GHOST MARBLE

            //Moves the chosen marble to its new location
            } else if (hasChosenMarble && location == marbleLocation) {

                hasChosenMarble = false;
                newMarbleLocation = currentBoard.requestMove(location); //Returns location of available move, if any
                Tiles[marbleLocation].setImageResource(R.drawable.emptyhole);//Draws image to area the marble moved away from
                Tiles[newMarbleLocation].setImageDrawable(savedCurrentMarbleImage);

                currentBoard.nextTurn(); //Next player's turn
                hasRolled = false;  //Reset dice
                diceThrow.setText("Please roll"); //Warns the user to roll the dice before making a move

            //User wants to see all other options
                //decided not to move the marble that is selected
            } else if (hasChosenMarble && (location == currentBoard.current.marble1 || location == currentBoard.current.marble2
                    || location == currentBoard.current.marble3 || location == currentBoard.current.marble4)
                    ) {

                Tiles[marbleLocation].setImageDrawable(savedCurrentMarbleImage);
                Tiles[newMarbleLocation].setImageDrawable(savedFutureImage);
                hasChosenMarble = false;

                //Catches any other possible variation (should not ever be thrown, but just in case
            } else {
                //Do nothing (to discuss if we should return error here)
                    //I think this will be used if no move can be made
                        //when method is added (isLegalMove())
            }
        }
    }
}

