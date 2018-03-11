package com.hackathon.souvenir;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by hgkumbhare on 28/2/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    /*
    For any change in database, change database name or increase the count
     */
    public static final String DATABASE_NAME = "souvenir1.db";

    /*
    Primary key : item_name (String)
    email_address (String)
    measurement_unit (String)(options-ml/gms)
    usage (Bigdecimal)(in one days)
    available_quantity in ml/gm(Bigdecimal)
    use_default_consumption
    reminder_before_days (Integer)

     */
    public static final String TABLENAME = "items";

    public class Item{
        public static final String item_name = "item_name";
        public static final String email_address = "email_address";
        public static final String measurement_unit = "measurement_unit";
        public static final String usage = "usage";
        public static final String available_quantity = "available_quantity";
        public static final String use_default_consumption = "use_default_consumption";
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
                + Item.measurement_unit+ " STRING, "
                + Item.usage+ " REAL, "
                + Item.available_quantity+ " REAL, "
                + Item.use_default_consumption + " INTEGER, "
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
        Log.d("INFO","Database Opened"+result);
        db.close();
        if(result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean update(ContentValues contentValues, String item_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.update(TABLENAME, contentValues, "item_name = ?", new String[] { item_name });
        Log.d("INFO", "Database Opened"+result);
        db.close();
        if(result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean update2(ContentValues contentValues, String item_name, SQLiteDatabase db) {
        //SQLiteDatabase db = this.getWritableDatabase();
        long result = db.update(TABLENAME, contentValues, "item_name = ?", new String[] { item_name });
        Log.d("INFO", "Database Opened"+result);
        db.close();
        if(result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public void delete(String primaryKey) {
        //Open the database
        SQLiteDatabase db = this.getWritableDatabase();

        //Execute sql query to remove from database
        //NOTE: When removing by String in SQL, value must be enclosed with ''
        Log.d("DELETING",primaryKey);
        db.execSQL("DELETE FROM " + TABLENAME + " WHERE " + Item.item_name + "= '" + primaryKey + "'");
        Log.d("DELETED",primaryKey);

        //Close the database
        db.close();
    }

}
