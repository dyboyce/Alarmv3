package app.com.example.dylan.alarmv3;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import java.util.Calendar;
import android.widget.TimePicker;
import android.app.AlarmManager;


/**
 *class AlarmMainActivity opens a time picker layout and sends a pending intent to the system
 * allowing people to chose when to set the alarm
 *
 * -Dylan
 *
 */
public class AlarmMainActivity extends AppCompatActivity {
    private PendingIntent pIntent;
    Calendar alarmCalendar = Calendar.getInstance();
    Calendar testCal = Calendar.getInstance();
    private TimePicker altimePicker;
    AlarmManager mang;

    private int block=0;
    private int math=0;
    private int shark=0;
    private int win=0;
    private int buttonum=1;

    /**
     * method onCreate reads in the flags for which games to run for the alarm
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_main);
        Intent linker =getIntent();
        Bundle linkerbund = linker.getExtras();
        block = linkerbund.getInt("block");
        math = linkerbund.getInt("math");
        shark = linkerbund.getInt("shark");
        win= linkerbund.getInt("win");
        buttonum=linkerbund.getInt("buttonum");

        Intent alarmIntent = new Intent(AlarmMainActivity.this, alarmReceiver.class);
        pIntent = PendingIntent.getBroadcast(AlarmMainActivity.this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        altimePicker = (TimePicker) findViewById(R.id.timePicker);
        mang= (AlarmManager) getSystemService(ALARM_SERVICE);

    }

    /**
     * method startalarm sends the pending intent to the system to later be processed by the
     * alarm reciever
     *
     * @param pIntent
     */
    public void startalarm(PendingIntent pIntent){
        alarmCalendar.set(Calendar.HOUR_OF_DAY, altimePicker.getCurrentHour());
        alarmCalendar.set(Calendar.MINUTE, altimePicker.getCurrentMinute());
        mang.set(AlarmManager.RTC_WAKEUP, alarmCalendar.getTimeInMillis(), pIntent);
        String disp="Alarm was set for!"+alarmCalendar.getTime();
        disp="Actual time: "+testCal.getTime();

    }

    /**
     * method buttonPress handles the clicks to the "SET ALARM" button of the timepicker
     * and calls 'startalarm'
     *
     * @param view v
     */
    public void buttonPress(View v) {
        int duration = Toast.LENGTH_SHORT;
        Context context = getApplicationContext();

        switch (v.getId()) {
            case R.id.setbutton:

                Intent myIntent = new Intent(AlarmMainActivity.this, alarmReceiver.class);
                myIntent.putExtra("flaghigh",1);
                myIntent.putExtra("block",block);
                myIntent.putExtra("math",math);
                myIntent.putExtra("shark",shark);
                myIntent.putExtra("win",win);
                myIntent.putExtra("buttonum",buttonum);
                pIntent = pIntent.getBroadcast(AlarmMainActivity.this, 0, myIntent, 0);

                startalarm(pIntent);
                finish();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alarm_main, menu);
        return true;
    }




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
