package com.example.shweta.railway;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    LoginDataBaseAdapter loginDataBaseAdapter;
    private EditText loginInputEmail, loginInputPassword;
    private Button btnlogin, registerScreen;
    public static String last_username, last_password;
    String userName,password,storedPassword;
    Context context = this;
    CheckBox checkBox;

    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        loginInputEmail = (EditText) findViewById(R.id.login_input_email);
        loginInputPassword = (EditText) findViewById(R.id.login_input_password);


        btnlogin = (Button) findViewById(R.id.btn_login);
        registerScreen = (Button) findViewById(R.id.registerScreen);
        session = new SessionManagement(getApplicationContext());

        registerScreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(context, RegisterActivity.class);
                startActivity(i);
            }
        });

    }


    public void onClick(View view) {

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 userName = loginInputEmail.getText().toString();
                 password = loginInputPassword.getText().toString();

                 storedPassword = loginDataBaseAdapter.getSinlgeEntry(userName);
                if (password.equals(storedPassword)) {
                    last_username = userName;
                    last_password = password;
                    session.createLoginSession(loginDataBaseAdapter.getLast_name(userName), userName);

                    Toast.makeText(LoginActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    Intent main = new Intent(LoginActivity.this, UserActivity.class);
                    startActivity(main);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this,
                            "User Name or Password does not match",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginDataBaseAdapter.close();
    }
}

