package com.hackathon.souvenir;

import android.app.AlertDialog;
//import android.content.Intent;
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

               //EditText days = (EditText) findViewById(R.id.days);

               SQLiteDatabase sqLiteDatabase = MainActivity.db.getReadableDatabase();
               ArrayList<String> results = new ArrayList<>();

               try {
                   Cursor cursor = sqLiteDatabase.rawQuery("select * from " + DatabaseHelper.TABLENAME , null);
                   
                   if(cursor.getCount() == 0) {
                       // show message
                       showMessage("Error","Nothing found");
                       return;
                   }

                   StringBuffer buffer = new StringBuffer();
                   while (cursor.moveToNext()) {
                       buffer.append("Item :"+ cursor.getString(0)+"\n");
                       buffer.append("Measurement Unit :"+ cursor.getString(2)+"\n");
                       buffer.append("Usage :"+ cursor.getString(3)+"\n");
                       buffer.append("Available Quantity :"+ cursor.getString(4)+"\n");
                       buffer.append("Reminder before:"+ cursor.getString(6)+"\n");
                       if(cursor.getString(5) == "1") 
                           buffer.append("Using default consumption\n\n");
                       else 
                           buffer.append("Not using default consumption\n\n");
                   }

                   // Show all data
                   showMessage("Data",buffer.toString());
                   /*if (cursor != null) {
                       if (cursor.moveToFirst()) {
                           do {
                               String item_name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Item.item_name));
                               String usage = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Item.usage));
                               String available_quantity = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Item.available_quantity));
                               Log.d("data", item_name + usage + available_quantity);
                               Integer available_quantity_int = Integer.valueOf(available_quantity);
                               Integer usage_int = Integer.valueOf(usage);
                               //Integer days_int = Integer.valueOf(days.getText().toString());
                               *//*Integer val = available_quantity_int - usage_int * days_int;
                               if (val < 0) {
                                   results.add(item_name + "-" + -val);
                               }*//*
                           } while (cursor.moveToNext());
                       }
                   }*/

                   sqLiteDatabase.close();

                   /*Intent startIntend = new Intent(getApplicationContext(), DisplayList.class);
                   startIntend.putStringArrayListExtra("mylist", results);
                   startActivity(startIntend);*/

               } catch (SQLiteException se) {
                   Log.e(getClass().getSimpleName(), "Could not create or Open the database");
               }
           }
       });
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
