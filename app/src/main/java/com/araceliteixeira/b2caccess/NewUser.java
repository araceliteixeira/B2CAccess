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

public class NewUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        Button cancelButton = (Button) findViewById(R.id.new_user_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGotoView = new Intent(NewUser.this, MainActivity.class);
                startActivity(intentGotoView);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSave_save:
                String email = ((EditText) findViewById(R.id.new_user_email)).getText().toString();
                String password = ((EditText) findViewById(R.id.new_user_password)).getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(NewUser.this, "Invalid email or password.", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    User user = new User();
                    user.setEmail(email);
                    user.setPassword(password);

                    UserDAO dao = new UserDAO(this);
                    dao.dbInsert(user);
                    dao.close();
                    Toast.makeText(NewUser.this, "User " + user.getEmail()  + " saved", Toast.LENGTH_SHORT).show();
                    finish();

                    Intent intentGotoView = new Intent(NewUser.this, MainActivity.class);
                    startActivity(intentGotoView);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
