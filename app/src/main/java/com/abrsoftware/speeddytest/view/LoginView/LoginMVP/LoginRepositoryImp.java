package com.abrsoftware.speeddytest.view.LoginView.LoginMVP;

import android.content.Context;
import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.util.Patterns;

import com.abrsoftware.speeddytest.MyApplication;
import com.abrsoftware.speeddytest.R;
import com.abrsoftware.speeddytest.helper.FirebaseHelper;
import com.abrsoftware.speeddytest.utils.Connectivity;
import com.abrsoftware.speeddytest.view.GeneralEvent;
import com.abrsoftware.speeddytest.view.PostEvent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by AbrWin on 23/05/19.
 */

public class LoginRepositoryImp extends PostEvent implements LoginMVP.Repository {
    public FirebaseAuth authReference;

    public LoginRepositoryImp() {
        this.authReference = FirebaseHelper.getAuthReference();
    }

    @Override
    public void doSingIn(String email, String password) {
        if (!Connectivity.isOnline(MyApplication.getCtx())) {
            postEvent(GeneralEvent.onShowMsjError, MyApplication.getCtx().getString(R.string.error_connec));
            return;
        }

        //Check GooglePlayServices
        if (!isGooglePlayServicesAvaliable(MyApplication.getCtx())) {
            return;
        }

        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            postEvent(GeneralEvent.onShowMsjError, MyApplication.getCtx().getString(R.string.error_mail));
        } else if (TextUtils.isEmpty(password)) {
            postEvent(GeneralEvent.onShowMsjError, MyApplication.getCtx().getString(R.string.error_password));
        } else {
            postEvent(GeneralEvent.showLoading);
            doSingInUser(email, password);
        }
    }

    public boolean isGooglePlayServicesAvaliable(Context context) {
        int statusCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);
        if (GoogleApiAvailability.getInstance().isUserResolvableError(statusCode)) {
            postEvent(GeneralEvent.onBeUserResolvableError, statusCode);
            return false;
        } else if (statusCode != ConnectionResult.SUCCESS) {
            postEvent(GeneralEvent.onGooglePlayServicesFailed);
            return false;
        }
        return true;
    }

    public void doSingInUser(String email, String password) {
        authReference.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    postEvent(GeneralEvent.onShowMsjError, MyApplication.getCtx().getString(R.string.error_mail));
                } else {
                    postEvent(GeneralEvent.onSignInSuccess);
                }
            }
        });
    }
}
