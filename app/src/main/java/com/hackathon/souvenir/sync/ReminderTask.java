package com.hackathon.souvenir.sync;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.hackathon.souvenir.DatabaseHelper;
import com.hackathon.souvenir.MainActivity;
import com.hackathon.souvenir.utilities.NotificationUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gunjan on 08/03/18.
 */

public class ReminderTask {
    public static final String ACTION_REFILL_ITEMS = "refill-reminder";
    public static void executeTask(Context context, String action) {
        if(ACTION_REFILL_ITEMS.equals(action)) {
            issueRefillReminder(context);
        }
    }

    private static void modifyList() {
        //SQLiteDatabase sqLiteDatabase = MainActivity.db.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("item_name", "milk");
        contentValues.put("use_default_consumption", "0");
        MainActivity.db.update(contentValues, "milk");
//        ArrayList<String> results = new ArrayList<>();
//        HashMap<String, ContentValues> hmap = new HashMap<>();
//        try {
//            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + DatabaseHelper.TABLENAME, null);
//
//            if (cursor != null) {
//                if (cursor.moveToFirst()) {
//                    do {
//                        ContentValues contentValues = new ContentValues();
//                        String item_name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Item.item_name));
//                        contentValues.put("item_name", item_name);
//
//                        if (cursor.getString(cursor.getColumnIndex(DatabaseHelper.Item.use_default_consumption)) == "1") {
//                            String usage = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Item.usage));
//                            Integer usage_int = Integer.valueOf(usage);
//                            String available_quantity = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Item.available_quantity));
//                            Integer available_quantity_int = Integer.valueOf(available_quantity);
//                            available_quantity_int = available_quantity_int - usage_int;
//                            if (available_quantity_int < 0)
//                                available_quantity_int = 0;
//                            contentValues.put("available_quantity", available_quantity_int.toString());
//                        } else {
//                            contentValues.put("use_default_consumption", "1");
//                        }
//                        hmap.put(item_name, contentValues);
//                    } while (cursor.moveToNext());
//                }
//            }
//            sqLiteDatabase.close();
//        } catch (SQLiteException se) {
//            //Log.e(getClass().getSimpleName(), "Could not create or Open the database");
//        }
//
//        for (Map.Entry<String, ContentValues> entry : hmap.entrySet()) {
//            String item_name = entry.getKey();
//            ContentValues contentValues = entry.getValue();
//
//            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//            builder.setCancelable(true);
//            builder.setTitle("in hashmp");
//            builder.setMessage(contentValues.get("use_default_consumption").toString());
//            builder.show();
//
//            MainActivity.db.update(contentValues, item_name);
//        }

    }
    private static void issueRefillReminder(Context context) {
        modifyList();
        NotificationUtils.remindUserToRefill(context);
    }
}
