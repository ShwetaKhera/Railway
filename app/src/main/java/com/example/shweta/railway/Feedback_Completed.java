package com.example.shweta.railway;

/**
 * Created by shweta on 20-04-2017.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import static com.example.shweta.railway.Feedback.rating;


public class Feedback_Completed extends AppCompatActivity{

    TextView tv ;
    Button b ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_completed);

        tv = (TextView)findViewById(R.id.textView11);
        b = (Button)findViewById(R.id.return_to_main_page);

        checkratings();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),UserActivity.class);
                startActivity(i);
            }
        });
    }
    void checkratings(){
        if(rating == 5)
        {
            tv.setText("Thanks alot.\nBut we will still keep on improving ourselves and give you the best services.");
        }
        if(rating > 2 && rating<5){
            tv.setText("Thanks alot for giving us good ratings.\nWe will keep on improving till you give us 5 stars !");
        }
        else {
            tv.setText("We regret for our poor services to you.\nWe will improve ourselves and will not give another chance of complain");
        }
    }
}
