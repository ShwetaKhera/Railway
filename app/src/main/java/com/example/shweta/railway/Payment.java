package com.example.shweta.railway;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.shweta.railway.BookTickets.fare;
import static com.example.shweta.railway.BookTickets.total;
import static com.example.shweta.railway.UserActivity.I;

public class Payment extends AppCompatActivity{

    LoginDataBaseAdapter loginDataBaseAdapter;
    TextView t ;
    Button credit,debit,cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        I = 1;
        loginDataBaseAdapter= new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        t = (TextView)findViewById(R.id.textView9);
        credit = (Button)findViewById(R.id.credit);
        debit = (Button)findViewById(R.id.debit);
        cancel = (Button)findViewById(R.id.cancel);


        t.setText("Your payable amount : Rs " + total);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),BookTickets.class);
                startActivity(i);
                finish();
            }
        });
        debit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Debit_Card.class);
                i.putExtra("caller", "Payment");
                startActivity(i);
            }
        });
        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Credit_card.class);
                i.putExtra("caller", "Payment");
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
