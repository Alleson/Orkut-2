package com.braincorp.orkut2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.braincorp.orkut2.R;
import com.braincorp.orkut2.database.UserDao;
import com.braincorp.orkut2.model.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextUserName;
    private EditText editTextPassword;

    private UserDao database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        database = UserDao.getInstance(this);
        bindViews();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btSignIn:
                signIn();
                clearEditTexts();
                break;
            case R.id.btCreateAccount:
                createAccount();
                clearEditTexts();
                break;
        }
    }

    private void bindViews() {
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);

        Button btSignIn = findViewById(R.id.btSignIn);
        Button btCreateAccount = findViewById(R.id.btCreateAccount);

        btSignIn.setOnClickListener(this);
        btCreateAccount.setOnClickListener(this);
    }

    private void clearEditTexts() {
        if (editTextUserName.getText() != null)
            editTextUserName.getText().clear();
        if (editTextPassword.getText() != null)
            editTextPassword.getText().clear();
    }

    private void signIn() {
        String userName = editTextUserName.getText().toString();
        String password = editTextPassword.getText().toString();
        User user = database.select(userName, password);
        boolean validCredentials = user != null;
        if (validCredentials) {
            // TODO: authenticate
        } else {
            View focus = getCurrentFocus();
            if (focus != null)
                Snackbar.make(focus, R.string.invalid_user_name_or_password, Snackbar.LENGTH_LONG)
                        .show();
        }
    }

    private void createAccount() {
        Intent intent = new Intent(LoginActivity.this, CreateUserActivity.class);
        startActivity(intent);
    }

}
