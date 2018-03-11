package com.hackathon.souvenir.utilities;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import com.hackathon.souvenir.R;
import com.hackathon.souvenir.AddItem;
import com.hackathon.souvenir.MainActivity;

/**
 * Created by gunjan on 06/03/18.
 */

public class NotificationUtils {
    private static int REQUEST_CODE_DUMMY_INT = 1234;
    private static final String NOTIFICATION_CHANNEL_ID = "reminder_notification_channel";
    private static int REFILL_REMINDER_ID = 1111;

    //This will be triggered when notification is pressed. This should show mainActivity
    private static PendingIntent contentIntent(Context context) {
        //create an activity that will open MainActivity
        Intent startActivityIntent = new Intent(context, MainActivity.class);
        return PendingIntent.getActivity(context, REQUEST_CODE_DUMMY_INT ,startActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
    private static Bitmap largeIcon(Context context) {
        Resources res = context.getResources();
        Bitmap retLargeIcon = BitmapFactory.decodeResource(res, android.R.drawable.btn_star);
        return  retLargeIcon;
    }
    public static void remindUserToRefill(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //after oreo, notification channel is required
            NotificationChannel mChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, context.getString(R.string.main_notification_channel_name), NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                //.setColor()
                .setSmallIcon(android.R.drawable.btn_star)
                .setLargeIcon(largeIcon(context))
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentText(context.getString(R.string.notification_text))
                .setStyle(new NotificationCompat.BigTextStyle()
                .bigText("Much longer text that cannot fit one line..."))
                .setContentIntent(contentIntent(context))
                .setAutoCancel(true);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }

        notificationManager.notify(REFILL_REMINDER_ID, notificationBuilder.build());
    }
}
