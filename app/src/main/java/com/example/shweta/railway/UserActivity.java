package com.example.shweta.railway;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

import static com.example.shweta.railway.LoginActivity.last_username;
import static com.example.shweta.railway.LoginDataBaseAdapter.db;


public class UserActivity extends AppCompatActivity {

    Button book_ticket,train_routes,ticket_cancellation,food_order,records,morder;
    Button feedback,sign_out;
    TextView t;
    Intent i;
    SessionManagement session;
    HashMap<String, String> user;

    static int I=0;

    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);

        session = new SessionManagement(getApplicationContext());
        loginDataBaseAdapter= new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        session.checkLogin();
        user = session.getUserDetails();

        // name
        String name = user.get(SessionManagement.KEY_NAME);

        // email
        String email = user.get(SessionManagement.KEY_EMAIL);


        String count = "SELECT count(*) FROM TRAINS";

        Cursor mcursor = db.rawQuery(count, null);

            mcursor.moveToFirst();
            int icount = mcursor.getInt(0);
            if (icount < 1) {
                loginDataBaseAdapter.insertEntry_train("12723", "Gujarat Express", "29-08-2017", 60, 300, "RAJKOT SURENDRANAGAR AHMEDABAD NADIAD ANAND VADODARA BHARUCH SURAT");
                loginDataBaseAdapter.insertEntry_train("29371", "Deccan Queen", "30-08-2017", 60, 500, "JAMNAGAR RAJKOT JUNAGADH");
                loginDataBaseAdapter.insertEntry_train("87347", "Chatrapati Shivaji Express", "31-08-2017", 60, 1000, "SURAT NAVSARI VALSAD");
                loginDataBaseAdapter.insertEntry_train("43823", "Indian Express", "31-08-2017", 60, 450, "PATAN MEHSANA GANDHINAGAR AHMEDABAD NADIAD ANAND VADODARA");
                loginDataBaseAdapter.insertEntry_train("99203", "RBD Express", "12-09-2017", 60, 680, "JAMNAGAR RAJKOT JUNAGADH VERAVAL");
                mcursor.close();
            }

        book_ticket = (Button)findViewById(R.id.book_ticket);
        train_routes = (Button)findViewById(R.id.train_routes);
        ticket_cancellation = (Button)findViewById(R.id.ticket_cancellation);
        food_order = (Button)findViewById(R.id.food_order);
        feedback = (Button)findViewById(R.id.feedback);
        sign_out = (Button)findViewById(R.id.sign_out);
        records = (Button)findViewById(R.id.records);
        morder = (Button)findViewById(R.id.morder);
        t = (TextView)findViewById(R.id.textView2);




        book_ticket.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {

                                               i = new Intent(getApplicationContext(), BookTickets.class);
                                               startActivity(i);
                                           }
                                       });
        feedback.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                              Intent i1 = new Intent(getApplicationContext(), Feedback.class);
                                                startActivity(i1);
                                            }
                                        });
        sign_out.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View view) {

                                                  session.logoutUser();
                                                  Intent i2 = new Intent(getApplicationContext(), LoginActivity.class);
                                                  startActivity(i2);
                                                  finish();
                                              }
                                          });

        train_routes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i3 = new Intent(getApplicationContext(),Train_Routes.class);
                startActivity(i3);
            }
        });

        ticket_cancellation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i3 = new Intent(getApplicationContext(),Ticket_Cancellation.class);
                startActivity(i3);
            }
        });

        food_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i3 = new Intent(getApplicationContext(),FoodMenu.class);
                startActivity(i3);
            }
        });

        records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(getApplicationContext(),BookingRecords.class);
                startActivity(i3);
            }
        });
        morder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MyOrder.class);
                startActivity(i);
            }
        });
        if(loginDataBaseAdapter.getLast_name(last_username) == null)
            t.setText("Hey "+ name + " !" );
        else
        t.setText("Hey "+ loginDataBaseAdapter.getLast_name(last_username) + " !" );
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

          loginDataBaseAdapter.close();
    }

}

