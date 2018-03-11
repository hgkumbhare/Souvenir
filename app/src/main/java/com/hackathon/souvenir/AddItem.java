package com.hackathon.souvenir;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                EditText edit_one_unit = (EditText)findViewById(R.id.one_unit);
                EditText edit_cost_of_one_unit = (EditText)findViewById(R.id.cost_of_one_unit);
                EditText edit_measurement_unit = (EditText)findViewById(R.id.measurement_unit);
                EditText edit_usage = (EditText)findViewById(R.id.usage);
                EditText edit_usage_time_period = (EditText)findViewById(R.id.usage_time_period);
                EditText edit_available = (EditText)findViewById(R.id.available);
                EditText edit_reminder_before_days = (EditText)findViewById(R.id.reminder_before_days);

                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseHelper.Item.email_address, MainActivity.email_address);
                contentValues.put(DatabaseHelper.Item.item_name, edit_item_name.toString());
                contentValues.put(DatabaseHelper.Item.one_unit, edit_one_unit.toString());
                contentValues.put(DatabaseHelper.Item.cost_of_one_unit, edit_cost_of_one_unit.toString());
                contentValues.put(DatabaseHelper.Item.measurement_unit, edit_measurement_unit.toString());

                contentValues.put(DatabaseHelper.Item.usage, edit_usage.toString());
                contentValues.put(DatabaseHelper.Item.usage_time_period, edit_usage_time_period.toString());
                contentValues.put(DatabaseHelper.Item.available, edit_available.toString());
                contentValues.put(DatabaseHelper.Item.reminder_before_days, edit_reminder_before_days.toString());

                MainActivity.db.insert(contentValues);


                Intent startIntend = new Intent(getApplicationContext(), Features.class);
                startActivity(startIntend);
            }
        });
    }

    /*public void testNotification(View view) {
        NotificationUtils.remindUserToRefill(this);
    }*/
}
