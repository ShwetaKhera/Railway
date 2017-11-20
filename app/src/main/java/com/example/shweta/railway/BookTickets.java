package com.example.shweta.railway;


import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.sql.Types.NULL;


public class BookTickets extends AppCompatActivity {

    LoginDataBaseAdapter loginDataBaseAdapter;
    EditText train_no,no_of_tickets;
    public static int total;
    public static String trn_no;
    public static float tickets,fare;

    Button proceed;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_tickets);

        loginDataBaseAdapter= new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        train_no = (EditText)findViewById(R.id.train_no);
        no_of_tickets = (EditText)findViewById(R.id.no_of_tickets);
        proceed = (Button)findViewById(R.id.proceed);
        t = (TextView)findViewById(R.id.textView10);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                trn_no = train_no.getText().toString();
                tickets = Integer.parseInt(no_of_tickets.getText().toString());
                fare = loginDataBaseAdapter.getFare(trn_no);

                int stored_value = loginDataBaseAdapter.Tickets_left(trn_no);
                if(tickets>stored_value || stored_value==0 || fare==NULL) {
                    t.setText(loginDataBaseAdapter.Train_name(trn_no)+" "+loginDataBaseAdapter.getFare(trn_no)+" "+"We are truely sorry for the inconvienience , please try another train");
                    train_no.setText("");
                    no_of_tickets.setText("");
                }
                else
                {
                    loginDataBaseAdapter.updateEntry_Train(trn_no,loginDataBaseAdapter.Train_name(trn_no),loginDataBaseAdapter.Date(trn_no)
                            ,stored_value-(int)tickets,(int)fare,loginDataBaseAdapter.Stations(trn_no));
                    total =(int) (fare*tickets);
                    Intent i = new Intent(getApplicationContext(),Payment.class);
                    startActivity(i);
                }

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