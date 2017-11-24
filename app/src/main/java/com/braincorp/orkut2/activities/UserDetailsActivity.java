package com.braincorp.orkut2.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.braincorp.orkut2.R;
import com.braincorp.orkut2.model.User;

public class UserDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_LOGGED_USER = "logged_user";
    private static final String EXTRA_TARGET_USER = "target_user";

    public static Intent getIntent(Context context, User loggedUser, User targetUser) {
        Intent intent = new Intent(context, UserDetailsActivity.class);
        intent.putExtra(EXTRA_LOGGED_USER, loggedUser);
        intent.putExtra(EXTRA_TARGET_USER, targetUser);
        return intent;
    }

    private User loggedUser;
    private User targetUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setActionBar();
        parseIntent();
    }

    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void parseIntent() {
        loggedUser = getIntent().getParcelableExtra(EXTRA_LOGGED_USER);
        targetUser = getIntent().getParcelableExtra(EXTRA_TARGET_USER);
    }

}
