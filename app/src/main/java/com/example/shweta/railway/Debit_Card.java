package com.example.shweta.railway;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

import static com.example.shweta.railway.BookTickets.tickets;
import static com.example.shweta.railway.BookTickets.total;
import static com.example.shweta.railway.BookTickets.trn_no;
import static com.example.shweta.railway.UserActivity.I;

/**
 * Created by shweta on 02-04-2017.
 */

public class Debit_Card extends AppCompatActivity {

    EditText card_no, date_of_exp, phn_no;
    Button submit_debit;

    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.debitcard);

        loginDataBaseAdapter= new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        submit_debit = (Button) findViewById(R.id.submit_debit);
        card_no = (EditText) findViewById(R.id.card_no);
        date_of_exp = (EditText) findViewById(R.id.date_of_exp);
        phn_no = (EditText) findViewById(R.id.phone_no);


        submit_debit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (I) {
                    case 1:
                        loginDataBaseAdapter.insertEntry_Records(trn_no, loginDataBaseAdapter.Train_name(trn_no), loginDataBaseAdapter.Date(trn_no), (int) tickets, total);
                        Intent i = new Intent(getApplicationContext(), BookingRecords.class);
                        startActivity(i);
                        finish();
                        break;
                    case 2:
                        i = new Intent(getApplicationContext(), MyOrder.class);
                        startActivity(i);
                        finish();
                        break;
                }
            }
        });
    }

}