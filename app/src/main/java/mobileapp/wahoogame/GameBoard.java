package mobileapp.wahoogame;

import java.util.Random;

/**
 * Created by Drew Demechko on 8/1/2015.
 */
public class GameBoard {

    private Random diceRoll = new Random();

    public Hole holes[]; //Total hole objects

    private int allHoleLocations[]; //Stores all holes, makes it easier to draw to GameActivity
    private int mainTrackLocations[]; //holes accessible by all, on main track []
    private int homeLocations[];
    private int startingLocations[];
    private int currentRoll; //stores the value of the most recent dice roll

    public GameBoard()
    {
        //Initialize the constant locations of all holes
        allHoleLocations = new int[]
                {//start of all
                 0,16,32,48, //starting
                 14,28,42,56,
                 224,208,192,176,
                 210,196,182,168, //end of starting
                 75, 76, 77, 78, 79, 80, 65, 50, 35, 20, 5, 6, 7, 8, 9, 24, //main track
                 39, 54, 69, 84, 85, 86, 87, 88, 89, 104, 119, 134, 149, 148, 147,
                 146, 145, 144, 159, 174, 189, 204, 219, 218, 217, 216, 215, 200, 185,
                 170, 155, 140, 139, 138, 137, 136, 135, 120, 105, 90, //end of main track
                 22,37,52,67, //home
                 118,117,116,115,
                 202,187,172,157,
                 106,107,108,109  //end of home
                };//end of all

        holes = new Hole[88];

        //Creates new hole objects
        for(int i = 0; i < holes.length; i++)
        {
            holes[i] = new Hole();
            holes[i].setGridLocation(allHoleLocations[i]);
        }

    }

    public void clearBoard()
    {
        //Resets the data back to default values for new game
    }

    public void setMove(String color, int startLoc, int endLoc)
    {

        if(isLegalMove())
        {
        //If move is legal, advance
        }
        else
        {
        //If move is illegal, return error message, we can do this with
        // a try catch if we choose
        }
    }

    public void GameOver()
    {
        //Output winner message
        //Clear board
        // the game is over
        //  output winner
        //   play again? which will call startgame in the GameActivity class

        // clear board
        clearBoard();
    }

    public void knockOff(){
        //Communicates with the Hole class to empty the hole and send marble
        // back to starting position
    }

    public boolean isLegalMove()
    {
        //if(something)
        //knockoff();

        //This will be a pretty decent sized method because it will be
        // checking for various conditions
        return true; //temp var
    }

    public int[] getallHoleLocations()
    {
        return allHoleLocations;
    }

    public int[] getMainTrackLocations()
    {
       return mainTrackLocations;
    }

    public int[] getHomeLocations()
    {
        return homeLocations;
    }

    public int[] getStartingLocations()
    {
        return startingLocations;
    }

    public int diceRollValue()
    {
        //some condition statements, if turn
        currentRoll = diceRoll.nextInt(6)+1;
        return currentRoll; //return 0 if unable to roll
    }
}
