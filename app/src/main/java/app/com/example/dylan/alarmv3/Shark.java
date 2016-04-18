package app.com.example.dylan.alarmv3;

/**
 * Created by Rich on 11/21/2015.
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * This clas creates a shark. The dimensions for the shark are stored
 * in the dimension class it extends
 */
public class Shark extends Dimension {
    private Bitmap spritesheet;

    private int score;

    private boolean up;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;

    /**
     * constructor to draw the shark
     * @param  w         int param of the width
     * @param  y         int param of the height
     * @param  numFrames int param of the height
     * @see    pic       Image of the shark
     */
    public Shark(Bitmap pic, int w, int h, int numFrames) {

        x = 50;
        y = GamePanel.HEIGHT / 2;
        moveY = 0;
        height = h;
        width = w;

        Bitmap[] image = new Bitmap[numFrames];
        spritesheet = pic ;

        for (int i = 0; i < image.length; i++)
        {
            image[i] = Bitmap.createBitmap(spritesheet, i*width, 0, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(10);
        startTime = System.nanoTime();
    }

    /**
     * method to set valaue of boolean setUp
     * @param  b    boolean to change value of up
     */
    public void setUp(boolean b){up = b;}

    /**
     * method to update the animation of the shark
     */
    public void update()
    {
        animation.update();

        if(up){
            moveY -=1;

        }
        else{
            moveY +=1;
        }

        if(moveY >5) moveY = 5;
        if(moveY <-5) moveY = -5;

        if(this.getY()> GamePanel.HEIGHT-40)
        {
            this.setY(GamePanel.HEIGHT-40);
        }

        if(this.getY() <= 0 )
        {
            this.setY(10);
        }
        y += moveY *2;

    }

    /**
     * Method to draw the image of the shark
     * @param  canvas    the canvas to be drawn
     */
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(animation.getImage(),x,y,null);
    }

    /**
     * method to return the state of the playing boolean
     * @return  playing    boolean that tells the game loop if it should be activly playing
     */
    public boolean getPlaying(){return playing;}

    /**
     * method setPlaying to set valaue of boolean setPlaying
     * @param  b    boolean to change value of setPlaying
     */
    public void setPlaying(boolean b){playing = b;}

    /**
     * method resetDY set valaue of acceleration back to 0
     */
    public void resetDY()
    {
        moveY = 0;
    }
}