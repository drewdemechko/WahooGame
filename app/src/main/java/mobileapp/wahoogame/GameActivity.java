package mobileapp.wahoogame;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends Activity {

    //The skeleton class, used to display graphics only
        //will have very little data to keep track of

    private ImageView Tiles[] = new ImageView[225];  //Array of ImageView objects to display the entire game board
    private GameBoard currentBoard;                  //Object that holds the data and can communicates with all of the others
    private TextView status;              //Used to display the number rolled by the dice
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

    private Context thisActivity = this;
    private AlertDialog.Builder EndofGame;

    private Drawable KNOCKEDOFF;


    //-------------------------------------------------------------------------------------
    //dice roll stuff
    ImageView dice_picture;		//reference to dice picture
    Random rng=new Random();	//generate random numbers
    SoundPool dice_sound = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
    int sound_id;		//Used to control sound stream return by SoundPool
    Handler handler;	//Post message to start roll
    Timer timer=new Timer();	//Used to implement feedback to user

    //-----------------------------------------------------------------------------------

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
    /*
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
    */

    public void clearBoard()
    {
        //Reset game activity
        this.recreate();
    }

    public void clearData()
    {
        //Reset data by creating a blank class
        currentBoard = new GameBoard();
    }

    //Starts a new game when new game is selected
    private class YesClickListener implements AlertDialog.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            clearBoard();
            clearData();
        }
    }

    private class MenuClickListener implements AlertDialog.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            Intent i = new Intent(getBaseContext(), MainActivity.class);
            startActivity(i);

        }
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
            if ((location == currentBoard.current.getMarbleLoc1() || location == currentBoard.current.getMarbleLoc2()
                    || location == currentBoard.current.getMarbleLoc3() || location == currentBoard.current.getMarbleLoc4())
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
                    status.setText("Choose Move");

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
                    status.setText("Please Roll");

                    if(currentBoard.isGameOver()){
                        winner = currentBoard.findWinner();
                        playerTurn.setText(winner);

                        //Displays a dialog at the end of the game
                        EndofGame = new AlertDialog.Builder(thisActivity);
                        EndofGame.setMessage(currentBoard.current.getColor() + " won!"); //Displays winner
                        EndofGame.setCancelable(false);
                        YesClickListener yes = new YesClickListener();
                        EndofGame.setPositiveButton("New game", yes);
                        MenuClickListener menu = new MenuClickListener();
                        EndofGame.setNegativeButton("Main Menu", menu);
                        EndofGame.create();
                        EndofGame.show();


                    }

                    hasRolled = false;  //Reset dice

                    //Let's user roll until he/she does not roll a 1 or 6
                    if (currentBoard.getCurrentRoll() != 1 && currentBoard.getCurrentRoll() != 6) {
                        currentBoard.nextTurn(); //Next player's turn
                        playerTurn.setText(currentBoard.findPlayer());
                        status.setText("Please roll"); //Warns the user to roll the dice before making a move
                    }
                }
            //User wants to see all other options
                //decided not to move the marble that is selected
            } else if (hasChosenMarble && (location == currentBoard.current.getMarbleLoc1() ||
                    location == currentBoard.current.getMarbleLoc2() || location == currentBoard.current.getMarbleLoc3()
                    || location == currentBoard.current.getMarbleLoc4())
                    ) {

                Tiles[marbleLocation].setImageDrawable(savedCurrentMarbleImage);
                Tiles[newMarbleLocation].setImageDrawable(savedFutureImage);
                status.setText("Choose Marble");
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
        status = (TextView) findViewById(R.id.status);
        playerTurn = (TextView) findViewById(R.id.playerturn);

        //dice throw stuff-------------
        //load dice sound
        sound_id=dice_sound.load(this,R.raw.shake_dice,1);
        //get reference to image widget
        dice_picture = (ImageView) findViewById(R.id.imageView1);
        //link handler to callback
        handler=new Handler(callback);
        //---------------------------

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

    public void returnToMenu(View v) {
        //Opens a new Game Activity
        Intent MainActivity = new Intent(GameActivity.this, GameActivity.class);
        startActivity(MainActivity);
    }

    //User pressed dice, lets start
    public void HandleClick(View arg0) {
        if(!hasRolled) {
            hasRolled = true;

            currentBoard.setDiceRoll();


            //Show rolling image
            dice_picture.setImageResource(R.drawable.dice3droll);
            //Start rolling sound
            dice_sound.play(sound_id, 1.0f, 1.0f, 0, 0, 1.0f);
            //Pause to allow image to update
            timer.schedule(new Roll(), 400);

        }
    }

    //When pause completed message sent to callback
    class Roll extends TimerTask {
        public void run() {
            handler.sendEmptyMessage(0);
        }
    }

    //Receives message from timer to start dice roll
    Handler.Callback callback = new Handler.Callback() {
        public boolean handleMessage(Message msg) {

                //Get roll result
                //String stringRoll = Integer.toString(currentBoard.getCurrentRoll());
                //status.setText(stringRoll);
                switch(currentBoard.getCurrentRoll()) {
                    case 1:
                        dice_picture.setImageResource(R.drawable.one);
                        break;
                    case 2:
                        dice_picture.setImageResource(R.drawable.two);
                        break;
                    case 3:
                        dice_picture.setImageResource(R.drawable.three);
                        break;
                    case 4:
                        dice_picture.setImageResource(R.drawable.four);
                        break;
                    case 5:
                        dice_picture.setImageResource(R.drawable.five);
                        break;
                    case 6:
                        dice_picture.setImageResource(R.drawable.six);
                        break;
                    default:
                }

            //check if legal move is possible
            if(!currentBoard.isLegal())
            {
                currentBoard.nextTurn(); //Next player's turn
                playerTurn.setText(currentBoard.findPlayer());
                hasRolled = false;  //Reset dice
                status.setText("Please roll"); //Warns the user to roll the dice before making a move

            }
           else status.setText("Choose Marble");


            return true;
        }
    };

    //Clean up
    @Override
    protected void onPause() {
        super.onPause();
        dice_sound.pause(sound_id);
    }
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}

