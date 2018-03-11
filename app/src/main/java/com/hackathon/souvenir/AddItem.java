package com.hackathon.souvenir;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hackathon.souvenir.utilities.NotificationUtils;

public class AddItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Button addItem = (Button) findViewById(R.id.addItem);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText edit_item_name = (EditText)findViewById(R.id.item_name);
                EditText edit_measurement_unit = (EditText)findViewById(R.id.measurement_unit);
                EditText edit_usage = (EditText)findViewById(R.id.usage);

                EditText edit_available_quantity = (EditText)findViewById(R.id.available_quantity);
                EditText edit_use_default_consumption = (EditText)findViewById(R.id.use_default_consumption);
                EditText edit_reminder_before_days = (EditText)findViewById(R.id.reminder_before_days);

                ContentValues contentValues = new ContentValues();

                Log.d("SEE HERE item_name :",edit_item_name.getText().toString());
                Log.d("SEE HERE measurement_unit :",edit_measurement_unit.getText().toString());
                Log.d("SEE HERE usage :",edit_usage.getText().toString());
                Log.d("SEE HERE available_quantity :",edit_available_quantity.getText().toString());
                Log.d("SEE HERE use_default_consumption :",edit_use_default_consumption.getText().toString());
                Log.d("SEE HERE reminder_before_days :",edit_reminder_before_days.getText().toString());

                contentValues.put(DatabaseHelper.Item.item_name, edit_item_name.getText().toString());
                contentValues.put(DatabaseHelper.Item.email_address, MainActivity.email_address);
                contentValues.put(DatabaseHelper.Item.measurement_unit, edit_measurement_unit.getText().toString());

                contentValues.put(DatabaseHelper.Item.usage, edit_usage.getText().toString());
                contentValues.put(DatabaseHelper.Item.available_quantity, edit_available_quantity.getText().toString());
                contentValues.put(DatabaseHelper.Item.use_default_consumption, edit_use_default_consumption.getText().toString());
                contentValues.put(DatabaseHelper.Item.reminder_before_days, edit_reminder_before_days.getText().toString());

                boolean result = MainActivity.db.insert(contentValues);

                Toast.makeText(AddItem.this, result?"ADDED SUCCESSFULLY":"FAILED TO ADD",
                        Toast.LENGTH_LONG).show();

                Intent startIntend = new Intent(getApplicationContext(), Features.class);
                startActivity(startIntend);
            }
        });
    }

    /*public void testNotification(View view) {
        NotificationUtils.remindUserToRefill(this);
    }*/
}
