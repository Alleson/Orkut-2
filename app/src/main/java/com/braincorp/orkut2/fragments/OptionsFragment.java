package com.braincorp.orkut2.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.braincorp.orkut2.R;
import com.braincorp.orkut2.activities.FriendsListActivity;
import com.braincorp.orkut2.model.User;
import com.braincorp.orkut2.view.ButtonWithIcon;

public class OptionsFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_USER = "user";

    public static OptionsFragment newInstance(User user) {
        OptionsFragment fragment = new OptionsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final boolean attachToRoot = false;
        View view = inflater.inflate(R.layout.fragment_options, container, attachToRoot);
        parseArgs();
        bindViews(view);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAddFriends:
                startFriendsListActivity(false);
                break;
            case R.id.buttonMyFriends:
                startFriendsListActivity(true);
                break;
        }
    }

    private void bindViews(View view) {
        ButtonWithIcon buttonAddFriends = view.findViewById(R.id.buttonAddFriends);
        ButtonWithIcon buttonMyFriends = view.findViewById(R.id.buttonMyFriends);

        buttonAddFriends.setOnClickListener(this);
        buttonMyFriends.setOnClickListener(this);
    }

    private void parseArgs() {
        Bundle args = getArguments();
        if (args == null)
            return;
        user = args.getParcelable(ARG_USER);
    }

    private void startFriendsListActivity(boolean showFriends) {
        Intent intent = FriendsListActivity.getIntent(getContext(), showFriends, user);
        startActivity(intent);
    }

}
