package mobileapp.wahoogame;

public class Hole {

    private boolean empty;    //Checks to see if the hole is occupied or empty
    private String color;     //Keeps track of the marble's color in which currently occupies this hole
    private int gridLocation; //Keeps track of each hole's grid location on the game activity board

    //Location in relation to the main board
    private boolean onMainTrack;
    private boolean home;
    private boolean startingPosition;

    private Player player; //Keeps track of player in the hole

    //Static methods used to save memory (only creates once)
    //Cannot be manipulated
    static int[] startingLocations = {0,16,32,48,
                                      14,28,42,56,
                                      224,208,192,176,
                                      210,196,182,168}; //When new player created check 4 holes at a time to determine which ones are not
                                                        // occupied by a player (synchronization may come in here)

    static int[] homeLocations = {22,37,52,67,
                                  118,117,116,115,
                                  202,187,172,157,
                                  106,107,108,109}; //When new player created check 4 holes at a time to determine which ones are not
                                                    // occupied by a player (synchronization may come in here)

    static int[] mainTrackLocations = {75, 76, 77, 78, 79, 80, 65, 50, 35, 20, 5, 6, 7, 8, 9, 24,
            39, 54, 69, 84, 85, 86, 87, 88, 89, 104, 119, 134, 149,
            148, 147, 146, 145, 144, 159, 174, 189, 204, 219, 218,
            217, 216, 215, 200, 185, 170, 155, 140, 139, 138, 137,
            136, 135, 120, 105, 90}; //counter clockwise starting
                                     // with player1 (top left) maintrack

    //Initialize the constant locations of all holes
    static int[] allHoleLocations =
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

    public Hole()
    {
        color = "none";
        empty = true;
        startingPosition = false;
        home = false;
        onMainTrack = false;
    }

    public String getColor()
    {
        //Returns the marble color in the hole
        return color;
    }

    public void setColor(String c)
    {
        //Sets the marble color in the hole
        color = c;
    }

    public boolean isEmpty()
    {
        //Checks if hole is occupied
        return(empty);
    }

    public void setFull()
    {
        empty = false;
    }

    public void setEmpty()
    {
        //Sets the hole back to default values (empty)
        empty = true;
        color = "none";
    }

    public void setGridLocation(int loc)
    {
        gridLocation = loc;

        for(int x: startingLocations) {
            if (gridLocation == x)
                startingPosition = true;
        }

        for(int x: homeLocations){
            if (gridLocation == x)
                home = true;
        }

        for(int x: mainTrackLocations){
            if(gridLocation == x)
                onMainTrack = true;
        }
    }

    public boolean isHome()
    {
        return home;
    }

    public boolean isStartingPosition() {
        return isStartingPosition();
    }

    public boolean isOnMainTrack()
    {
        return onMainTrack;
    }

    //Finds a hole by gridvalue and returns the index in allHoleLocations
    public static int FindHole(int n) //pass gridlocation
    {
        for(int i = 0; i < allHoleLocations.length; i++)
        {
            if(allHoleLocations[i] == n)
                return i;
        }
        //print some error message
        return 0;
    }
}
