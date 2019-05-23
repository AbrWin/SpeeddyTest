package com.abrsoftware.speeddytest.view;

import android.app.Activity;
import android.content.Context;
import androidx.fragment.app.Fragment;
import android.view.View;

import com.abrsoftware.speeddytest.view.dialog.DialogCustom;

public class BaseView extends Fragment {
    public View rootView;
    public Activity activity;
    public Context context;
    private DialogCustom dialogCustom;
    private View viewCustomDialog;



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        this.context = activity.getApplicationContext();
    }

    public void inflateCustomDialog(int idLayout) {
        View layoutDialog = activity.getLayoutInflater().inflate(idLayout, null);
        dialogCustom = new DialogCustom(activity, layoutDialog);
        setViewCustomDialog(layoutDialog);
    }

    public void hiddeCustomDialog() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialogCustom != null) {
                    dialogCustom.dismiss();
                }
            }
        });
    }

    public void showCustomDialog() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialogCustom != null) {
                    dialogCustom.setCancelable(false);
                    dialogCustom.setCanceledOnTouchOutside(false);
                    dialogCustom.show();
                }
            }
        });

    }

    public View getViewCustomDialog() {
        return viewCustomDialog;
    }

    public void setViewCustomDialog(View viewCustomDialog) {
        this.viewCustomDialog = viewCustomDialog;
    }

    public DialogCustom getDialogCustom() {
        return dialogCustom;
    }

    public void setDialogCustom(DialogCustom dialogCustom) {
        this.dialogCustom = dialogCustom;
    }
}
