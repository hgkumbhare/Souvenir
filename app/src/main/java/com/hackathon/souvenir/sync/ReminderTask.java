package com.hackathon.souvenir.sync;

import android.content.Context;

import com.hackathon.souvenir.utilities.NotificationUtils;

/**
 * Created by gunjan on 08/03/18.
 */

public class ReminderTask {
    public static final String ACTION_REFILL_ITEMS = "refill-reminder";
    public static void executeTask(Context context, String action ) {
        if(ACTION_REFILL_ITEMS.equals(action)) {
            issueRefillReminder(context);
        }
    }

    private static void issueRefillReminder(Context context) {
        NotificationUtils.remindUserToRefill(context);
    }
}
