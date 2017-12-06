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

import static com.araceliteixeira.b2caccess.R.menu.menu_form;

public class NewUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(menu_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_form_ok:
                User user = new User();
                user.setEmail(((EditText) findViewById(R.id.new_user_email)).getText().toString());
                user.setPassword(((EditText) findViewById(R.id.new_user_password)).getText().toString());

                UserDAO dao = new UserDAO(this);

                Toast.makeText(NewUser.this, "User " + user.getEmail()  + " saved", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
