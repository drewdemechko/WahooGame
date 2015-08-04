package mobileapp.wahoogame;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by Drew Demechko on 8/1/2015.
 */
public class Player {
    String color;
    String name;
    int marble1;
    int marble2;
    int marble3;
    int marble4;

    private Drawable selectedMarble;

    int[] homeLocations = new int[4];
    int[] startingLocations = new int[4];

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

    public void setMarbleLocation(int startLocation, int endLocation)
    {
        //Sets up the default marble locations by checking if marbles are at
        // the other 4 locations starting from the top left
    }



    //Not sure if we will need this one because the players shouldn't be
    // able to change colors mid game
    /*public void setColor(String c)*/
}
