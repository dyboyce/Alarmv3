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



public class AlarmMainActivity extends AppCompatActivity {
    private PendingIntent pIntent;
    Calendar alarmCalendar = Calendar.getInstance();
    Calendar testCal = Calendar.getInstance();
    private TimePicker altimePicker;
    AlarmManager mang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_main);

        Intent alarmIntent = new Intent(AlarmMainActivity.this, alarmReceiver.class);
        pIntent = PendingIntent.getBroadcast(AlarmMainActivity.this, 0, alarmIntent, 0);
        altimePicker = (TimePicker) findViewById(R.id.timePicker);
        mang= (AlarmManager) getSystemService(ALARM_SERVICE);

    }

    public void startalarm(PendingIntent pIntent){
        alarmCalendar.set(Calendar.HOUR_OF_DAY, altimePicker.getCurrentHour());
        alarmCalendar.set(Calendar.MINUTE, altimePicker.getCurrentMinute());
        mang.set(AlarmManager.RTC_WAKEUP, alarmCalendar.getTimeInMillis(), pIntent);
        System.out.println("Alarm was set for!"+alarmCalendar.getTime());
        System.out.println("Actual time: "+testCal.getTime());
    }

    public void buttonPress(View v) {
        int duration = Toast.LENGTH_SHORT;
        Context context = getApplicationContext();
        CharSequence text = "Placeholding text, you shouldnt read this!";
        Toast toast = Toast.makeText(context, text, duration);
        switch (v.getId()) {
            case R.id.setbutton:
                text = "Time should go here";
                toast = Toast.makeText(context, text, duration);
                toast.show();
                Intent myIntent = new Intent(AlarmMainActivity.this, alarmReceiver.class);
                pIntent = pIntent.getBroadcast(AlarmMainActivity.this, 0, myIntent, 0);
                startalarm(pIntent);
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
