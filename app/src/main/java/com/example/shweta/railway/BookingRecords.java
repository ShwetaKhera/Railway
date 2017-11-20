package com.example.shweta.railway;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static com.example.shweta.railway.BookTickets.tickets;
import static com.example.shweta.railway.BookTickets.total;
import static com.example.shweta.railway.BookTickets.trn_no;
import static com.example.shweta.railway.LoginDataBaseAdapter.db;

/**
 * Created by shweta on 09-05-2017.
 */

public class BookingRecords extends AppCompatActivity {

    LoginDataBaseAdapter loginDataBaseAdapter;

    String Records="";

    TextView tv_records;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_records);

        loginDataBaseAdapter= new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        tv_records = (TextView)findViewById(R.id.textView_records);

        Cursor cursor = db.query("RECORDS", null, "TRAIN_NO", null, null, null, null);
        cursor.moveToLast();
        if(cursor.getCount()>0) {
            try {
                do {
                    String ID = cursor.getString(cursor.getColumnIndex("TRAIN_NO"));
                    String NAME = cursor.getString(cursor.getColumnIndex("TRAIN_NAME"));
                    String DATE = cursor.getString(cursor.getColumnIndex("DATE"));
                    String NOT = cursor.getString(cursor.getColumnIndex("NO_OF_TICKETS"));
                    String AMOUNT = cursor.getString(cursor.getColumnIndex("AMOUNT_PAID"));
                    Records = Records + ID + "          " + NAME + "\n                              " + DATE + "          " + NOT + "          Rs." + AMOUNT + "\n\n";
                } while (cursor.moveToPrevious());
            } finally {
                cursor.close();
            }
        }
        tv_records.setText(Records);
        Records = Records.trim();

    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }
}
