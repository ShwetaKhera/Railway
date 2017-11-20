package com.example.shweta.railway;

/**
 * Created by shweta on 20-04-2017.
 */

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import static com.example.shweta.railway.FoodMenu.Aadapter;
import static com.example.shweta.railway.FoodMenu.list;
import static com.example.shweta.railway.FoodMenu.total;
import static com.example.shweta.railway.LoginDataBaseAdapter.db;

public class Cart extends AppCompatActivity {

    LoginDataBaseAdapter loginDataBaseAdapter;

    TextView total1,cart_tv;
    static String LIST = "", LIST1 = "";
    Button pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();


        total1 = (TextView)findViewById(R.id.total);
        pay = (Button)findViewById(R.id.pay);
        cart_tv = (TextView)findViewById(R.id.cart_tv);
        total1.setText("Total : Rs."+total);

        Cursor cursor = db.query("FOOD", null, "AMOUNT", null, null, null, null);
        cursor.moveToFirst();

        if(cursor.getCount()>0) {
            try {
                do {

                    String ITEM = cursor.getString(cursor.getColumnIndex("ITEM"));
                    String AMOUNT = cursor.getString(cursor.getColumnIndex("AMOUNT"));

                    LIST = LIST + ITEM + "(" + AMOUNT + ")\n\n";
                    LIST1 = LIST1 + ITEM + "\n\n";
                } while (cursor.moveToNext());
            } finally {
                cursor.close();
            }
        }

        cart_tv.setText(LIST);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Payment_Food.class);
                startActivity(i);
                finish();
            }
        });
    }

}
