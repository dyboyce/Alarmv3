package app.com.example.dylan.alarmv3;

/**
 * Created by Rich on 11/21/2015.
 */
import android.graphics.Rect;

/**
 * abstract class Dimension defines to coordinates and width and height of fish, blood, ans shark
 */
public abstract class Dimension {
    protected int x;
    protected int y;
    protected int moveY;
    protected int moveX;
    protected int width;
    protected int height;
    /**
     * method setX sets the x coor
     * @param   x    int x defines the x coor
     */
    public void setX(int x)
    {
        this.x = x;
    }
    /**
     * method setY sets the x coor
     * @param   y    int y defines the y coor
     */
    public void setY(int y)
    {
        this.y = y;
    }
    /**
     * method getX gets the x coor
     * @return   x    int x defines the x coor
     */
    public int getX()
    {
        return x;
    }

    /**
     * method getY gets the x coor
     * @return   y    int y defines the x coor
     */
    public int getY()
    {
        return y;
    }

    /**
     * method getHeight gets the x coor
     * @return   height    int height defines the height
     */
    public int getHeight()
    {
        return height;
    }
    /**
     * method getWidth gets the x coor
     * @return   width    int width defines the width
     */
    public int getWidth()
    {
        return width;
    }
    /**
     * method getRectangle gets the x coor
     * @return   rect    returns a rectangle the completly encases the object
     */
    public Rect getRectangle()
    {
        return new Rect(x, y, x+width, y+height);
    }

}