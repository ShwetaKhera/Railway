package com.example.shweta.railway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.shweta.railway.Cart.LIST;
import static com.example.shweta.railway.Cart.LIST1;
import static com.example.shweta.railway.FoodMenu.total;
import static com.example.shweta.railway.LoginDataBaseAdapter.db;

/**
 * Created by shweta on 12-05-2017.
 */

public class MyOrder extends AppCompatActivity {

    TextView total_new,order;
    Button cancel,del,back_to_main_page;

    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myorder);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        total_new = (TextView)findViewById(R.id.total);
        order = (TextView)findViewById(R.id.order);

        cancel = (Button)findViewById(R.id.cancel);
        del = (Button)findViewById(R.id.del);
        back_to_main_page = (Button)findViewById(R.id.back_to_main_page);

        total_new.setText(total+"");
        order.setText(LIST1);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.execSQL("delete from FOOD");
                LIST = "";
                LIST1 = "";
                total = 0;
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.execSQL("delete from FOOD");
                LIST = "";
                LIST1 = "";
                total = 0;
            }
        });

        back_to_main_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),UserActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
