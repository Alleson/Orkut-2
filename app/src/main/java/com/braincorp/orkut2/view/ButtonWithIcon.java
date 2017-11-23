package com.braincorp.orkut2.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.braincorp.orkut2.R;

public class ButtonWithIcon extends CardView {

    private Drawable icon;

    private CharSequence text;

    public ButtonWithIcon(Context context) {
        super(context);
        init(context);
    }

    public ButtonWithIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ButtonWithIcon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public Drawable getIcon() {
        return icon;
    }

    public CharSequence getText() {
        return text;
    }

    private void init(Context context) {
        init(context, null);
    }

    private void init(Context context, AttributeSet attributes) {
        setRadius(5);
        ViewGroup root = this;
        final boolean attachToRoot = true;
        LayoutInflater.from(context).inflate(R.layout.view_button_with_icon, root, attachToRoot);
        parseAttrs(context, attributes);
    }

    private void parseAttrs(Context context, AttributeSet attributes) {
        if (attributes == null)
            return;
        TypedArray typedArray = context.obtainStyledAttributes(attributes, R.styleable.ButtonWithIcon);

        icon = typedArray.getDrawable(R.styleable.ButtonWithIcon_icon);
        text = typedArray.getText(R.styleable.ButtonWithIcon_text);

        ImageView iconImageView = findViewById(R.id.icon);
        iconImageView.setImageDrawable(icon);

        TextView textTextView = findViewById(R.id.text);
        textTextView.setText(text);

        typedArray.recycle();
    }

}
