package app.com.example.dylan.alarmv3;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by Aaron on 11/21/2015.
 */

/*
 * class Droid creates a new instance of a droid image
 */
public class Droid {
    private Bitmap bitmap;
    private int x;
    private int y;
    private boolean touched;
    private static final String TAG = Droid.class.getSimpleName();

    /*
     * constructor droid creates a new instance of a droid
     * @param    bitmap      Bitmap bitmap, is the image of the droid to display
     * @param    x           int x, defines the x coordiante to draw the droid at
     * @param    y           int y, defines the y coordiante to draw the droid at
     */
    public Droid(Bitmap bitmap, int x, int y)
    {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
    }

    /*
    * getBitmap returns the current bitmap image
    * @return bitmap     the current image of the droid
    */
    public Bitmap getBitmap()
    {
        return bitmap;
    }

    /*
     * setBitmap sets the current bitmap image
     * @param bitmap     the current image of the droid
     */
    public void setBitmap(Bitmap bitmap)
    {
        this.bitmap = bitmap;
    }

    /*
     * getX returns the x value of the driod
     * @return   x     the coordinate the droid is located at
     */
    public int getX()
    {
        return x;
    }

    /*
     * setX sets the x value of the driod
     * @param   x     the coordinate the droid is located at
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /*
     * getY returns the y value of the driod
     * @return   y     the y coordinate the droid is located at
     */
    public int getY()
    {
        return y;
    }

    /*
    * setY returns the y value of the driod
    * @param   y     the y coordinate the droid is located at
    */
    public void setY(int y)
    {
        this.y = y;
    }

    /*
    * setTouched changes the state of the touched boolean
    * @param   touched      boolean touched
    */
    public boolean isTouched(float ex, float ey)
    {
        Log.d(TAG, "isTouched reached!");
        if (ex < x + 50 && ex > x - 50 && ey < y + 50 && ey > y - 50) {
            Log.d(TAG, "isTouched if statement reached!");
            return true;
        }
        return false;
    }

    /*
 * method checks if two droids are equal
 * @param   obj -  Object being compared to.
 * @return whether the two droids are equal or not.
 */
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Droid other = (Droid) obj;
        if ((this.bitmap == null) ? (other.bitmap != null) : !this.bitmap.equals(other.bitmap))
        {
            return false;
        }
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }

    public void setTouched(boolean touched)
    {
        this.touched = touched;
    }


    /*
    * method draw draws the image of the droid
    * @param   canvas      Canvas canvas, the canvas to draw on
    */
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y - (bitmap.getHeight() / 2), null);
    }


    /*
    * method handleActionDown checks if droid was touched or not.
    * @param   eventX - x coordinate
    * @param   eventY - y coordinate
    */
    public void handleActionDown(int eventX, int eventY)
    {
        if (eventX >= (x - bitmap.getWidth() / 2) && (eventX <= (x + bitmap.getWidth()/2)))
        {
            if (eventY >= (y - bitmap.getHeight() / 2) && (y <= (y + bitmap.getWidth()/2)))
            {
                setTouched(true);
            }
            else
            {
                setTouched(false);
            }
        }
        else
        {
            setTouched(false);
        }
    }

}