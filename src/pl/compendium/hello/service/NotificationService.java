package pl.compendium.hello.service;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.IBinder;
import roboguice.service.RoboService;

import javax.inject.Inject;

public class NotificationService extends RoboService {

    private static final int NOTIFICATION_ID = 1337;

    @Inject
    NotificationManager notificationManager;

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        showNotification();
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
