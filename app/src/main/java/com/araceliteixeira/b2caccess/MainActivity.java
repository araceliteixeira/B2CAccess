package com.araceliteixeira.b2caccess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.araceliteixeira.b2caccess.DAO.UserDAO;
import com.araceliteixeira.b2caccess.model.User;

import java.util.List;

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
                String email = ((EditText) findViewById(R.id.main_email)).getText().toString();
                String password = ((EditText) findViewById(R.id.main_password)).getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User();
                    user.setEmail(email);
                    user.setPassword(password);

                    UserDAO dao = new UserDAO(MainActivity.this);
                    List<User> users = dao.dbSearch();
                    dao.close();

                    boolean doLogin = false;
                    for (User u: users) {
                        if (user.getEmail().equals(u.getEmail()) && user.getPassword().equals(u.getPassword())) {
                            doLogin = true;
                        }
                    }
                    if (doLogin) {
                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        Intent intentGotoView = new Intent(MainActivity.this, ProductView.class);
                        intentGotoView.putExtra("email", user.getEmail());
                        startActivity(intentGotoView);
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Button newUser = (Button) findViewById(R.id.main_new_user);
        newUser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intentGotoView = new Intent(MainActivity.this, NewUser.class);
                startActivity(intentGotoView);
            }
        });

        Button guestLogin = (Button) findViewById(R.id.main_guest_login);
        guestLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intentGotoView = new Intent(MainActivity.this, ProductView.class);
                startActivity(intentGotoView);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        EditText email = (EditText) findViewById(R.id.main_email);
        email.setText("");
        EditText password = (EditText) findViewById(R.id.main_password);
        password.setText("");
    }
}
