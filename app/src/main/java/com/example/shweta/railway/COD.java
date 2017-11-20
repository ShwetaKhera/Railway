package com.example.shweta.railway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by shweta on 12-05-2017.
 */

public class COD extends AppCompatActivity {

    EditText sn;
    Button submit;

    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cod);

        loginDataBaseAdapter= new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        submit = (Button) findViewById(R.id.done);
        sn = (EditText) findViewById(R.id.seatno);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MyOrder.class);
                startActivity(i);
            }
        });
    }

}
