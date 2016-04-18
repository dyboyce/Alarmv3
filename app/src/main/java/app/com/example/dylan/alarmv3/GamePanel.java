package app.com.example.dylan.alarmv3;

/**
 * Created by Rich on 11/21/2015.
 */
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

/*
* class Gamepenel extends SurfaceView and implements SurfaceHolder.Callback
* Creates the view for the game to be played on
*/

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{

    private int block=0;
    private int math=0;
    private int sharkfl=0;
    private int flaghigh=0;
    private int win=0;
    private int buttonum=0;

    public static final int WIDTH = 300;
    public static final int HEIGHT = 147;
    private long fishStartTime;
    private GameLoop thread;
    private Ocean backGround;
    private Shark shark;
    private ArrayList<Blood> blood;
    private ArrayList<Fish> fish;
    private Random rand = new Random();
    private boolean gameWon;
    private boolean newGameCreated;
    private int numberEaten = 0;
    private long startReset;
    private boolean reset;
    private boolean playing;
    private boolean started;
    MediaPlayer mp;


    /*
 * constructor GamePanel takes a Context as param
 * @param    context     Context context
 */
    public GamePanel(Context context)
    {
        super(context);

        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        //make gamePanel focusable so it can handle events
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){}
    //writes the int flags in from the activity
    public void setints(int b, int m, int s, int fh, int w, int btn){

        block=b;
        math=m;
        sharkfl=s;
        flaghigh=fh;
        win=w;
        buttonum=btn;

    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        int counter = 0;
        while(retry && counter<1000)
        {
            counter++;
            try{thread.setRunning(false);
                thread.join();
                retry = false;
                thread = null;

            }catch(InterruptedException e){e.printStackTrace();}
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){

        backGround = new Ocean(BitmapFactory.decodeResource(getResources(), R.drawable.ocean));
        shark = new Shark(BitmapFactory.decodeResource(getResources(), R.drawable.shark), 65, 25, 3);
        blood = new ArrayList<Blood>();
        fish = new ArrayList<Fish>();
        fishStartTime = System.nanoTime();
        gameWon = false;
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        final AudioManager mAudioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        int origionalVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_ALARM);
        mp = MediaPlayer.create(getContext(), notification);
        mp.start();


        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            int Replay=0;int
                    numReplay=15;
            public void onCompletion(MediaPlayer mp) {
                if( Replay < numReplay){
                    mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mAudioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM), 0);
                    mp.setVolume(1.0f,1.0f);
                    mp.start();
                    Replay++;
                }
            }
        });

        thread = new GameLoop(getHolder(), this);

        //start the game loop
        thread.setRunning(true);
        thread.start();
    }

    /*
  * update all values from the gameloop
  */
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            if(!shark.getPlaying() && newGameCreated && reset)
            {
                shark.setPlaying(true);
                shark.setUp(true);
            }
            if(shark.getPlaying())
            {
                if(!started)started = true;
                reset = false;
                shark.setUp(true);
            }
            return true;
        }
        if(event.getAction()==MotionEvent.ACTION_UP)
        {
            shark.setUp(false);
            return true;
        }

        return super.onTouchEvent(event);
    }

    public void update()
    {
        if(numberEaten == 3)
        {

            try{
                Method method;
                Class[] methodParameters = new Class[]{Boolean.TYPE};
                Object[] params = new Object[]{new Boolean(false)};

                // Get the method by providing its name and a Class array with parameters
                method = Shark.class.getDeclaredMethod("setPlaying", boolean.class);

                // Execute the method and pass parameter values. The return value is stored
                method.invoke(shark, params);

            } catch (NoSuchMethodException e){
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {}

            Context con = getContext();
            Intent winshark = new Intent(con, menuActivity.class);
            winshark.putExtra("flaghigh",1);
            winshark.putExtra("block",block);
            winshark.putExtra("math",math);
            winshark.putExtra("shark",0);
            winshark.putExtra("win",1);
            winshark.putExtra("buttonum",buttonum);

            con.startActivity(winshark);
            //endit();
        }
        if(shark.getPlaying()) {

            backGround.update();
            shark.update();

            //add fish
            long missileElapsed = (System.nanoTime()-fishStartTime)/1000000;
            if(missileElapsed > 3000){

                if(fish.size()==0)
                {
                    fish.add(new Fish(BitmapFactory.decodeResource(getResources(),R.drawable.
                            fish2),WIDTH + 10, HEIGHT/2, 38, 32, 10));
                }
                else
                {
                    fish.add(new Fish(BitmapFactory.decodeResource(getResources(),R.drawable.fish2),
                            WIDTH+10, (int)(rand.nextDouble()*HEIGHT+15),38,32,10));
                }

                //reset timer
                fishStartTime = System.nanoTime();
            }

            //loop through every fish and check collision and remove
            for(int i = 0; i < fish.size();i++)
            {
                //update fish
                fish.get(i).update();

                // check for collisions
                if(collision(fish.get(i),shark))
                {
                    fish.remove(i);
                    numberEaten++;
                    blood.add( new Blood(shark.getX()+70, shark.getY()+15));
                    break;
                }
                //remove fish if off the screen
                if(fish.get(i).getX()<-100)
                {
                    fish.remove(i);
                    break;
                }
            }
        }
        else{
            shark.resetDY();
            if(!reset)
            {
                newGameCreated = false;
                startReset = System.nanoTime();
                reset = true;
                playing = true;
            }

            long resetElapsed = (System.nanoTime()-startReset)/1000000;
            if(resetElapsed > 2500 && !newGameCreated) {
                newGame();
            }
        }
    }

    /*
   * collision checks to see if the shark has overlapped rectangels with any other object
   * @param     a      Dimension a first objects rec to check
   * @param     b      Dimension b second objects rec to compare to first
   */
    public boolean collision(Dimension a, Dimension b)
    {
        if(Rect.intersects(a.getRectangle(), b.getRectangle()))
        {
            return true;
        }
        return false;
    }


    /*
 * draw, draws all the animations on canvas
 * @param     canvas      Canvas canvas, canvas to draw on
 */
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        final float scaleX = getWidth()/(WIDTH*1.f);
        final float scaleY = getHeight()/(HEIGHT*1.f);

        if(canvas!=null) {
            final int savedState = canvas.save();
            canvas.scale(scaleX, scaleY);
            backGround.draw(canvas);

            //draw the shark
            if(!playing) {
                shark.draw(canvas);
            }

            //draw blood
            for(Blood b: blood)
            {
                b.draw(canvas);
            }

            //draw fish
            for(Fish m: fish)
            {
                m.draw(canvas);
            }

            drawText(canvas);
            canvas.restoreToCount(savedState);
        }
    }


    /*
 * newGame resets all values to default at start of game
 */
    public void newGame()
    {
        // reset all game variables
        playing = false;
        fish.clear();
        blood.clear();
        numberEaten = 0;
        shark.resetDY();
        shark.setY(HEIGHT / 2);
        gameWon = false;
        newGameCreated = true;
    }


    /*
 * drawText, draws all the text on canvas
 * @param     canvas      Canvas canvas, canvas to draw on
 */
    public void drawText(Canvas canvas)
    {
        // print the number of fsh eaten to the screen
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(17);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("Number Eaten: " + numberEaten, 10, HEIGHT-10, paint);

        // when game is resetting print message to tap to start
        if(!shark.getPlaying()&&newGameCreated&&reset)
        {
            Paint paint1 = new Paint();
            paint1.setTextSize(15);
            paint1.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            canvas.drawText("Goodmornig! Tap TO START", 30, HEIGHT/2, paint1);
        }
    }
}