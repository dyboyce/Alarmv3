package app.com.example.dylan.alarmv3;

/**
 * Created by Rich on 11/21/2015.
 */
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
/**
 * Class blood extends dimension, creates blood animation
 */
public class Blood extends Dimension {
    public int r;

    /**
     * constructor for blood, takes in x,y coordinates for location
     * @param  x    defines the x coordiante for the blood to be drawn at
     * @param  y    defines the x coordiante for the blood to be drawn at
     */
    public Blood(int x, int y)
    {
        r = 5;
        super.x = x;
        super.y = y;
    }

    /**
     * method to update the animation of the blood
     */
    public void update()
    {
        x-=10;
    }

    /**
     * Method to draw the image of the blood
     * @param  canvas    the canvas to be drawn on
     */
    public void draw(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(x-r, y-r, r, paint);
        canvas.drawCircle(x-r+2, y-r-2,r,paint);
        canvas.drawCircle(x-r+4, y-r+1, r, paint);
    }

}
