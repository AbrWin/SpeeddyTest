package com.abrsoftware.speeddytest.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;

public class DialogCustom extends Dialog {
    private View viewDialog;

    public DialogCustom(Activity activity, View view) {
        super(activity);
        viewDialog = view;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(view);
        getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
        getWindow().setGravity(Gravity.NO_GRAVITY);
    }
}