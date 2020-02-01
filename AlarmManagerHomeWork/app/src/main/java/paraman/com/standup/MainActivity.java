package paraman.com.standup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID = 0;
    private static final String PRIMARY_CHANNEL_ID =
            "primary_notification_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToggleButton alarmToggle = findViewById(R.id.alarmToggle);
        final Intent notifyIntent = new Intent(this, AlarmReceiver.class);

        boolean alarmUp = (PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent,
                PendingIntent.FLAG_NO_CREATE) != null);
        alarmToggle.setChecked(alarmUp);

        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);


        createNotificationChannel();

        alarmToggle.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton,
                                                 boolean isChecked) {
                        String toastMessage;
                        if(isChecked){
                                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, getExactTime(),
                                        notifyPendingIntent);
                                toastMessage = "alarm is on "+ getExactTime();
                        } else {
                            if (alarmManager != null) {
                                alarmManager.cancel(notifyPendingIntent);
                            }
                            mNotificationManager.cancelAll();
                            //Set the toast message for the "off" case.
                            toastMessage = "alarm Off!";
                        }


                        //Show a toast to say the alarm is turned on or off.
                        Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT)
                                .show();

                    }
                });

    }


    /**
     * Creates a Notification channel, for OREO and higher.
     */
    public void createNotificationChannel() {

        // Create a notification manager object.
        mNotificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel
                    (PRIMARY_CHANNEL_ID,
                            "Make a Wish!",
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription
                    ("Notifies every 15 minutes to stand up and walk");
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }


    private long getExactTime()
    {
        Calendar now = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(System.currentTimeMillis());
        Log.d("TAG","Current time "+new Time(System.currentTimeMillis()));
        calendar.set(Calendar.HOUR_OF_DAY, 14);  // TODO : Change the time format according to your device, also change the time accordingly
        calendar.set(Calendar.MINUTE, 46);
        calendar.set(Calendar.SECOND, 0);
        long alarmTime;

        if (calendar.getTimeInMillis() <= now.getTimeInMillis())
            alarmTime=calendar.getTimeInMillis() + (AlarmManager.INTERVAL_DAY+1);
        else
            alarmTime=calendar.getTimeInMillis();
        return alarmTime;
    }


    // NOTE: Code can be used for different android version
//     if (SDK_INT < Build.VERSION_CODES.KITKAT) {
//        al.set(AlarmManager.RTC_WAKEUP,_alarm, fintent);
//    }
//        else if (Build.VERSION_CODES.KITKAT <= SDK_INT  && SDK_INT < Build.VERSION_CODES.M) {
//        al.setExact(AlarmManager.RTC_WAKEUP,_alarm,fintent);
//    }
//        else if (SDK_INT >= Build.VERSION_CODES.M) {
//        al.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,_alarm,fintent);
//    }
}
