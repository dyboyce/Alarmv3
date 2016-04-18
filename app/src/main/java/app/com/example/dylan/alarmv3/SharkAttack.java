package app.com.example.dylan.alarmv3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.support.design.widget.*;
import android.support.design.widget.*;

/**
 * Created by Rich on 11/21/2015.
 */


/**
 * class Sharkattack creates and instance of the game sharkAttack
 */
public class SharkAttack extends Activity {

    private int block=0;
    private int math=0;
    private int shark=0;
    private int flaghigh=0;
    private int win=0;
    private int buttonum=0;

    /**
     * method onCreate sets the intial enviorment for game launch
     * @param  savedInstanceState    bundle of previous state
     * @Override
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent linker=getIntent();
        Bundle linkerbund=linker.getExtras();
        block = linkerbund.getInt("block");
        math = linkerbund.getInt("math");
        shark = linkerbund.getInt("shark");
        win= linkerbund.getInt("win");
        buttonum=linkerbund.getInt("buttonum");
        //turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        GamePanel gmp =new GamePanel(this);
        gmp.setints(block,math,shark,flaghigh,win,buttonum);
        setContentView(gmp);
    }


    /**
     * method onCreateOptionsMenu of boolean setUp
     * @param  menu    menu variable
     * @Override
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(,menu);
        return true;
    }

    /**
     * method to set boolean of ite selected
     * @param  item    menuItem variable
     * @Override
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}