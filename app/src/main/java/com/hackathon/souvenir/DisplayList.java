package com.hackathon.souvenir;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DisplayList extends Activity {


    // Array of strings...
    ListView simpleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);

        ArrayList<String> myList = (ArrayList<String>) getIntent().getStringArrayListExtra("mylist");

        simpleList = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.content_display_list, R.id.textView, myList);
        simpleList.setAdapter(arrayAdapter);
    }

}
