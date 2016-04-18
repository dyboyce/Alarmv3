package app.com.example.dylan.alarmv3;

/**
 * Created by Rich on 11/21/2015.
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

/**
 * class Fish extends dimension for storage of cooridantes and creates image of fish
 */
public class Fish extends Dimension {
    private int speed;
    private Random rand = new Random();
    private Animation animation = new Animation();
    private Bitmap spritesheet;


    /**
     * Constructor for fish, takes in ints for height, width, x, and y
     * also takes int for number of frames in the spriteSheet and an image of the sprite sheet
     * @param  x          int defining the x coordinate
     * @param  y          int defining the y coordinate
     * @param  w          int defining the width
     * @param  h          int defining the height
     * @param  numFrames  int defing number of frames in the sprite sheet
     * @param  pic        bitmap image of the spritesheet of fish
     */
    public Fish(Bitmap pic, int x, int y, int w, int h, int numFrames)
    {
        super.x = x;
        super.y = y;
        width = w;
        height = h;

        speed = 7 + (int) (rand.nextDouble()/40);

        //cap missile speed
        if(speed>40)speed = 40;

        Bitmap[] image = new Bitmap[numFrames];

        spritesheet = pic;

        for(int i = 0; i<image.length;i++)
        {
            image[i] = Bitmap.createBitmap(spritesheet, 0, i*height, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(100-speed);

    }

    /**
     * method to update the animation of the fish
     */
    public void update()
    {
        x-=speed;
        animation.update();
    }

    /**
     * Method to draw the image of the fish
     * @param  canvas    the canvas to be drawn
     */
    public void draw(Canvas canvas)
    {
        try{
            canvas.drawBitmap(animation.getImage(),x,y,null);
        }catch(Exception e){}
    }


    /**
     * Method getWidth overides getWidth method of the dimension class
     * @Override
     */
    @Override
    public int getWidth()
    {
        //offset slightly for more realistic collision detection
        return width-15;
    }

}