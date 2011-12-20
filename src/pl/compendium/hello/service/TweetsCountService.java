package pl.compendium.hello.service;

import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.google.inject.Inject;
import pl.compendium.hello.intent.MyIntents;
import pl.compendium.hello.twitter.SQLiteTwitter;
import roboguice.service.RoboService;

import java.util.Timer;
import java.util.TimerTask;

public class TweetsCountService extends RoboService {

    private static final String TAG = TweetsCountService.class.getSimpleName();

    @Inject
    SQLiteTwitter twitter;

    Timer timer = new Timer();

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(TAG, "Starting this service...");

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.i(TAG, "Will check tweet count...");

                int tweetCount = twitter.publicTimelineSize();

                Intent intent = new Intent(MyIntents.TWEET_COUNT_ACTION);
                intent.putExtra("count", tweetCount);
                sendBroadcast(intent);
            }
        }, 1000 /*ms*/, 5000 /*ms*/);
    }

    private void showNotification() {
        long when = System.currentTimeMillis();

//        Notification notification = new Notification(R.drawable.ic_kapibara, title, when);
//
//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(this,
//                                                                0,
//                                                                notificationIntent,
//                                                                0);
//
//        notification.setLatestEventInfo(context, title, msg, contentIntent);
//        notification.flags = Notification.FLAG_AUTO_CANCEL;
//
//        notificationManager.notify(NOTIFICATION_ID, notification);
//
//                notificationManager.notify(NOTIFICATION_ID, notification);
    }
}
