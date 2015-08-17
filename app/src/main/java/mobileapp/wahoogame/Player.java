package mobileapp.wahoogame;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Player {
    private String color;
    private int marbleLoc1;
    private int marbleLoc2;
    private int marbleLoc3;
    private int marbleLoc4;

    int[] holes;
    int[] startingLocations = new int[4];
    int[] homeLocations = new int[4];

    //Where player moves when out of starting location
    private int firstHole;

    public Player(String c)
    {
        color = c;
    }

    public String getColor()
    {
        return color;
    }

    //Sets location of marble
    public void setMarbleLoc1(int m1)
    {
        marbleLoc1 = m1;
    }

    public int getMarbleLoc1()
    {
      return marbleLoc1;
    }

    public void setMarbleLoc2(int m2)
    {
        marbleLoc2 = m2;
    }

    public int getMarbleLoc2(){
        return marbleLoc2;
    }

    public void setMarbleLoc3(int m3)
    {
        marbleLoc3 = m3;
    }

    public int getMarbleLoc3()
    {
        return marbleLoc3;
    }

    public void setMarbleLoc4(int m4)
    {
        marbleLoc4 = m4;
    }

    public int getMarbleLoc4()
    {
        return marbleLoc4;
    }

    public int FindHole(int n) //pass gridlocation
    {
        for(int i = 0; i < holes.length; i++)
        {
            if(holes[i] == n)
                return i;
        }
        //print some error message
        return 666;
    }
}
