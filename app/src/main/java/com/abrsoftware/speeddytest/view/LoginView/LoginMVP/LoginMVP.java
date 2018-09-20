package com.abrsoftware.speeddytest.view.LoginView.LoginMVP;

import com.abrsoftware.speeddytest.view.GeneralEvent;

/**
 * Created by AbrWin on 29/10/17.
 */

public class LoginMVP {

    public interface View {
        void showLoading(boolean show);

        void showMsj(String msj);

        void succesUser();
    }

    public interface Presenter {
        void loginUser(String user, String password);

        void oncreate();

        void ondestroy();

        void onEventMainThread(GeneralEvent event);
    }

    public interface Repository {
        void doSingIn(String user, String password);
    }

}
