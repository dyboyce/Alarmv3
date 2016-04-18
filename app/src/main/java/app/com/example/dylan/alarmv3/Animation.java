package app.com.example.dylan.alarmv3;

/**
 * Created by Rich on 11/21/2015.
 */
import android.graphics.Bitmap;
/**
 * class Animation handles the details of drawing the shark, fish, and blood
 */
public class Animation {
    private Bitmap[] frames;
    private int currentFrame;
    private long startTime;
    private long delay;

    /**
     * method setFrames selects the current frame for display
     * @param  pics    Bitmap[] pics is the image of the sprite sheet to be displayed
     */
    public void setFrames(Bitmap[] pics)
    {
        this.frames = pics;
        currentFrame = 0;
        startTime = System.nanoTime();
    }


    /**
     * Method setDelay sets the amount of time to wait between displaying individual sprites
     * @param  d    long d defines the amount of time to wait
     */
    public void setDelay(long d){delay = d;}

    /**
     * method to update the animation
     */

    public void update()
    {
        long elapsed = (System.nanoTime()-startTime)/1000000;

        if(elapsed > delay)
        {
            currentFrame++;
            startTime = System.nanoTime();
        }
        if(currentFrame == frames.length){
            currentFrame = 0;
        }
    }
    public Bitmap getImage(){
        return frames[currentFrame];
    }
}