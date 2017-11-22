package com.braincorp.orkut2.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.braincorp.orkut2.R;
import com.braincorp.orkut2.model.User;

public class HomePageActivity extends AppCompatActivity {

    private static final String EXTRA_USER = "user";

    public static Intent getIntent(Context context, User user) {
        Intent intent = new Intent(context, HomePageActivity.class);
        intent.putExtra(EXTRA_USER, user);
        return intent;
    }

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        parseIntent();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(EXTRA_USER, user);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        user = savedInstanceState.getParcelable(EXTRA_USER);
    }

    private void parseIntent() {
        user = getIntent().getParcelableExtra(EXTRA_USER);
    }

}
