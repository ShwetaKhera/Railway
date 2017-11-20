package com.example.shweta.railway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.shweta.railway.BookTickets.total;
import static com.example.shweta.railway.UserActivity.I;

/**
 * Created by shweta on 12-05-2017.
 */

public class Payment_Food extends AppCompatActivity{

    LoginDataBaseAdapter loginDataBaseAdapter;
    Button credit,debit,cancel,cod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_food);

        I = 2;
        loginDataBaseAdapter= new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        credit = (Button)findViewById(R.id.credit);
        debit = (Button)findViewById(R.id.debit);
        cod = (Button)findViewById(R.id.cod);
        cancel = (Button)findViewById(R.id.cancel);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Cart.class);
                startActivity(i);
                finish();
            }
        });
        debit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Debit_Card.class);
                i.putExtra("caller", "Payment_Food");
                startActivity(i);
            }
        });
        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Credit_card.class);
                i.putExtra("caller", "Payment_Food");
                startActivity(i);
            }
        });
        cod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),COD.class);
                startActivity(i);
            }
        });
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

        loginDataBaseAdapter.close();
    }
}

