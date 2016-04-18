package app.com.example.dylan.alarmv3;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.media.Ringtone;
import android.net.Uri;

/**
 * Created by Dylan on 10/4/2015.
 */


        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Class alarmReciever takes a pending intent set for a certain time, and is able to use special
 * permission WAKE_LOCK to start the app at the desired time
 *
 * -Dylan
 *
 *
 */
public class alarmReceiver extends BroadcastReceiver {

    private int block=0;
    private int math=0;
    private int shark=0;
    private int buttonum=1;
    private int win=0;

    /**
     *method onRecieve gets the pending intent at the correct time and triggers the menuActivity
     * with a valid intent to start games
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Wake Up!", Toast.LENGTH_SHORT).show();
        System.out.println("Really did it guys");
        Bundle linkerbund = intent.getExtras();
        block = linkerbund.getInt("block");
        math = linkerbund.getInt("math");
        shark = linkerbund.getInt("shark");
        System.out.println(" "+block+" "+math+" "+shark);
        win= linkerbund.getInt("win");
        buttonum=linkerbund.getInt("buttonum");
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            MediaPlayer mp = MediaPlayer.create(context, notification);
            mp.start();


            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                int Replay=0;int
                        numReplay=10;
                public void onCompletion(MediaPlayer mp) {
                    if( Replay < numReplay){
                        mp.start();
                        Replay++;
                    }
                }
            });
           Intent sintent = new Intent(context, menuActivity.class);
            block = menuActivity.getblock();
            math = menuActivity.getmath();
            shark = menuActivity.getshark();
            buttonum = menuActivity.getButtonum();
            sintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            System.out.println(" "+block+" "+math+" "+shark);
            sintent.putExtra("flaghigh",1);
            sintent.putExtra("block",block);
            sintent.putExtra("math",math);
            sintent.putExtra("shark",shark);
            sintent.putExtra("buttonum",buttonum);
            sintent.putExtra("win",0);
            System.out.println("Tried to start new activity");
            context.startActivity(sintent);
            mp.release();
            mp.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

