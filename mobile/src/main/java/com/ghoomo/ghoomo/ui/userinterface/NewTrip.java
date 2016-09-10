package com.ghoomo.ghoomo.ui.userinterface;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ghoomo.ghoomo.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

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
    int PLACE_PICKER_SOURCE_REQUEST = 1;
    int PLACE_PICKER_DESTINATION_REQUEST = 2;

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
    private TextView mSource;
    private TextView mDestination;

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

        mSource = (TextView)findViewById(R.id.new_trip_source);
        mSource.setOnClickListener(this);
        mDestination = (TextView)findViewById(R.id.new_trip_destination);
        mDestination.setOnClickListener(this);
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
                break;
            case R.id.new_trip_source:
                launchPlacePicker(PLACE_PICKER_SOURCE_REQUEST);
            break;
            case R.id.new_trip_destination:
                launchPlacePicker(PLACE_PICKER_DESTINATION_REQUEST);
                break;

            default:
                    break;
        }
    }

    private void launchPlacePicker(int requestId) {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(NewTrip.this), requestId);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            if (resultCode == RESULT_OK) {
                if (requestCode == PLACE_PICKER_SOURCE_REQUEST) {
                Place place = PlacePicker.getPlace(data, this);
                String toastMsg = (String) place.getName();
                mSource.setText(toastMsg);
            }else if(requestCode == PLACE_PICKER_DESTINATION_REQUEST){
                    Place place = PlacePicker.getPlace(data, this);
                    String toastMsg = (String) place.getName();
                    mDestination.setText(toastMsg);
                }
        }
    }
}
