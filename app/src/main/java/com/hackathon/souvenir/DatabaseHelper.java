package com.hackathon.souvenir;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hgkumbhare on 28/2/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "souvenir.db";

    /*
    items
    Primary key : item_name (String)
    email_address (String)
    one_unit (Bigdecimal)
    cost_of_one_unit (Bigdecimal)
    measurement_unit (String)(options-ml/gms)
    usage in ml/gm (Bigdecimal)
    usage_time_period (Bigdecimal)(in days)
    available in ml/gm(Bigdecimal)
    reminder_before_days (Integer)

     */
    public static final String TABLENAME = "items";

    public class Item{
        public static final String item_name = "item_name";
        public static final String email_address = "email_address";
        public static final String one_unit = "one_unit";
        public static final String cost_of_one_unit = "cost_of_one_unit";
        public static final String measurement_unit = "measurement_unit";
        public static final String usage = "usage";
        public static final String usage_time_period = "usage_time_period";
        public static final String available = "available";
        public static final String reminder_before_days = "reminder_before_days";
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + TABLENAME
                +" ( "
                + Item.item_name+" STRING PRIMARY KEY, "
                + Item.email_address+ " STRING, "
                + Item.one_unit+ " REAL, "
                + Item.cost_of_one_unit+ " REAL, "
                + Item.measurement_unit+ " STRING, "
                + Item.usage+ " REAL,"
                + Item.usage_time_period+ " INTEGER, "
                + Item.available+ " REAL, "
                + Item.reminder_before_days + " INTEGER "
                +")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // You can delete tables here
        onCreate(db);
    }

    public boolean insert(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(TABLENAME, null, contentValues);
        if(result == -1) {
            return false;
        }
        else {
            return true;
        }
    }
}
