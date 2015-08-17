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

    Player KNOCKEDOFF;
    int KNOCKEDOFFNEWLOCATION;
    boolean KNOCKEDOFFTRUE = false;

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
                    holes[i].setColor(player1.getColor());
                }

                //Set player's start/home positions
                player1.marble1 = 0;
                player1.startingLocations[0] = player1.marble1;
                player1.marble2 = 16;
                player1.startingLocations[1] = player1.marble2;
                player1.marble3 = 32;
                player1.startingLocations[2] = player1.marble3;
                player1.marble4 = 48;
                player1.startingLocations[3] = player1.marble4;

                player1.homeLocations[0] = 106;
                player1.homeLocations[1] = 107;
                player1.homeLocations[2] = 108;
                player1.homeLocations[3] = 109;

                player1.holes = new int[]{75,76,77,78,79,80,65,50,35,20,5,6,7,8,9,24,39,54,69,84,85,86,87,88,89,
                                            104,119,134,149,148,147,146,145,144,159,174,189,204,219,218,
                                            217,216,215,200,185,170,155,140,139,138,137, 136, 135, 120, 105,
                                            106, 107, 108, 109};

                current = player1; //Set player 1 to hold the first turn
            }
            else if(player2 == null)
            {
                //Set new player object
                player2 = new Player(c);

                for(int i = 4; i <= 7; i++) {
                    //Define starting/home positions
                    holes[i].setFull();
                    holes[i].setColor(player2.getColor());
                }

                //Set player's start/home positions
                player2.marble1 = 14;
                player2.startingLocations[0] = player2.marble1;
                player2.marble2 = 28;
                player2.startingLocations[1] = player2.marble2;
                player2.marble3 = 42;
                player2.startingLocations[2] = player2.marble3;
                player2.marble4 = 56;
                player2.startingLocations[3] = player2.marble4;

                player2.homeLocations[0] = 22;
                player2.homeLocations[1] = 37;
                player2.homeLocations[2] = 52;
                player2.homeLocations[3] = 67;

                player2.holes = new int[]{9,24,39,54,69,84,85,86,87,88,89,104,119,134,149,148,147,146,145,144,159,
                                            174,189,204,219,218,217,216,215,200,185,170,155,140,139,138,
                                            137,136,135,120,105,90,75,76,77,78,79,80,65,50,35,20,5,6,7,
                                            22,37,52,67};
            }
            else if(player3 == null)
            {
                //Set new player object
                player3 = new Player(c);

                for(int i = 8; i <= 11; i++) {
                    //Define starting/home positions
                    holes[i].setFull();
                    holes[i].setColor(player3.getColor());
                }

                //Set player's start/home positions
                player3.marble1 = 224;
                player3.startingLocations[0] = player3.marble1;
                player3.marble2 = 208;
                player3.startingLocations[1] = player3.marble2;
                player3.marble3 = 192;
                player3.startingLocations[2] = player3.marble3;
                player3.marble4 = 176;
                player3.startingLocations[3] = player3.marble4;

                player3.homeLocations[0] = 115;
                player3.homeLocations[1] = 116;
                player3.homeLocations[2] = 117;
                player3.homeLocations[3] = 118;

                player3.holes = new int[]{149,148,147,146,145,144,159,174,189,204,219,218,217,216,215,200,185,170,155,
                                                140,139,138,137,136,135,120,105,90,75,76,77,78,79,80,
                                                65,50,35,20,5,6,7,8,9,24,39,54,69,84,85,86,87,88,89,
                                                104,119,
                                                118,117,116,115};
            }
            else if(player4 == null)
            {
                //Set new player object
                player4 = new Player(c);

                for(int i = 12; i <= 15; i++) {
                    //Define starting/home positions
                    holes[i].setFull();
                    holes[i].setColor(player4.getColor());
                }

                //Set player's start/home positions
                player4.marble1 = 210;
                player4.startingLocations[0] = player4.marble1;
                player4.marble2 = 196;
                player4.startingLocations[1] = player4.marble2;
                player4.marble3 = 182;
                player4.startingLocations[2] = player4.marble3;
                player4.marble4 = 168;
                player4.startingLocations[3] = player4.marble4;

                player4.marble1 = 210;
                player4.startingLocations[0] = player4.marble1;
                player4.marble2 = 196;
                player4.startingLocations[1] = player4.marble2;
                player4.marble3 = 182;
                player4.startingLocations[2] = player4.marble3;
                player4.marble4 = 168;
                player4.startingLocations[3] = player4.marble4;

                player4.homeLocations[0] = 157;
                player4.homeLocations[1] = 172;
                player4.homeLocations[2] = 187;
                player4.homeLocations[3] = 202;

                player4.holes = new int[]{215,200,185,170,155,
                        140,139,138,137,136,135,120,105,90,75,76,77,78,79,80,
                        65,50,35,20,5,6,7,8,9,24,39,54,69,84,85,86,87,88,89,
                        104,119,134,149,148,147,146,145,144,159,174,189,204,219,218,217,
                        202,187,172,157};
            }
        }catch(Exception e)
        {
            //Display error message that no more players are allowed to join!!
                //Game is full
        }
    }

    public boolean GameOver()
    {
        //Keep track of home positions filled for each player
        int player1count = 0;
        int player2count = 0;
        int player3count = 0;
        int player4count = 0;

            for(int j = 0; j < 4; j++) {
                if (holes[Hole.FindHole(player1.homeLocations[j])].isEmpty() == false)
                    player1count++;
                if (holes[Hole.FindHole(player2.homeLocations[j])].isEmpty() == false)
                    player2count++;
                if (holes[Hole.FindHole(player3.homeLocations[j])].isEmpty() == false)
                    player3count++;
                if (holes[Hole.FindHole(player4.homeLocations[j])].isEmpty() == false)
                    player4count++;
            }

        // Clear game board if player has won and display winner in pop up box
        if(player1count == 4 || player2count == 4 || player3count == 4 || player4count == 4)
            return true;
        else
            return false;
    }

    //Returns possible move
    public int requestMove(int marbleLocation) {
        //Finds index location in allholesarray
        int tempOldIndex = current.FindHole(marbleLocation);
        int tempNewIndex = 666;
        int tempNewHole = 666;

        boolean inStartingLocation = false;
        boolean inHomeLocation = false;
        boolean completedTurn = false;

        //Check if marble being moved is in start
        for (int x : Hole.startingLocations) {
            if (x == marbleLocation) {
                inStartingLocation = true;
                break;
            }
        }

        //Check if marble being moved is in home
        for (int x : Hole.homeLocations) {
            if (x == marbleLocation) {
                inHomeLocation = true;
                break;
            }
        }

        //If selected marble is in home location or on main track
        if (inStartingLocation == false) {
            if(tempOldIndex + getCurrentRoll() >= current.holes.length){

                //test debug
                return 666;
            }
            else{
                tempNewIndex = tempOldIndex + getCurrentRoll();
                tempNewHole = current.holes[tempNewIndex];
            }

            //Does not allow player to jump/land on its own marbles.
            for(int i = tempOldIndex+1; i <= tempNewIndex; i++){
                if(holes[Hole.FindHole(current.holes[i])].getColor() == current.getColor())
                    tempNewHole = 666;
            }

            completedTurn = true;
        }

        //If selected marble is in starting location
        else if (inStartingLocation) {
            if (getCurrentRoll() == 6 || getCurrentRoll() == 1) {
                tempNewIndex = 0;
                tempNewHole = current.holes[0];

                completedTurn = true;
            }

            else
            {
                tempNewHole = 666;
                completedTurn = true;
            }

        }

            if(requestedMove && holes[Hole.FindHole(current.holes[tempNewIndex])].isEmpty())
            {
                holes[Hole.FindHole(marbleLocation)].setEmpty();
                holes[Hole.FindHole(tempNewHole)].setFull();
                holes[Hole.FindHole(tempNewHole)].setColor(current.getColor());
                requestedMove = false;

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
            }
            else if(requestedMove && holes[Hole.FindHole(current.holes[tempNewIndex])].isEmpty() == false)
            {
                //Take appropriate steps to replace marble and send back to starting location
                knockOff(holes[Hole.FindHole(current.holes[tempNewIndex])], current.holes[tempNewIndex]);
                KNOCKEDOFFTRUE = true;
                holes[Hole.FindHole(marbleLocation)].setEmpty(); //TEST TOMORROW
                holes[Hole.FindHole(tempNewHole)].setFull();
                holes[Hole.FindHole(tempNewHole)].setColor(current.getColor());
                requestedMove = false;

                holes[Hole.FindHole(current.holes[tempNewIndex])].setFull();
                holes[Hole.FindHole(current.holes[tempNewIndex])].setColor(current.getColor());
              requestedMove = false;

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

            }

            if (completedTurn) {

                //return Grid location for Game Activity to draw
                return tempNewHole;

            } else {
                return 666; //WILL CHANGE THIS RETURN
            }
    }

    public void knockOff(Hole tempHole, int loc)
    {
        if(tempHole.getColor() != current.getColor())
        {
            if(player1.marble1 == loc || player1.marble2 == loc || player1.marble3 == loc || player1.marble4 == loc)
            {
                KNOCKEDOFF = player1;
                for(int i = 0; i < 4; i++) {
                    if(holes[Hole.FindHole(player1.startingLocations[i])].isEmpty()) {
                        if (player1.marble1 == loc) {
                            player1.marble1 = player1.startingLocations[i];
                        }
                        else if (player1.marble2 == loc)
                        {
                            player1.marble2 = player1.startingLocations[i];
                        }
                        else if(player1.marble3 == loc)
                        {
                            player1.marble3 = player1.startingLocations[i];
                        }
                        else if(player1.marble4 == loc)
                        {
                            player1.marble4 = player1.startingLocations[i];
                        }
                        KNOCKEDOFFNEWLOCATION = player1.startingLocations[i];
                        holes[Hole.FindHole(player1.startingLocations[i])].setFull();
                        holes[Hole.FindHole(player1.startingLocations[i])].setColor(player1.getColor());
                        break;
                    }
                }
            }
            else if(player2.marble1 == loc || player2.marble2 == loc || player2.marble3 == loc || player2.marble4 == loc)
            {
                KNOCKEDOFF = player2;
                for(int i = 0; i < 4; i++) {
                    if(holes[Hole.FindHole(player2.startingLocations[i])].isEmpty()) {
                        if (player2.marble1 == loc) {
                            player2.marble1 = player2.startingLocations[i];
                        }
                        else if (player2.marble2 == loc)
                        {
                            player2.marble2 = player2.startingLocations[i];
                        }
                        else if(player2.marble3 == loc)
                        {
                            player2.marble3 = player2.startingLocations[i];
                        }
                        else if(player2.marble4 == loc)
                        {
                            player2.marble4 = player2.startingLocations[i];
                        }
                        KNOCKEDOFFNEWLOCATION = player2.startingLocations[i];
                        holes[Hole.FindHole(player2.startingLocations[i])].setFull();
                        holes[Hole.FindHole(player2.startingLocations[i])].setColor(player2.getColor());
                        break;
                    }
                }
            }
            else if(player3.marble1 == loc || player3.marble2 == loc || player3.marble3 == loc || player3.marble4 == loc)
            {
                KNOCKEDOFF = player3;
                for(int i = 0; i < 4; i++) {
                    if(holes[Hole.FindHole(player3.startingLocations[i])].isEmpty()) {
                        if (player3.marble1 == loc) {
                            player3.marble1 = player3.startingLocations[i];
                        }
                        else if (player3.marble2 == loc)
                        {
                            player3.marble2 = player3.startingLocations[i];
                        }
                        else if(player3.marble3 == loc)
                        {
                            player3.marble3 = player3.startingLocations[i];
                        }
                        else if(player3.marble4 == loc)
                        {
                            player3.marble4 = player3.startingLocations[i];
                        }
                        KNOCKEDOFFNEWLOCATION = player3.startingLocations[i];
                        holes[Hole.FindHole(player3.startingLocations[i])].setFull();
                        holes[Hole.FindHole(player3.startingLocations[i])].setColor(player3.getColor());
                        break;
                    }
                }
            }
            else if(player4.marble1 == loc || player4.marble2 == loc || player4.marble3 == loc || player4.marble4 == loc)
            {
                KNOCKEDOFF = player4;
                for(int i = 0; i < 4; i++) {
                    if(holes[Hole.FindHole(player4.startingLocations[i])].isEmpty()) {
                        if (player4.marble1 == loc) {
                            player4.marble1 = player4.startingLocations[i];
                        }
                        else if (player4.marble2 == loc)
                        {
                            player4.marble2 = player4.startingLocations[i];
                        }
                        else if(player4.marble3 == loc)
                        {
                            player4.marble3 = player4.startingLocations[i];
                        }
                        else if(player4.marble4 == loc)
                        {
                            player4.marble4 = player4.startingLocations[i];
                        }
                        KNOCKEDOFFNEWLOCATION = player4.startingLocations[i];
                        holes[Hole.FindHole(player4.startingLocations[i])].setFull();
                        holes[Hole.FindHole(player4.startingLocations[i])].setColor(player4.getColor());
                        break;
                    }
                }
            }
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

    public String findWinner() {
        String playerTurn = "";
        if (current.getColor() == "red") {
            playerTurn = "Red Wins!";
        }
        if (current.getColor() == "blue") {
            playerTurn = "Blue Wins!";
        }
        if (current.getColor() == "yellow") {
            playerTurn = "Yellow Wins!";
        }
        if (current.getColor() == "green") {
            playerTurn = "Green Wins!";
        }

        return playerTurn;
    }
}
