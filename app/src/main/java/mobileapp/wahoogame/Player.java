package mobileapp.wahoogame;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by Drew Demechko on 8/1/2015.
 */
public class Player {
    private String color;
    String name;
    int marble1;
    int marble2;
    int marble3;
    int marble4;

    private Drawable selectedMarble;

    int[] homeLocations = new int[4];
    int[] startingLocations = new int[4];
    int[] currentLocations = new int[4];

    //Where player moves when out of starting location
    private int firstHole;
    private int lastHole;

    //May need this later, not sure yet
    private boolean isAlive = false;
    //I think we may need a Starting location of int[] to keep track for knock offs/set backs

    public Player(String c)
    {
        color = c;
        isAlive = true;
        //Find starting and home locations for new player
    }

    public String getColor()
    {
        return color;
    }

    public int getfirstHole()
    {
        return firstHole;
    }

    public void setFirstHole(int loc)
    {
       firstHole = loc;
    }

    public int getLastHole()
    {
        return lastHole;
    }

    public void setLastHole(int loc)
    {
        lastHole = loc;
    }

    public int[] getCurrentLocations()
    {
        return currentLocations;
    }

    public void setCurrentLocations(int m1, int m2, int m3, int m4)
    {
        currentLocations[0] = m1;
        currentLocations[1] = m2;
        currentLocations[2] = m3;
        currentLocations[3] = m4;

        setMarble1(m1);
        setMarble2(m2);
        setMarble3(m3);
        setMarble4(m4);
    }
    //Sets location of marble
    public void setMarble1(int m1)
    {
        marble1 = m1;
    }

    public void setMarble2(int m2)
    {
        marble2 = m2;
    }

    public void setMarble3(int m3)
    {
        marble3 = m3;
    }

    public void setMarble4(int m4)
    {
        marble4 = m4;
    }
    //Not sure if we will need this one because the players shouldn't be
    // able to change colors mid game
    /*public void setColor(String c)*/
}
