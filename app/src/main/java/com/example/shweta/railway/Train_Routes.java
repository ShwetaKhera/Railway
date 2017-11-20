package com.example.shweta.railway;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.shweta.railway.LoginDataBaseAdapter.db;

public class Train_Routes extends AppCompatActivity {
    EditText from,to;
    Button submit;
    TextView STATION_TEXT;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.from_to);
        submit = (Button)findViewById(R.id.submit);
        from = (EditText)findViewById(R.id.from);
        to = (EditText)findViewById(R.id.to);
        STATION_TEXT = (TextView)findViewById(R.id.STATION_TEXT);

        loginDataBaseAdapter= new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String FROM = from.getText().toString();
                String TO = to.getText().toString();
                String N = "";

                Cursor cursor = db.query("TRAINS", null, "STATIONS LIKE '%"+FROM+"%"+TO+"%'", null, null, null, null);
                cursor.moveToFirst();
                if(cursor.getCount() > 0) {
                    try {
                        do {
                            String ID = cursor.getString(cursor.getColumnIndex("TRAIN_NO"));
                            String NAME = cursor.getString(cursor.getColumnIndex("TRAIN_NAME"));
                            String FARE = cursor.getString(cursor.getColumnIndex("TICKET_FARE"));
                            N = N + ID + "          " + NAME + "          " + FARE + "\n\n";
                        } while (cursor.moveToNext());
                    } finally {
                        cursor.close();
                    }
                }
                STATION_TEXT.setText(N);
                N = N.trim();
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
