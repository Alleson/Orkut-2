package com.braincorp.orkut2.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.braincorp.orkut2.R;
import com.braincorp.orkut2.model.User;

public class HomePageActivity extends AppCompatActivity implements DialogInterface.OnClickListener {

    private static final String EXTRA_USER = "user";

    public static Intent getIntent(Context context, User user) {
        Intent intent = new Intent(context, HomePageActivity.class);
        intent.putExtra(EXTRA_USER, user);
        return intent;
    }

    private TextView textViewWelcome;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        parseIntent();
        bindViews();
        setTexts();
    }

    @Override
    public void onBackPressed() {
        showSignOutDialogue();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_sign_out) {
            showSignOutDialogue();
            return true;
        }
        return super.onOptionsItemSelected(item);
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

    @Override
    public void onClick(DialogInterface dialogInterface, int which) {
        if (which == DialogInterface.BUTTON_POSITIVE)
            finish();
    }

    private void bindViews() {
        textViewWelcome = findViewById(R.id.textViewWelcome);
    }

    private void parseIntent() {
        user = getIntent().getParcelableExtra(EXTRA_USER);
    }

    private void setTexts() {
        String welcomeText = String.format(getString(R.string.welcome), user.getFullName());
        textViewWelcome.setText(welcomeText);
    }

    private void showSignOutDialogue() {
        new AlertDialog.Builder(this).setTitle(R.string.sign_out)
                .setIcon(R.drawable.ic_sign_out_dark)
                .setMessage(R.string.message_sign_out)
                .setNegativeButton(R.string.no, null)
                .setPositiveButton(R.string.yes, this)
                .show();
    }

}
