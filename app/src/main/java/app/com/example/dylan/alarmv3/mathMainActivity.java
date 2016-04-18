package app.com.example.dylan.alarmv3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.media.RingtoneManager;
import android.media.Ringtone;
import android.net.Uri;
import android.media.MediaPlayer;
import android.media.AudioManager;


import java.util.ArrayList;

/**
 *Created by Ai Nakamura
 *
 */


/**
 * Class mathMainActivity organizes and runs the math equation game, relies on
 * the instances of MathEquation.java to get its random numbers
 */
public class mathMainActivity extends AppCompatActivity {



    private int block=0;
    private int math=0;
    private int shark=0;
    private int flaghigh=0;
    private int win=0;
    private int buttonum=0;
    MediaPlayer mp;

    MathEquation me = new MathEquation();
    ArrayList<String> positiveReinforcements = new ArrayList<String>();
    int counter = 0;


    /**
     * method onCreate effectively runs the game loop and reads in game flags
     * @param savedInstanceState
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

        //Creates the alarmsounds --------------------------------------------
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        final AudioManager mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int origionalVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_ALARM);

        mp = MediaPlayer.create(getApplicationContext(), notification);
        mp.start();


        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            int Replay=0;int
                    numReplay=15;
                                       public void onCompletion(MediaPlayer mp) {
                                           if( Replay < numReplay){
                                               mp.setVolume(1.0f,1.0f);
                                               mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mAudioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM), 0);
                                               mp.start();
                                               Replay++;
                                           }
                                       }
        });

        //Creates the alarmsounds ------------------------------------------
                setContentView(R.layout.activity_math_main);

        positiveReinforcements.add("You got this! Just five to get right!");
        positiveReinforcements.add("One down, four more to go!");
        positiveReinforcements.add("Way to go! That's two!");
        positiveReinforcements.add("Three out of five! Almost there!");
        positiveReinforcements.add("Just one more!!!");
        //1. Display the equation
        setEquation();
        //2. Assign the different functions to the buttons
        setButtons();
        //3. Set counter, then change PositiveReinforcement display
        setPositiveReinforcements();
    }


    /**
     * method setPositiveReinforcements displays new positive messages
     */
    private void setPositiveReinforcements() {
        TextView pr = (TextView)findViewById(R.id.textView2);
        pr.setText(positiveReinforcements.get(counter));
    }

    /**
     *
     * method setEquation gets a new equation from the java helper class
     */
    private void setEquation() {
        TextView equation = (TextView)findViewById(R.id.textView);
        equation.setText(me.toString());
    }

    /**
     * method setButtons decides the random layout of the buttons
     * and generates text changes for objects on the layout
     *
     */
    private void setButtons() {
        //Get reference to buttons
        Button one = (Button)findViewById(R.id.button);
        Button two = (Button)findViewById(R.id.button2);
        Button three = (Button)findViewById(R.id.button3);
        Button four = (Button)findViewById(R.id.button4);
        ArrayList<Button> list = new ArrayList<Button>();
        list.add(one);
        list.add(two);
        list.add(three);
        list.add(four);

        //decide which button will hold the correct one
        Button ans;
        int decider = ((int)(Math.random()*10)) % 4;
        switch(decider) {
            case 1:
                ans = one;
                break;
            case 2:
                ans = two;
                break;
            case 3:
                ans = three;
                break;
            default:
                ans = four;
                break;
        }
        ans.setText(Character.toString(me.getAns()));
        //make list of only wrong characters
        ArrayList<Character> wrong = new ArrayList<Character>();
        wrong.add('+');
        wrong.add('-');
        wrong.add('*');
        wrong.add('/');
        for (int i = 0; i < wrong.size(); i++) {
            if (wrong.get(i) == me.getAns()) {wrong.remove(i);}
        }
        //first set all buttons to display a "you got it wrong" Toast message
        int wrongIndex = 0;
        for (Button b : list) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("ErrorButton", "You clicked a wrong button");
                    Toast.makeText(
                            mathMainActivity.this,
                            "EEEEGGHH. Start over sleepy head!",
                            Toast.LENGTH_SHORT
                    ).show();
                    counter=0;
                    setPositiveReinforcements();
                }
            });
            if (ans != b) {
                try {
                    b.setText(Character.toString(wrong.get(wrongIndex)));
                    wrongIndex++;
                }
                catch (Exception e) {
                    System.out.println(e.fillInStackTrace());
                }
            }
        }
        //now set ans button to be the correct one
        ans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("RightOne", "You clicked good!");
                Toast.makeText(
                        mathMainActivity.this,
                        "GoodJob!",
                        Toast.LENGTH_SHORT
                ).show();
                counter++;
                if (counter == positiveReinforcements.size()) {
                    //*********************************************************************
                    //we've reached the number needed to be correct. Go to win screen.
                    // Intent intent = new Intent(mathMainActivity.this, WinActivity.class);
                    //startActivity(intent);
                    //find a way to prevent the rest of this code from executing -- the page is crashing
                    //instead of just going to the new page.
                    System.out.println("finish is crashing it");
                    Intent winmath = new Intent(getApplicationContext(), menuActivity.class);
                    winmath.putExtra("flaghigh",1);
                    winmath.putExtra("block",block);
                    winmath.putExtra("math",math);
                    winmath.putExtra("shark",shark);
                    winmath.putExtra("win",1);
                    winmath.putExtra("buttonum",buttonum);
                    mp.stop();
                    mp.release();
                    startActivity(winmath);

                } else {
                    me = new MathEquation();
                    setEquation();
                    setButtons();
                    setPositiveReinforcements();
                }
            }
        });



    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

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

    /*
    Tester code

    @Override
    protected void onDestroy()
    {
        Toast.makeText(mathMainActivity.this,"onDestroy Called.", Toast.LENGTH_SHORT);
        System.out.println("onDestroy called");
        super.onDestroy();
    }

    @Override
    protected void onStop()
    {
        Toast.makeText(mathMainActivity.this,"onStop Called.", Toast.LENGTH_SHORT);
        System.out.println("onStop called");
        super.onStop();
    }

    @Override
    public void onBackPressed()
    {
        Toast.makeText(mathMainActivity.this,"onBackPressed Called.", Toast.LENGTH_SHORT);
        System.out.println("onBackpressed called");
        super.onPause();
    }
    @Override
    protected void onPause()
    {
        Toast.makeText(mathMainActivity.this,"onPause Called.", Toast.LENGTH_SHORT);
        System.out.println("onPause called");
        super.onPause();
    }
    @Override
    public void finish(){
        Toast.makeText(mathMainActivity.this,"Finish Called.", Toast.LENGTH_SHORT);
        System.out.println("Finish called");
        super.finish();
    }*/

}