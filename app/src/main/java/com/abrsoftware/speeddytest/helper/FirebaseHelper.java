package com.abrsoftware.speeddytest.helper;

import com.google.firebase.auth.FirebaseAuth;


/**
 * Created by AbrWin on 23/05/19.
 */

public class FirebaseHelper {
    private static FirebaseAuth auth;


    private static class SinglentonHolder {
        private static final FirebaseHelper INSTANCE = new FirebaseHelper();
    }

    public static FirebaseHelper getInstance() {
        return SinglentonHolder.INSTANCE;
    }

    public FirebaseHelper() {
        this.auth = FirebaseAuth.getInstance();
    }

    public static FirebaseAuth getAuthReference() {
        return auth;
    }
}
