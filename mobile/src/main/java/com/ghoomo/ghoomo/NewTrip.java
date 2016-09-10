package com.ghoomo.ghoomo;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.CursorAnchorInfo;

import contact.ContactAccessor;
import contact.ContactAccessorImpl;

public class NewTrip extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView contactRecyclerView = (RecyclerView)findViewById(R.id.new_trip_rv);
        ContactAccessorImpl contactAccessor = new ContactAccessorImpl(this);
        Cursor cursor = contactAccessor.getAllContacts();
        Log.w("Contacts size==",cursor.getCount()+"");

    }

}
