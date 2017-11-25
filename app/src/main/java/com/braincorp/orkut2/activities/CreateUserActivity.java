package com.braincorp.orkut2.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.braincorp.orkut2.R;
import com.braincorp.orkut2.database.Database;
import com.braincorp.orkut2.model.User;

import java.util.Calendar;
import java.util.Date;

public class CreateUserActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextUserName;
    private EditText editTextPassword;
    private EditText editTextFullName;
    private EditText editTextDateOfBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setActionBar();
        bindViews();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fabSave) {
            if (save())
                finish();
            else
                Snackbar.make(view, R.string.error_creating_user, Snackbar.LENGTH_LONG).show();
            clearEditTexts();
        }
    }

    private void bindViews() {
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextFullName = findViewById(R.id.editTextFullName);
        editTextDateOfBirth = findViewById(R.id.editTextDateOfBirth);

        FloatingActionButton fabSave = findViewById(R.id.fabSave);
        fabSave.setOnClickListener(this);
    }

    private void clearEditTexts() {
        if (editTextUserName.getText() != null)
            editTextUserName.getText().clear();
        if (editTextPassword.getText() != null)
            editTextPassword.getText().clear();
        if (editTextFullName.getText() != null)
            editTextFullName.getText().clear();
        if (editTextDateOfBirth.getText() != null)
            editTextDateOfBirth.getText().clear();
    }

    private Date convertStringToDate(String str) {
        if (!str.contains("/"))
            throw new IllegalArgumentException();

        String[] rawValues = str.split("/");
        int day = Integer.valueOf(rawValues[0]);
        int month = Integer.valueOf(rawValues[1]);
        int year = Integer.valueOf(rawValues[2]);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);

        return calendar.getTime();
    }

    private boolean save() {
        String userName = editTextUserName.getText().toString();
        String password = editTextPassword.getText().toString();
        String fullName = editTextFullName.getText().toString();
        String dateOfBirthStr = editTextDateOfBirth.getText().toString();

        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password) || TextUtils.isEmpty(fullName))
            return false;

        User.Builder userBuilder = new User.Builder().setUserName(userName)
                .setPassword(password)
                .setFullName(fullName);

        if (!dateOfBirthStr.equals("")) {
            Date dateOfBirth = convertStringToDate(dateOfBirthStr);
            userBuilder.setDateOfBirth(dateOfBirth);
        }

        User user = userBuilder.build();
        Database.getInstance().insert(user);
        return true;
    }

    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }


}
