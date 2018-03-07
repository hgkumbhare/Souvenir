package com.hackathon.souvenir;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteItem extends Activity {

    private ArrayList<String> results = new ArrayList<String>();
    private String tableName = DatabaseHelper.TABLENAME;
    private Spinner spinner1;
    private Button btnSubmit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_delete_item);
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

        btnSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(DeleteItem.this,
                        "Deleted Item : " +
                                "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
                MainActivity.db.delete(String.valueOf(spinner1.getSelectedItem()));
            }

        });
    }

    private void openAndQueryDatabase() {
        SQLiteDatabase sqLiteDatabase = MainActivity.db.getReadableDatabase();

        spinner1 = (Spinner) findViewById(R.id.spinner1);

        try {
            Cursor  cursor = sqLiteDatabase.rawQuery("select * from "+tableName,null);

            if (cursor != null ) {
                if (cursor.moveToFirst()) {
                    do {
                        String item_name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Item.item_name));
                        results.add("Item Name: " + item_name);
                    }while (cursor.moveToNext());
                }
            }

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, results);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);

        } catch (SQLiteException se ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } finally {
            if (sqLiteDatabase != null)
                sqLiteDatabase.execSQL("DELETE FROM " + tableName);
            sqLiteDatabase.close();
        }

    }

}
