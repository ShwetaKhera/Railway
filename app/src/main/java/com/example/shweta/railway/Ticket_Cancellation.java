package com.example.shweta.railway;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.shweta.railway.LoginDataBaseAdapter.db;

/**
 * Created by shweta on 13-05-2017.
 */

public class Ticket_Cancellation extends AppCompatActivity {

    LoginDataBaseAdapter loginDataBaseAdapter;

    EditText trn_no;
    Button cancel_btn;

    String train_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_cancel);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        trn_no = (EditText)findViewById(R.id.train_number);
        cancel_btn = (Button)findViewById(R.id.cancel_btn);

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                train_no = trn_no.getText().toString();

                Cursor cursor = db.query("RECORDS", null, "TRAIN_NO=?", new String[] { train_no }, null, null, null);
                if(cursor.getCount() < 1){
                    cursor.close();
                    Toast.makeText(getApplicationContext(),"No such record exists , Please try again !",Toast.LENGTH_SHORT);
                }
                cursor.moveToFirst();

                try {
                    do {
                        db.delete("RECORDS","TRAIN_NO="+train_no,null);
                    } while (cursor.moveToNext());
                } finally {
                    Toast.makeText(getApplicationContext(),"Your Booking has been cancelled !",Toast.LENGTH_SHORT);
                    cursor.close();
                }

            }
        });
    }
}