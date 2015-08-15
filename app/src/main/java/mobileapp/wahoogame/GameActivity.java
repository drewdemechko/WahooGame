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

    private int roll;                        //Holds the value of current roll
    private String winner;

    Drawable KNOCKEDOFF;

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

        //Add player turn to text box
        playerTurn.setText(currentBoard.findPlayer());
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
                return R.drawable.blueselectedmarble;
            case "red":
                return R.drawable.redselectedmarble;
            case "yellow":
                return R.drawable.yellowselectedmarble;
            case "green":
                return R.drawable.greenselectedmarble;
            default:
                return R.drawable.placeholder;
        }
    }

    //Returns image of player's ghost marble
    public int findGhostMarbleImage(Player p){
        switch (p.getColor()) {
            case "blue":
                return R.drawable.blueghostmarble;
            case "red":
                return R.drawable.redghostmarble;
            case "yellow":
                return R.drawable.yellowghostmarble;
            case "green":
                return R.drawable.greenghostmarble;
            default:
                return R.drawable.placeholder;
        }

    }

    //Used when roll dice button is clicked by user
    public void rollDice(View v) {

            if (!hasRolled)
            {
                currentBoard.setDiceRoll(); //rolls the dice
                roll = currentBoard.getCurrentRoll(); //stores value of roll
                String stringRoll = Integer.toString(roll);

                diceThrow.setText(stringRoll);
                hasRolled = true;
            }

                //check if legal move is possible
                if(!currentBoard.isLegal())
                {
                    currentBoard.nextTurn(); //Next player's turn
                    playerTurn.setText(currentBoard.findPlayer());
                    hasRolled = false;  //Reset dice
                    diceThrow.setText("Please roll"); //Warns the user to roll the dice before making a move
               }
    }

    public void clearBoard()
    {
        //Reset graphics on board
    }

    //Listens for hole images being clicked on
    private class HoleImageClickListener implements View.OnClickListener {

        private int location;   //Holds hole location that has been clicked

        public HoleImageClickListener(int location) {
            this.location = location;
        }


        public void onClick(View view) {
                        if(hasRolled)
                            move();
    }

        //Communicates with GameBoard class make a move
        public void move() {

            //Selects current players marble
            if ((location == currentBoard.current.marble1 || location == currentBoard.current.marble2
                    || location == currentBoard.current.marble3 || location == currentBoard.current.marble4)
                    && (hasChosenMarble == false)) {


                marbleLocation = location; //Stores location of marble selected
                savedCurrentMarbleImage = Tiles[location].getDrawable(); //Keeps current image saved


                //Shows available move
                newMarbleLocation = currentBoard.requestMove(location);

                if(newMarbleLocation != 666) {
                    hasChosenMarble = true;
                    Tiles[location].setImageResource(findSelectedMarbleImage(currentBoard.current)); //Highlight the current marble
                    savedFutureImage = Tiles[newMarbleLocation].getDrawable();
                    Tiles[newMarbleLocation].setImageResource(findGhostMarbleImage(currentBoard.current));
                }

            //Moves the chosen marble to its new location
            } else if (hasChosenMarble && location == marbleLocation) {

                hasChosenMarble = false;
                currentBoard.requestedMove();   //Changes hole properties
                newMarbleLocation = currentBoard.requestMove(location); //Returns location of available move, if any

                if(newMarbleLocation != 666) {

                    if(currentBoard.KNOCKEDOFFTRUE == true)
                    {
                        Tiles[currentBoard.KNOCKEDOFFNEWLOCATION].setImageResource(findMarbleColor(currentBoard.KNOCKEDOFF));
                        currentBoard.KNOCKEDOFFTRUE = false;
                    }

                    Tiles[marbleLocation].setImageResource(R.drawable.emptyhole);//Draws image to area the marble moved away from
                    Tiles[newMarbleLocation].setImageDrawable(savedCurrentMarbleImage);

                    if(currentBoard.isGameOver()){
                        winner = currentBoard.findWinner();
                        playerTurn.setText(winner);
                    }

                    hasRolled = false;  //Reset dice

                    //Let's user roll until he/she does not roll a 1 or 6
                    if (currentBoard.getCurrentRoll() != 1 && currentBoard.getCurrentRoll() != 6) {
                        currentBoard.nextTurn(); //Next player's turn
                        playerTurn.setText(currentBoard.findPlayer());
                        diceThrow.setText("Please roll"); //Warns the user to roll the dice before making a move
                    }
                }
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
}

