package app.com.example.dylan.alarmv3;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

/**
 *Created By Aaron
 */



    public class DroidzActivity extends Activity
    {
        private static final String TAG = DroidzActivity.class.getSimpleName();


        private int block=0;
        private int math=0;
        private int shark=0;
        private int flaghigh=0;
        private int win=0;
        private int buttonum=0;




        /*
* This method is called first once the code is compiled.
* @param Bundle - a mapping of String values to various Parceable types
*/
        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            //Calls the onCreate method from the Activity class.
            super.onCreate(savedInstanceState);
            Intent linker=getIntent();
            Bundle linkerbund=linker.getExtras();
            block = linkerbund.getInt("blocks");
            math = linkerbund.getInt("math");
            shark = linkerbund.getInt("shark");
            win= linkerbund.getInt("win");
            buttonum=linkerbund.getInt("buttonum");


            requestWindowFeature(Window.FEATURE_NO_TITLE);
            //takes up the whole screen of phone.
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            BlockPuzzleMain droidact = new BlockPuzzleMain(this);
            droidact.setints(block,math,shark,flaghigh,win,buttonum);
            setContentView(droidact);
            //Log.d(TAG, "View added");
        }

        //Once the app is exited then it is destroyed.
        @Override
        protected void onDestroy()
        {
            Log.d(TAG, "Destroying...");
            super.onDestroy();
        }
        //App is stopped if interruptions occur.
        @Override
        protected void onStop()
        {
            Log.d(TAG, "Stopping...");
            super.onStop();
        }
    }
