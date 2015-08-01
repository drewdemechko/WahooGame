package mobileapp.wahoogame;

/**
 * Created by Drew Demechko on 8/1/2015.
 */
public class Hole {

    private boolean empty;
    private String color;
    static private int holeId; //Way to keep track of holes
    private int gridLocation;

    private boolean onMainTrack;
    private boolean home;
    private boolean startingPosition;

    private Player player;

    //Static methods used to save memory (only creates once)

    static int[] startingLocations = {0,16,32,48,
                                      14,28,42,56,
                                      224,208,192,176,
                                      210,196,182,168};

    static int[] homeLocations = {22,37,52,67,
                                  118,117,116,115,
                                  202,187,172,157,
                                  106,107,108,109};

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
    }

    public void setAsStartingPosition()
    {
        startingPosition = true;
    }

    public void setAsHome()
    {
        home = true;
    }

    public void setAsMainTrack()
    {
        onMainTrack = true;
    }

    public boolean isHome()
    {
        return home;
    }

    public boolean isStartingPosition()
    {
        return isStartingPosition();
    }

    public boolean isOnMainTrack()
    {
        return onMainTrack;
    }
}
