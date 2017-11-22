package com.braincorp.orkut2.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class ViewUtils {

    private ViewUtils() { }

    public static void hideKeyboard(View focus) {
        if (focus != null) {
            InputMethodManager imm = (InputMethodManager) focus.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null)
                imm.hideSoftInputFromWindow(focus.getWindowToken(), 0);
        }
    }

}
