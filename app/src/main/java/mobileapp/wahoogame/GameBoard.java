package mobileapp.wahoogame;

import java.util.Random;

/**
 * Created by Drew Demechko on 8/1/2015.
 */
public class GameBoard {

    private int newRequestedLocation;
    private int oldRequestedLocation;
    private Random diceRoll = new Random();

    public Hole holes[]; //Total hole objects

    private int allHoleLocations[]; //Stores all holes, makes it easier to draw to GameActivity
    private int mainTrackLocations[]; //holes accessible by all, on main track []
    private int homeLocations[];
    private int startingLocations[];
    private int currentRoll; //stores the value of the most recent dice roll

    private boolean winnerFound = false;

    Player player1;
    Player player2;
    Player player3;
    Player player4;

    Player current; //To track current players turn

    public GameBoard()
    {
        //Initialize the constant locations of all holes
        allHoleLocations = new int[]
                {//start of all
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
                };//end of all

        holes = new Hole[88];

        //Creates new hole objects
        for(int i = 0; i < holes.length; i++)
        {
            holes[i] = new Hole();
            holes[i].setGridLocation(allHoleLocations[i]);
        }

        //Creates all 4 players
        createPlayer("blue");
        createPlayer("yellow");
        createPlayer("red");
        createPlayer("green");
    }

    public void nextTurn()
    {
        //Determine who has the next turn
        if(current == null)
            current = player1;
        if(current == player1)
            current = player2;
        else if(current == player2)
            current = player3;
        else if(current == player3)
            current = player4;
        else if(current == player4)
            current = player1;
    }
    public void createPlayer(String c)
    {
        //Checks if there is space for new players
        int[] temphome = new int[16];
        int[] tempstart = new int[16];
        try {
            if (player1 == null) {
                //Set new player object
                player1 = new Player(c);

                for(int i = 0; i <= 3; i++) {
                    //Define starting/home positions
                    holes[i].setFull();
                    holes[i].setPlayer(player1);
                    tempstart[i] = holes[i].getGridLocation();
                    temphome[i] = holes[i+84].getGridLocation();
                }

                //Set player's start/home positions
                //player1.startingLocations = tempstart;
                player1.homeLocations = temphome; //May be able to check if full for isGameOver()
                player1.marble1 = tempstart[0];
                player1.startingLocations[0] = player1.marble1;
                player1.marble2 = tempstart[1];
                player1.startingLocations[1] = player1.marble2;
                player1.marble3 = tempstart[2];
                player1.startingLocations[2] = player1.marble3;
                player1.marble4 = tempstart[3];
                player1.startingLocations[3] = player1.marble4;
                player1.setFirstHole(75);
                player1.setLastHole(105);
                current = player1; //Set players turn
            }
            else if(player2 == null)
            {
                //Set new player object
                player2 = new Player(c);

                for(int i = 4; i <= 7; i++) {
                    //Define starting/home positions
                    holes[i].setFull();
                    holes[i].setPlayer(player2);
                    tempstart[i] = holes[i].getGridLocation();
                    temphome[i] = holes[i+68].getGridLocation();
                }

                //Set player's start/home positions
                //player2.startingLocations = tempstart;
                player2.homeLocations = temphome; //May be able to check if full for isGameOver()
                player2.marble1 = tempstart[4];
                player2.startingLocations[0] = player2.marble1;
                player2.marble2 = tempstart[5];
                player2.startingLocations[1] = player2.marble2;
                player2.marble3 = tempstart[6];
                player2.startingLocations[2] = player2.marble3;
                player2.marble4 = tempstart[7];
                player2.startingLocations[3] = player2.marble4;
                player2.setFirstHole(9);
                player2.setLastHole(7);
            }
            else if(player3 == null)
            {
                //Set new player object
                player3 = new Player(c);

                for(int i = 8; i <= 11; i++) {
                    //Define starting/home positions
                    holes[i].setFull();
                    holes[i].setPlayer(player3);
                    tempstart[i] = holes[i].getGridLocation();
                    temphome[i] = holes[i+68].getGridLocation();
                }

                //Set player's start/home positions
                //player3.startingLocations = tempstart;
                player3.homeLocations = temphome; //May be able to check if full for isGameOver()
                player3.marble1 = tempstart[8];
                player3.startingLocations[0] = player3.marble1;
                player3.marble2 = tempstart[9];
                player3.startingLocations[1] = player3.marble2;
                player3.marble3 = tempstart[10];
                player3.startingLocations[2] = player3.marble3;
                player3.marble4 = tempstart[11];
                player3.startingLocations[3] = player3.marble4;
                player3.setFirstHole(149);
                player3.setLastHole(119);
            }
            else if(player4 == null)
            {
                //Set new player object
                player4 = new Player(c);

                for(int i = 12; i <= 15; i++) {
                    //Define starting/home positions
                    holes[i].setFull();
                    holes[i].setPlayer(player4);
                    tempstart[i] = holes[i].getGridLocation();
                    temphome[i] = holes[i+68].getGridLocation();
                }

                //Set player's start/home positions
                //player4.startingLocations = tempstart;
                player4.homeLocations = temphome; //May be able to check if full for isGameOver()
                player4.marble1 = tempstart[12];
                player4.startingLocations[0] = player4.marble1;
                player4.marble2 = tempstart[13];
                player4.startingLocations[1] = player4.marble2;
                player4.marble3 = tempstart[14];
                player4.startingLocations[2] = player4.marble3;
                player4.marble4 = tempstart[15];
                player4.startingLocations[3] = player4.marble4;
                player4.setFirstHole(215);
                player4.setLastHole(217);
            }
        }catch(Exception e)
        {
            //Display error message that no more players are allowed to join!!
        }
    }

    public void clearBoard()
    {
        //Resets the data back to default values for new game
    }

    public void setMove(int startLoc, int endLoc)
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
        int tempOldLoc = getOldRequestedLocation();
        int tempNewLoc = getNewRequestedLocation();
        int tempOldIndex;
        int tempNewIndex;
        boolean inStartingLocation = false;
        int startingCount = 0;
        boolean inHomeLocation = false;
        int homeCount = 0;
        boolean completedTurn = false;

        //Check if marble being moved is in start
        for(int x: Hole.startingLocations)
        {
            if(x == tempOldLoc) {
                inStartingLocation = true;
                startingCount++;
            }
        }

        for(int x: Hole.homeLocations)
        {
            if(x == tempOldLoc)
            {
                inHomeLocation = true;
                homeCount++;
            }
        }

        //Finds index location in allholesarray
        tempOldIndex = Hole.FindHole(tempOldLoc);
        tempNewIndex = Hole.FindHole(tempNewLoc);

        //If selected marble is in home location or on main track
        if(inStartingLocation == false)
        {
            if(tempOldIndex + getCurrentRoll() == tempNewIndex)
            {
                //Find which marble the user wants to move
                if(current.marble1 == tempOldLoc)
                {
                    current.marble1 = tempNewLoc;
                }
                else if(current.marble2 == tempOldLoc)
                {
                    current.marble2 = tempNewLoc;
                }
                else if(current.marble3 == tempOldLoc)
                {
                    current.marble3 = tempNewLoc;
                }
                else if(current.marble4 == tempOldLoc)
                {
                    current.marble4 = tempNewLoc;
                }

                completedTurn = true;
            }
            else
            {
                //return error, could not move
            }

        }
        //If selected marble is in starting location
        else if((getCurrentRoll() == 6 || getCurrentRoll() == 1) && tempNewLoc == current.getfirstHole() && inStartingLocation)
        {
        //Find which marble the user wants to move
            if(current.marble1 == tempOldLoc)
            {
                current.marble1 = tempNewLoc;
            }
            else if(current.marble2 == tempOldLoc)
            {
                current.marble2 = tempNewLoc;
            }
            else if(current.marble3 == tempOldLoc)
            {
                current.marble3 = tempNewLoc;
            }
            else if(current.marble4 == tempOldLoc)
            {
                current.marble4 = tempNewLoc;
            }
            completedTurn = true;
        }
        else
        {
            //Return error, illegal move
            completedTurn = false;
        }

        if(completedTurn)
        {
            //holes[tempNewIndex] = FILLHOLE
            //holes[tempNewIndex] = SetCurrentPlayerColor
            //holes[tempOldIndex] = SETEMPTY
            //holes[tempOldIndex] = SetColor to "none"
            return true;
        }
        else
        {
            return false;
            //knockoff();

            //This will be a pretty decent sized method because it will be
            // checking for various conditions
        }
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

    public void setDiceRoll()
    {
        //currentRoll = diceRoll.nextInt(6)+1;
        currentRoll = 6;
    }
    public int getCurrentRoll()
    {
        return currentRoll;
    }

    public boolean isGameOver()
    {
       //Checks if one player has all 4 home holes filled
        // will run after each player turn
      if(holes[72].isEmpty() == false && holes[73].isEmpty() == false && holes[74].isEmpty() == false && holes[75].isEmpty() == false)
          winnerFound = true;
      else if(holes[76].isEmpty() == false && holes[77].isEmpty() == false && holes[78].isEmpty() == false && holes[79].isEmpty() == false)
          winnerFound = true;
      else if(holes[80].isEmpty() == false && holes[81].isEmpty() == false && holes[82].isEmpty() == false && holes[83].isEmpty() == false)
          winnerFound = true;
      else if(holes[84].isEmpty() == false && holes[85].isEmpty() == false && holes[86].isEmpty() == false && holes[87].isEmpty() == false)
          winnerFound = true;
        else
          winnerFound = false;

        return winnerFound;
    }

    //Value pulled from GameActivity actionlistener
    public void setNewRequested(int r)
    {
        newRequestedLocation = r;
    }

    public int getNewRequestedLocation()
    {
        return newRequestedLocation;
    }

    public void setOldRequested(int r)
    {
        oldRequestedLocation = r;
    }

    public int getOldRequestedLocation()
    {
        return oldRequestedLocation;
    }
}
