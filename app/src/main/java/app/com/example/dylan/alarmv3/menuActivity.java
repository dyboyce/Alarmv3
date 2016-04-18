package app.com.example.dylan.alarmv3;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.support.v7.widget.SwitchCompat;

/**
 * class menuActivity is the singleton design pattern that handles most of the work on calling and
 * organizing the app. It checks what games to call, and handles button clicks, transitions to the
 * alarmmain activity to set the alarm time.
 *
 * -Dylan
 *
 *
 */

public class menuActivity extends AppCompatActivity {

    private static int block = 1;
    private static int math = 1;
    private static int shark = 1;
    private int blockset = 1;
    private int mathset = 1;
    private int sharkset = 1;
    private int flaghigh = 0;
    private int win = 0;
    private static int buttonum = 0;


    /**
     * method onCreate checks for intent extras, if flags for games are up it processes the flag
     * and runs the game. If no flags are up, it presents the main menu
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent linker = getIntent();
        System.out.println("made it to on create");
        if (linker.hasExtra("flaghigh")) {
            System.out.println("I was called from an intent! ____________________________________________________________");
            Bundle linkerbund = linker.getExtras();
            block = linkerbund.getInt("block");
            math = linkerbund.getInt("math");
            shark = linkerbund.getInt("shark");
            win = linkerbund.getInt("win");
            buttonum = linkerbund.getInt("buttonum");
            System.out.println("new values: math: " + math + " block: " + block + " shark: " + shark + " button: " + buttonum + " win: " + win);
            if (math == 1) {
                //call math with rest
                math = 0;
                Intent mathcall = new Intent(this, mathMainActivity.class);
                mathcall.putExtra("flaghigh", 1);
                mathcall.putExtra("block", block);
                mathcall.putExtra("math", math);
                mathcall.putExtra("shark", shark);
                mathcall.putExtra("win", win);
                mathcall.putExtra("buttonum", buttonum);
                startActivity(mathcall);
            } else if (block == 1) {
                //call block with rest
                block = 0;
                Intent blockcall = new Intent(this, DroidzActivity.class);
                blockcall.putExtra("flaghigh", 1);
                blockcall.putExtra("block", block);
                blockcall.putExtra("math", math);
                blockcall.putExtra("shark", shark);
                blockcall.putExtra("win", win);
                blockcall.putExtra("buttonum", buttonum);
                startActivity(blockcall);
            } else if (shark == 1) {
                //call shark
                shark = 0;
                Intent sharkcall = new Intent(this, SharkAttack.class);
                sharkcall.putExtra("flaghigh", 1);
                sharkcall.putExtra("block", block);
                sharkcall.putExtra("math", math);
                sharkcall.putExtra("shark", shark);
                sharkcall.putExtra("win", win);
                sharkcall.putExtra("buttonum", buttonum);
                startActivity(sharkcall);
            } else if (win == 1) {
                //print you did it!
                //reset the right button
                setContentView(R.layout.activity_menu);
            } else {
                setContentView(R.layout.activity_menu);
            }

        }
        setContentView(R.layout.activity_menu);

    }

    /**
     * method onCreateOptionsMenu generates and populates the the settings menu to provide game
     * choices. Must return true or menu will not display
     *
     * @param menu
     * @return boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the bmenu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);

        return true;
    }

    /**
     * method onOptionsItemSelected handles the interfacing of objects in the menu
     *
     *
     * @param item
     * @return boolean
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

    /**
     * method getblock acts as an accessor for the block parameter
     * @return int block
     */
    public static int getblock() {
        return block;
    }

    /**
     * method getmath acts as an accessor for the math parameter
     * @return int math
     */
    public static int getmath() {
        return math;
    }

    /**
     * method getshark acts as an accessor for the shark parameter
     * @return
     */
    public static int getshark() {
        return shark;
    }

    /**
     * method getButtonum returns the button number parameter
     * @return
     */
    public static int getButtonum() {
        return buttonum;
    }

    /**
     * method buttonpressmenu handles user clicks on the 3 toggle buttons and calls the
     * AlarmMainActivity activity
     *
     * @param view v
     */
    public void buttonpressmenu(View v) {
        int duration = Toast.LENGTH_SHORT;
        Context context = getApplicationContext();
        CharSequence text = "Placeholding text, you shouldnt read this!";

        block = blockset;
        math = mathset;
        shark = sharkset;
        Intent intent = new Intent(this, AlarmMainActivity.class);
        intent.putExtra("flaghigh", 1);
        intent.putExtra("block", blockset);
        intent.putExtra("math", mathset);
        intent.putExtra("shark", sharkset);
        intent.putExtra("win", win);

        switch (v.getId()) {
            case R.id.toggleButton:
                text = "Start new activity";
                intent.putExtra("buttonum", 1);
                buttonum = 1;
                startActivity(intent);


                break;
            case R.id.toggleButton3:
                text = "Start new activity";
                intent.putExtra("buttonum", 2);
                buttonum = 2;
                startActivity(intent);

                break;
            case R.id.toggleButton2:
                text = "Start new activity";
                intent.putExtra("buttonum", 3);
                buttonum = 3;
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();



    }
}
