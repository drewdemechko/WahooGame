package mobileapp.wahoogame;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Player {
    private String color;
    String name;
    int marble1;
    int marble2;
    int marble3;
    int marble4;

    private Drawable selectedMarble;
    int[] holes;
    int[] startingLocations = new int[4];
    int[] currentLocations = new int[4];

    //Where player moves when out of starting location
    private int firstHole;

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

    public int FindHole(int n) //pass gridlocation
    {
        for(int i = 0; i < holes.length; i++)
        {
            if(holes[i] == n)
                return i;
        }
        //print some error message
        return 0;
    }
}
