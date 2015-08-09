package mobileapp.wahoogame;

import java.util.Random;

//Holds all GameBoard data
    //the middle man for all classes
public class GameBoard {

    private Random diceRoll = new Random();
    public Hole holes[];                        //Stores all hole objects
    private int currentRoll;                    //Stores the value of the most recent dice roll
    private boolean winnerFound = false;        //Checks if a player won the game
    private boolean requestedMove = false;

    //Holds a reference for each player
    Player player1;
    Player player2;
    Player player3;
    Player player4;
    Player current; //Tracks data of the current player, whoever has the turn

    public GameBoard()
    {
        holes = new Hole[88]; //Creates an array of hole objects on the board

        for(int i = 0; i < holes.length; i++)
        {
            holes[i] = new Hole();
            holes[i].setGridLocation(Hole.allHoleLocations[i]); //Set location of each hole on main grid
        }

        //Creates 4 new players
        createPlayer("blue");
        createPlayer("yellow");
        createPlayer("red");
        createPlayer("green");
    }

    //Allows next player to make a move
    public void nextTurn()
    {
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

    //Creates a new Player object
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
                player1.homeLocations = temphome;
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
                current = player1; //Set player 1 to hold the first turn
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
                //Game is full
        }
    }

    public void clearBoard()
    {
        //Resets the data back to default values for new game
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

    //Returns possible move
    public int requestMove(int marbleLocation) {
        //Finds index location in allholesarray
        int tempOldIndex = Hole.FindHole(marbleLocation);
        int tempNewIndex = 666;
        int tempNewHole = 666;

        boolean inStartingLocation = false;
        boolean inHomeLocation = false;
        boolean completedTurn = false;

        //Check if marble being moved is in start
        for (int x : Hole.startingLocations) {
            if (x == marbleLocation)
                inStartingLocation = true;
        }

        //Check if marble being moved is in home
        for (int x : Hole.homeLocations) {
            if (x == marbleLocation)
                inHomeLocation = true;
        }

        //If selected marble is in home location or on main track
        if (inStartingLocation == false) {
            //tempNewIndex = tempOldIndex;
            /*
            //increment tempNewIndex and check if position contains marble of same color
            for(int i = 0; i < getCurrentRoll(); i++){
                tempNewIndex++;

            }
            */
            tempNewIndex = tempOldIndex + getCurrentRoll();
            tempNewHole = Hole.allHoleLocations[tempNewIndex];

            //Find which marble the user wants to move
            //and set new position
            if (current.marble1 == marbleLocation) {
                current.marble1 = tempNewHole;
            } else if (current.marble2 == marbleLocation) {
                current.marble2 = tempNewHole;
            } else if (current.marble3 == marbleLocation) {
                current.marble3 = tempNewHole;
            } else if (current.marble4 == marbleLocation) {
                current.marble4 = tempNewHole;
            }

            for(int i = tempOldIndex+1; i <= tempNewIndex; i++){
                if(holes[i].getColor() == current.getColor())
                    return 666;
            }

            completedTurn = true;
        }

        //If selected marble is in starting location
        else if (inStartingLocation) {
            if (getCurrentRoll() == 6 || getCurrentRoll() == 1) {
                tempNewIndex = Hole.FindHole(current.getfirstHole());
                tempNewHole = Hole.allHoleLocations[tempNewIndex];
                //Find which marble the user wants to move
                //and set new position

                if (current.marble1 == marbleLocation) {
                    current.marble1 = tempNewHole;
                } else if (current.marble2 == marbleLocation) {
                    current.marble2 = tempNewHole;
                } else if (current.marble3 == marbleLocation) {
                    current.marble3 = tempNewHole;
                } else if (current.marble4 == marbleLocation) {
                    current.marble4 = tempNewHole;
                }

                completedTurn = true;
            }
            else
            {
                tempNewHole = 666;
                completedTurn = true;
            }

        }
            if(requestedMove)
            {
                //if(isLegal()) {
                    holes[tempNewIndex].setFull();
                    holes[tempNewIndex].setColor(current.getColor());
                    holes[tempOldIndex].setEmpty();
                    holes[tempOldIndex].setColor("none");
                    requestedMove = false;
               // }
            }
            if (completedTurn) {
                return tempNewHole;
            } else {
                return 666; //WILL CHANGE THIS RETURN
                //knockoff();

            }
    }

    //Checks to see if a legal move is available
    public boolean isLegal(){
        boolean isLegal = true;

        boolean marble1 = true;
        boolean marble2 = true;
        boolean marble3 = true;
        boolean marble4 = true;

        //save current marble locations
        int loc1 = current.marble1;
        int loc2 = current.marble2;
        int loc3 = current.marble3;
        int loc4 = current.marble4;

        //check if each of current player's marbles can move
        if(requestMove(current.marble1) == 666) {marble1 = false;}
        if(requestMove(current.marble2) == 666) {marble2 = false;}
        if(requestMove(current.marble3) == 666) {marble3 = false;}
        if(requestMove(current.marble4) == 666) {marble4 = false;}

        //Return marbles to original values
        current.setMarble1(loc1);
        current.setMarble2(loc2);
        current.setMarble3(loc3);
        current.setMarble4(loc4);

        //if no marble can move, isLegal is false
       if(!marble1 && !marble2 && !marble3 && !marble4){
            isLegal = false;}



        return isLegal;
    }

    //Returns all hole locations
    public int[] getallHoleLocations()
    {
        return Hole.allHoleLocations;
    }

    //Returns all main track locations
    public int[] getMainTrackLocations()
    {
       return Hole.mainTrackLocations;
    }

    //Returns all home locations
    public int[] getHomeLocations()
    {
        return Hole.homeLocations;
    }

    //Returns all starting locations
    public int[] getStartingLocations()
    {
        return Hole.startingLocations;
    }

    //Set dice roll to a new random number (1-6)
    public void setDiceRoll()
    {
        currentRoll = diceRoll.nextInt(6)+1;
        //currentRoll = 6;
        //currentRoll = 1;
    }
    public int getCurrentRoll()
    {
        return currentRoll;
    }

    public void requestedMove()
    {
        requestedMove = true;
    }

    //Returns text for Player Turn
    public String findPlayer() {
        String playerTurn = "";
        if (current.getColor() == "red") {
            playerTurn = "Red's Turn";
        }
        if (current.getColor() == "blue") {
            playerTurn = "Blue's Turn";
        }
        if (current.getColor() == "yellow") {
            playerTurn = "Yellow's Turn";
        }
        if (current.getColor() == "green") {
            playerTurn = "Green's Turn";
        }

        return playerTurn;
    }

    //Checks if one player has all 4 home holes filled
        //Will run after each player's turn
    public boolean isGameOver()
    {
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

        return winnerFound; //Game is over
    }
}
