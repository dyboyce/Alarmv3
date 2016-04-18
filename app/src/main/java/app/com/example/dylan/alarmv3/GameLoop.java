package app.com.example.dylan.alarmv3;

/**
 * Created by Rich on 11/21/2015.
 */
import android.graphics.Canvas;
import android.view.SurfaceHolder;


/**
 * class GameLoop extends thread, handles the cycling of game
 */
public class GameLoop extends Thread
{
    private int FPS = 30;
    public static Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;

    /**
     * Constructor GameLoop creates a new Game loop
     * @param   surfaceHolder    SurfaceHolder surfaceHolder
     * @param   gamePanel        GamePanel gamePanel, the instance of gamePanel to create
     */
    public GameLoop(SurfaceHolder surfaceHolder, GamePanel gamePanel)
    {
        super();
        this.gamePanel = gamePanel;
        this.surfaceHolder = surfaceHolder;
    }

    /**
     * method run defines the loop for the game to execute on
     * @Override
     */
    @Override
    public void run()
    {
        long startTime;
        long mils;
        long waitTime;
        long target = 1000/FPS;

        while(running) {
            startTime = System.nanoTime();
            canvas = null;

            //try locking the canvas
            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            } catch (Exception e) {
            }
            finally{
                if(canvas!=null)
                {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch(Exception e){e.printStackTrace();}
                }
            }

            mils = (System.nanoTime() - startTime) / 1000000;
            waitTime = target-mils;

            try{
                this.sleep(waitTime);
            }catch(Exception e){}

        }
    }

    /**
     * method setRunning takes a boolean and changes the state of running
     * @param   b    boolean b defines the state to set running to
     */
    public void setRunning(boolean b)
    {
        running=b;
    }
}
