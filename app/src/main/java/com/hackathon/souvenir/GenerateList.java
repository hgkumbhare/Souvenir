package com.hackathon.souvenir;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class GenerateList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_list);


        Button addItem = (Button) findViewById(R.id.generateList);
        addItem.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               EditText days = (EditText) findViewById(R.id.days);

               SQLiteDatabase sqLiteDatabase = MainActivity.db.getReadableDatabase();
               ArrayList<String> results = new ArrayList<>();

               try {
                   Cursor cursor = sqLiteDatabase.rawQuery("select * from " + DatabaseHelper.TABLENAME, null);

                   if (cursor != null) {
                       if (cursor.moveToFirst()) {
                           do {
                               String item_name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Item.item_name));
                               String usage = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Item.usage));
                               String available_quantity = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Item.available_quantity));
                               Log.d("data", item_name + usage + available_quantity);
                               Integer available_quantity_int = Integer.valueOf(available_quantity);
                               Integer usage_int = Integer.valueOf(usage);
                               Integer days_int = Integer.valueOf(days.getText().toString());
                               Integer val = available_quantity_int - usage_int * days_int;
                               if (val < 0) {
                                   results.add(item_name + "-" + -val);
                               }
                           } while (cursor.moveToNext());
                       }
                   }

                   sqLiteDatabase.close();

                   Intent startIntend = new Intent(getApplicationContext(), DisplayList.class);
                   startIntend.putStringArrayListExtra("mylist", results);
                   startActivity(startIntend);

               } catch (SQLiteException se) {
                   Log.e(getClass().getSimpleName(), "Could not create or Open the database");
               }
           }
       });
    }
}
