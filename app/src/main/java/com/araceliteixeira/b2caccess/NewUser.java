package com.araceliteixeira.b2caccess;

import com.araceliteixeira.b2caccess.DAO.UserDAO;
import com.araceliteixeira.b2caccess.model.User;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class NewUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        Button saveButton = (Button) findViewById(R.id.new_user_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ((EditText) findViewById(R.id.new_user_email)).getText().toString();
                String password = ((EditText) findViewById(R.id.new_user_password)).getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(NewUser.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User();
                    user.setEmail(email);
                    user.setPassword(password);

                    UserDAO dao = new UserDAO(NewUser.this);
                    List<User> users = dao.dbSearch();

                    boolean userExists = false;
                    for (User u: users) {
                        if (user.getEmail().equals(u.getEmail())) {
                            userExists = true;
                        }
                    }

                    if (userExists) {
                        Toast.makeText(NewUser.this, "User " + user.getEmail() + " already exists",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        dao.dbInsert(user);
                        dao.close();
                        Toast.makeText(NewUser.this, "User " + user.getEmail() + " saved",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });

        Button cancelButton = (Button) findViewById(R.id.new_user_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
