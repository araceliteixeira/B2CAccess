package com.araceliteixeira.b2caccess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button userLogin = (Button) findViewById(R.id.main_user_login);
        userLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intentGotoView = new Intent(MainActivity.this, UserView.class);
                startActivity(intentGotoView);
            }
        });

        Button newUser = (Button) findViewById(R.id.main_new_user);
        userLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intentGotoView = new Intent(MainActivity.this, NewUser.class);
                startActivity(intentGotoView);
            }
        });

        Button guestLogin = (Button) findViewById(R.id.main_guest_login);
        userLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intentGotoView = new Intent(MainActivity.this, GuestView.class);
                startActivity(intentGotoView);
            }
        });
    }
}
