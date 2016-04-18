package app.com.example.dylan.alarmv3;

/**
 * Created by Aaron on 10/4/2015.
 */
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;


/*
 * class puzzMainThread extends Thread, creates the man game loop
 */
public class puzzMainThread extends Thread {

    private static final String TAG = puzzMainThread.class.getSimpleName();

    private SurfaceHolder surfaceHolder;
    static public BlockPuzzleMain gamePanel;
    private static boolean running;
    public Canvas canvas;

 /*
     * setRunning, sets the running boolean if the game is being played
     * @param     running      boolean running is the state you want the thread to be in
    */
    public void setRunning(boolean running)
    {
        this.running = running;
    }


     /*
     * method puzzMainThread  calls the gamePanel and sets up to run
     * @param     surfaceHolder
     * @param     gamePanel
    */
    public puzzMainThread(SurfaceHolder surfaceHolder, BlockPuzzleMain gamePanel)
    {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
        Log.d(TAG, "Puzzle Main Thread initialized");
    }

    /*
 * setOff, sets the running variable to off
 */
    public static void setoff(){
        running=false;
    }

    /**
     * method run is called automatically when the program starts. And calls the recurring
     * draw on
     */
    @Override
    public void run()
    {
        canvas = null;
        //loop starts as long as running is true.
        while (running) {
            //this makes the screen show the image desired.
            //lockCanvas() sets the canvas even when the try catch block is exited.
            try {
                canvas = this.surfaceHolder.lockCanvas();
                if(canvas!=null){
                    //As long as the canvas exists then the onDraw() method will be called which draws the game.
                synchronized (surfaceHolder) {
                    // synchronized allows one thread to be running if another one is turned off.
                    gamePanel.onDraw(canvas);

                }
                }
                //finally block always ensures that the finally statement is called.
            } finally {
                if (canvas != null) {
                    //ensures that pixels on screen stay the same until lockCanvas() is called again
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

}

