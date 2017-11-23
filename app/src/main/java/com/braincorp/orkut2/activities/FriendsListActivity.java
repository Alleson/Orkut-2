package com.braincorp.orkut2.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.braincorp.orkut2.R;
import com.braincorp.orkut2.model.User;

public class FriendsListActivity extends AppCompatActivity {

    public static final String EXTRA_SHOW_FRIENDS = "show_friends";
    public static final String EXTRA_USER = "user";

    public static Intent getIntent(Context context, boolean showFriends, User user) {
        Intent intent = new Intent(context, FriendsListActivity.class);
        intent.putExtra(EXTRA_SHOW_FRIENDS, showFriends);
        intent.putExtra(EXTRA_USER, user);
        return intent;
    }

    private boolean showFriends;

    private RecyclerView recyclerView;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setActionBar();
        bindViews();
        parseIntent();
    }

    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void bindViews() {
        recyclerView = findViewById(R.id.recyclerViewFriendsList);
    }

    private void parseIntent() {
        showFriends = getIntent().getBooleanExtra(EXTRA_SHOW_FRIENDS, false);
        user = getIntent().getParcelableExtra(EXTRA_USER);
    }

}
