package app.com.example.dylan.alarmv3;


/**
 * Created by Rich on 11/21/2015.
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;


/**
 * class Ocean creates the image and animation for the background
 */
public class Ocean {

    private Bitmap image;
    private int x, y, dx;

    /**
     * constructor Ocean takes and image of a bitmap and stores it in oceans bitmap variable
     * @param  res    bitmap image of ocean
     * @see    res    image of ocean
     */
    public Ocean(Bitmap res)
    {
        image = res;
    }

    /**
     * method update, updates the values of background to give it apperance it is moving
     */
    public void update()
    {
        x+=dx;
        if(x<-GamePanel.WIDTH){
            x=0;
        }
    }

    /**
     * method draw, draws the image of the ocean on the canvas
     * @param  canvas    Canvas variable
     */
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(image, x, y,null);
        if(x<0)
        {
            canvas.drawBitmap(image, x+GamePanel.WIDTH, y, null);
        }
    }
}

