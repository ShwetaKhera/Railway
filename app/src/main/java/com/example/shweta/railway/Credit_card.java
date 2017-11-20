package com.example.shweta.railway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.shweta.railway.BookTickets.tickets;
import static com.example.shweta.railway.BookTickets.total;
import static com.example.shweta.railway.BookTickets.trn_no;
import static com.example.shweta.railway.UserActivity.I;

/**
 * Created by shweta on 12-05-2017.
 */

public class Credit_card extends AppCompatActivity{
    EditText name,card_no, date_of_exp,cvv;
    Button submit_credit;

    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credit_card);

        loginDataBaseAdapter= new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        submit_credit = (Button) findViewById(R.id.submit_debit);
        name = (EditText) findViewById(R.id.name);
        card_no = (EditText) findViewById(R.id.card_no);
        date_of_exp = (EditText) findViewById(R.id.date_of_exp);
        cvv = (EditText) findViewById(R.id.cvv);


        submit_credit.setOnClickListener(new View.OnClickListener() {
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
