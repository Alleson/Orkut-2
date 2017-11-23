package com.braincorp.orkut2.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.braincorp.orkut2.R;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final boolean attachToRoot = false;
        View view = inflater.inflate(R.layout.fragment_options, container, attachToRoot);
        bindViews(view);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAddFriends:
                // TODO: add friends
                break;
            case R.id.buttonMyFriends:
                // TODO: my friends
                break;
        }
    }

    private void bindViews(View view) {
        ButtonWithIcon buttonAddFriends = view.findViewById(R.id.buttonAddFriends);
        ButtonWithIcon buttonMyFriends = view.findViewById(R.id.buttonMyFriends);

        buttonAddFriends.setOnClickListener(this);
        buttonMyFriends.setOnClickListener(this);
    }

}
