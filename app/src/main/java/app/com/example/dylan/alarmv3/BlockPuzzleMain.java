package app.com.example.dylan.alarmv3;

/**
 * Created by Aaron on 12/1/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;



public class BlockPuzzleMain extends SurfaceView implements SurfaceHolder.Callback {

    private int block=0;
    private int math=0;
    private int shark=0;
    private int flaghigh=0;
    private int win=0;
    private int buttonum=0;



    private static final String TAG = BlockPuzzleMain.class.getSimpleName();

    private puzzMainThread thread;
    private Droid droid;
    private Droid droid1;
    int x, y, p, z;
    private Bitmap question;
    private Bitmap tunnel;
    private Context xy;
    MediaPlayer mp;

    /*
    * @param context - this parameter is passed in from the onCreate method in the DroidzActivity.
    */
    public BlockPuzzleMain(Context context)
    {
        //context parameter is passed on to SurfaceView class.

        super(context);
        //variable is initialized.
        xy=context;
        getHolder().addCallback(this);
        droid = new Droid(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), 400, 1000);
        droid1 = new Droid(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), 100, 1000);
        Log.d(TAG, "Droid object created!");
        thread = new puzzMainThread(getHolder(), this);
        question = BitmapFactory.decodeResource(getResources(), R.mipmap.questionmark);
        tunnel = BitmapFactory.decodeResource(getResources(), R.mipmap.tunnel);
        x = 0;
        y = 0;
        p = 2;
        z = 0;
        //This sets the screen to be touchable.
        setFocusable(true);
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        //Sets the alarm to play
        final AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int origionalVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_ALARM);
        mp = MediaPlayer.create(context, notification);
        mp.start();


        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            int Replay=0;int
                    numReplay=10;
            public void onCompletion(MediaPlayer mp) {
                if( Replay < numReplay){
                    mp.setVolume(1.0f,1.0f);
                    mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mAudioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM), 0);
                    mp.start();
                    Replay++;
                }
            }
        });

    }

    public void setints(int b, int m, int s, int fh, int w, int btn){

        block=b;
        math=m;
        shark=s;
        flaghigh=fh;
        win=w;
        buttonum=btn;

    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    /*
  * this method sets the running variable to true and starts the thread.
  * @param SurfaceHolder
  */
    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
        thread.setRunning(true);
        thread.start();
    }

    /*
  * this method destroys the game.
  * @param SurfaceHolder
  */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        Log.d(TAG, "Surface is being destroyed");
        boolean retry = true;
        while (retry)
        {
            try {
                thread.join();
                retry = false;
            }
            catch (InterruptedException e)
            {

            }
        }
        Log.d(TAG, "Thread was shut down cleanly");
    }

    /*
   * this method determines if the droid object is touched or not.
   * @param event
   */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eve = event.getAction();
        //eve is set to the action value of the event;
        switch (eve) {
            case MotionEvent.ACTION_MOVE:
                // this applies for moving the droid.
                if (droid.isTouched(event.getX(), event.getY()))
                {
                    droid.setX((int) event.getX());

                }
                if (droid1.isTouched(event.getX(), event.getY())) {
                    droid1.setY((int) event.getY());

                }
                break;
            case MotionEvent.ACTION_UP:

                break;


        }
        return true;

    }

    /*
 * @param - canvas; this takes in a canvas which is like a screen background.
 */
    @Override
    public void onDraw(Canvas canvas)
    {
        canvas.drawColor(Color.BLACK);
        droid.draw(canvas);
        droid1.draw(canvas);
        //this draws the green rectangles on the canvas.
        Rect rectangle = new Rect();
        rectangle.set(900, canvas.getHeight() / 2 - y, 700, canvas.getHeight() - y);
        Paint green = new Paint();
        green.setColor(Color.GREEN);
        green.setStyle(Paint.Style.FILL);
        //The rectangle coordinates are changing every time the onDraw method is called.
        canvas.drawRect(rectangle, green);

        Rect rectangle1 = new Rect();
        rectangle1.set(1300, canvas.getHeight() / 2 + y, 1100, y);

        canvas.drawRect(rectangle1, green);

        Rect rectangle2 = new Rect();
        rectangle2.set(1300, canvas.getHeight()/2 - y, 1100, canvas.getHeight() - y);

        canvas.drawRect(rectangle2, green);

        Rect rectangle3 = new Rect();
        rectangle3.set(1600, canvas.getHeight() / 2 + y, 1400, y);

        canvas.drawRect(rectangle3, green);

        Rect rectangle4 = new Rect();
        rectangle4.set(300+x, 500, 0+x, 200);

        canvas.drawRect(rectangle4, green);
        //In beginning of game instructions are posted.
        if (p < 3)
        {
            Paint white = new Paint();
            white.setColor(Color.WHITE);
            white.setTextSize(50);
            white.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            canvas.drawText("Drag the droids to the checkpoints and find the tunnel!", 100, 300, white);
        }
        //Increases x and y values of rectangles.
        if (p%2 == 0)
        {
            x += 5;
            y += 5/2;
            if (x == 1000)
            {
                p += 1;
            }
        }
        if (p % 2 == 1)
        {
            x -= 5;
            y -= 5/2;
            if (x == 0)
            {
                p += 1;
            }
        }
        //Checkpoints are drawn.
        canvas.drawBitmap(question, 1700, 900, new Paint());
        canvas.drawBitmap(question, 50, 50, new Paint());
        //Droid gets set back if it touches rectangle.
        if ((rectangle.centerY() + (canvas.getHeight() / 4)) > (droid.getY() - 50) && droid.getX() - 50 > 700 && droid.getX() + 50 < 900 || (rectangle2.centerY() + (canvas.getHeight() / 4)) > (droid.getY() - 50) && droid.getX() - 50 > 1100 && droid.getX() + 50 < 1300)
        {
            droid.setX(400);
            droid.setY(1000);

        }

        if ((rectangle4.centerX() - 150 < (droid1.getX() + 50) && droid1.getY() - 50 < 500 && droid1.getY() + 50 > 200))
        {
            droid1.setX(100);
            droid1.setY(1000);
        }


        if (droid.getX() > 1600 && droid1.getY() < 150)
        {
            z += 1;
        }

        if (z >= 1)
        {
            canvas.drawBitmap(tunnel, 50, 900, new Paint());
        }


        if (z >= 1 && droid.getX() < 150 && droid1.getY() > 850)
        {
            Paint white = new Paint();
            white.setColor(Color.WHITE);
            white.setTextSize(200);
            white.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            canvas.drawText("Good Morning!", 300, 300, white);
            puzzMainThread.setoff();
            Context con = getContext();
            Intent winblocks = new Intent(con, menuActivity.class);

            winblocks.putExtra("flaghigh",1);
            winblocks.putExtra("block",0);
            winblocks.putExtra("math",math);
            winblocks.putExtra("shark",shark);
            winblocks.putExtra("win",1);
            winblocks.putExtra("buttonum",buttonum);
            mp.stop();
            //stops the alarm sounds
            con.startActivity(winblocks);

            mp.release();
        }

    }



}

