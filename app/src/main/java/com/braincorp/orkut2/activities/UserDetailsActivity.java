package com.braincorp.orkut2.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.braincorp.orkut2.R;
import com.braincorp.orkut2.model.User;
import com.braincorp.orkut2.utils.DateUtils;

import java.util.Locale;

public class UserDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String EXTRA_LOGGED_USER = "logged_user";
    private static final String EXTRA_TARGET_USER = "target_user";

    public static Intent getIntent(Context context, User loggedUser, User targetUser) {
        Intent intent = new Intent(context, UserDetailsActivity.class);
        intent.putExtra(EXTRA_LOGGED_USER, loggedUser);
        intent.putExtra(EXTRA_TARGET_USER, targetUser);
        return intent;
    }

    private TextView textViewName;
    private TextView textViewDateOfBirth;
    private TextView textViewAge;

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
        bindViews();
        setTexts();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAdd:

                break;
            case R.id.buttonDelete:

                break;
        }
    }

    private void bindViews() {
        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonDelete = findViewById(R.id.buttonDelete);
        buttonAdd.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        textViewName = findViewById(R.id.textViewName);
        textViewDateOfBirth = findViewById(R.id.textViewDateOfBirth);
        textViewAge = findViewById(R.id.textViewAge);
    }

    private void parseIntent() {
        loggedUser = getIntent().getParcelableExtra(EXTRA_LOGGED_USER);
        targetUser = getIntent().getParcelableExtra(EXTRA_TARGET_USER);
    }

    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setTexts() {
        textViewName.setText(targetUser.getFullName());

        String dateOfBirthText = String.format(getString(R.string.date_of_birth_format),
                DateUtils.getFormattedDate(targetUser.getDateOfBirth()));
        textViewDateOfBirth.setText(dateOfBirthText);

        String ageText = String.format(Locale.getDefault(), getString(R.string.age),
                DateUtils.getAge(targetUser.getDateOfBirth()));
        textViewAge.setText(ageText);
    }

}
