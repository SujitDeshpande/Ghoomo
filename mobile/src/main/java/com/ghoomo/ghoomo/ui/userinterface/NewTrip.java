package com.ghoomo.ghoomo.ui.userinterface;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.ghoomo.ghoomo.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import contact.ContactAccessorImpl;

public class NewTrip extends AppCompatActivity implements View.OnClickListener{

    private String TAG = NewTrip.class.getSimpleName();
    private EditText mEditTextFromDate;
    Date selectedFromDate;
    private EditText mEditTextToDate;
    private Calendar mSelectedFromCalendar;
    private  DatePickerDialog.OnDateSetListener fromListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            mSelectedFromCalendar = Calendar.getInstance();
            mSelectedFromCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            mSelectedFromCalendar.set(Calendar.YEAR, year);
            mSelectedFromCalendar.set(Calendar.MONTH, monthOfYear);

            String myFormat = "dd-MM-yyyy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            String selectedFromDate = sdf.format(mSelectedFromCalendar.getTime());
            mEditTextFromDate.setText(selectedFromDate);

        }
    };

    private Calendar mSelectedToCalendar;
    private DatePickerDialog.OnDateSetListener toListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            mSelectedToCalendar = Calendar.getInstance();
            mSelectedToCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            mSelectedToCalendar.set(Calendar.YEAR, year);
            mSelectedToCalendar.set(Calendar.MONTH, monthOfYear);

            String myFormat = "dd-MM-yyyy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            String selectedToDate = sdf.format(mSelectedToCalendar.getTime());
            mEditTextToDate.setText(selectedToDate);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ContactAccessorImpl contactAccessor = new ContactAccessorImpl(this);
        mEditTextFromDate = (EditText)findViewById(R.id.new_trip_from_date);
        mEditTextToDate = (EditText)findViewById(R.id.new_trip_to_date);
        mEditTextFromDate.setOnClickListener(this);
        mEditTextToDate.setOnClickListener(this);
//        Cursor cursor = contactAccessor.getAllContacts();
//        Log.w("TAG==",cursor.getCount()+"");
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()){
//            Log.w(TAG,""+ cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
//            cursor.moveToNext();
//        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.new_trip_from_date:
                showCalendar(System.currentTimeMillis(),fromListener);
                break;
            case R.id.new_trip_to_date:
                showCalendar(System.currentTimeMillis(), toListener);
                default:
                    break;
        }
    }


    /**
     * Shows the Date picker with Month,Year and Day
     */
    private void showCalendar(Long timeInMillis, DatePickerDialog.OnDateSetListener listener) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(timeInMillis);
        //get current Day,Month,Year
        int month = mCalendar.get(Calendar.MONTH);
        int year = mCalendar.get(Calendar.YEAR);
        int day = mCalendar.get(Calendar.DAY_OF_MONTH);

        // Set the calendar for minimum age required
        mCalendar.set(Calendar.YEAR, year);

        //Start Date picker with respective current date
        DatePickerDialog datePickerDialog =
                new DatePickerDialog(this, android.R.style.Theme_DeviceDefault_Dialog, listener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(timeInMillis);
        datePickerDialog.show();
    }

//
//
//        if(mSelectedFromCalendar.getTimeInMillis()<mSelectedToCalendar.getTimeInMillis()){
//
//        }



}
