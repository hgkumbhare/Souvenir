package com.hackathon.souvenir;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdateItem extends AppCompatActivity {

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*//*
                SQLiteDatabase sqLiteDatabase = MainActivity.db.getReadableDatabase();
            }
        });
    }*/
    private ArrayList<String> results = new ArrayList<String>();
    private HashMap<String, String> item_quantity = new HashMap<>();
    private String tableName = DatabaseHelper.TABLENAME;
    private Spinner spinner1;
    private Button btnSubmit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_update_item);
        openAndQueryDatabase();

        addListenerOnButton();
        addListenerOnSpinnerItemSelection();

    }

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Integer added_quantity  = 0;
                Integer new_usage = 0;
                Integer prev_quantity = 0;
                String item_name = String.valueOf(spinner1.getSelectedItem());
                EditText edit_measurement_unit = (EditText)findViewById(R.id.measurement_unit);
                EditText edit_usage = (EditText)findViewById(R.id.usage);
                EditText edit_available_quantity = (EditText)findViewById(R.id.available_quantity);
                EditText edit_reminder_before_days = (EditText)findViewById(R.id.reminder_before_days);

                ContentValues contentValues = new ContentValues();

                Log.d("SEE HERE measurement_unit :",edit_measurement_unit.getText().toString());
                Log.d("SEE HERE usage :",edit_usage.getText().toString());
                Log.d("SEE HERE available_quantity :",edit_available_quantity.getText().toString());
                Log.d("SEE HERE reminder_before_days :",edit_reminder_before_days.getText().toString());

                contentValues.put(DatabaseHelper.Item.item_name, item_name);
                if(edit_measurement_unit.getText().toString().length() > 0)
                    contentValues.put(DatabaseHelper.Item.measurement_unit, edit_measurement_unit.getText().toString());

                if(edit_available_quantity.getText().toString().length() > 0) {
                    added_quantity = Integer.valueOf(edit_available_quantity.getText().toString());
                }
                if(edit_usage.getText().toString().length() > 0) {
                    new_usage = Integer.valueOf(edit_usage.getText().toString());
                    contentValues.put(DatabaseHelper.Item.use_default_consumption, "0");
                }

                Integer remaining_quantity = Integer.valueOf(item_quantity.get(item_name)) + added_quantity - new_usage ;
                contentValues.put(DatabaseHelper.Item.available_quantity, remaining_quantity.toString());

                if(edit_reminder_before_days.getText().toString().length() > 0)
                    contentValues.put(DatabaseHelper.Item.reminder_before_days, edit_reminder_before_days.getText().toString());

                MainActivity.db.update(contentValues, item_name);
                Toast.makeText(UpdateItem.this,
                        "Updated Item_name : "+ String.valueOf(spinner1.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
                Intent startIntend = new Intent(getApplicationContext(), Features.class);
                startActivity(startIntend);
            }

        });
    }

    private void openAndQueryDatabase() {
        SQLiteDatabase sqLiteDatabase = MainActivity.db.getReadableDatabase();

        spinner1 = (Spinner) findViewById(R.id.spinner1);

        try {
            Cursor cursor = sqLiteDatabase.rawQuery("select * from "+tableName,null);

            if (cursor != null ) {
                if (cursor.moveToFirst()) {
                    do {
                        String item_name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Item.item_name));
                        results.add(item_name);
                        item_quantity.put(item_name,cursor.getString(cursor.getColumnIndex(DatabaseHelper.Item.available_quantity)));
                    }while (cursor.moveToNext());
                }
            }

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, results);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner1.setAdapter(dataAdapter);
            sqLiteDatabase.close();

        } catch (SQLiteException se ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        }

    }
}
