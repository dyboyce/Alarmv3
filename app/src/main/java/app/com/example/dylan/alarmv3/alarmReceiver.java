package app.com.example.dylan.alarmv3;
import android.media.RingtoneManager;
import android.media.Ringtone;
import android.net.Uri;

/**
 * Created by Dylan on 10/4/2015.
 */


        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.widget.Toast;

public class alarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Made it to the alarm stage", Toast.LENGTH_SHORT).show();
        System.out.println("Really did it guys");
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(context, notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

