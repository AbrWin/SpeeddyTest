package com.abrsoftware.speeddytest.view.LoginView.LoginMVP;

import com.abrsoftware.speeddytest.helper.Eventbus;
import com.abrsoftware.speeddytest.helper.GreenRoboHelper;
import com.abrsoftware.speeddytest.view.GeneralEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by AbrWin on 23/05/19.
 */

public class LoginPresenterImp implements LoginMVP.Presenter {

    public Eventbus eventbus;
    public LoginMVP.View cAccountView;
    public LoginRepositoryImp loginRepositoryImp;

    public LoginPresenterImp(LoginMVP.View cAccountView) {
        this.eventbus = GreenRoboHelper.getInstance();
        this.cAccountView = cAccountView;
        this.loginRepositoryImp = new LoginRepositoryImp();
    }

    @Override
    public void loginUser(String user, String password) {
        loginRepositoryImp.doSingIn(user, password);
    }

    @Override
    public void oncreate() {
        eventbus.register(this);
    }

    @Override
    public void ondestroy() {
        eventbus.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    @Override
    public void onEventMainThread(GeneralEvent event) {
        if (event != null) {
            switch (event.getEventType()) {
                case GeneralEvent.showLoading:
                    cAccountView.showLoading(true);
                    break;
                case GeneralEvent.onShowMsjError:
                    cAccountView.showLoading(false);
                    cAccountView.showMsj(event.getErrorMessage());
                    break;
                case GeneralEvent.onSignInError:
                    cAccountView.showLoading(false);
                    cAccountView.showMsj(event.getErrorMessage());
                    break;
                case GeneralEvent.onSignInSuccess:
                    cAccountView.showLoading(false);
                    cAccountView.succesUser();
                    break;
            }
        }

    }
}
