package com.abrsoftware.speeddytest.view.homeView.HomeMVP;

import com.abrsoftware.speeddytest.helper.Eventbus;
import com.abrsoftware.speeddytest.helper.GreenRoboHelper;
import com.abrsoftware.speeddytest.view.GeneralEvent;

import org.greenrobot.eventbus.Subscribe;

public class HomePresenterImp implements HomeMvp.Presenter {

    public Eventbus eventbus;
    public HomeMvp.View view;
    public HomeRepositoryImp repository;

    public HomePresenterImp(HomeMvp.View view) {
        this.view = view;
        this.eventbus = GreenRoboHelper.getInstance();
        this.repository = new HomeRepositoryImp();
    }

    @Override
    public void oncreate() {
        eventbus.register(this);
    }

    @Override
    public void ondestroy() {
        eventbus.unregister(this);
    }

    @Override
    public void getNewsEs() {
        repository.getNewsEs();
    }

    @Override
    public void getNewsEn() {
        repository.getNewsEn();
    }

    @Subscribe
    @Override
    public void onEventMainThread(GeneralEvent event) {
        if (event == null) {
            return;
        }

        switch (event.eventType) {
            case GeneralEvent.showLoading:
                view.showLoading(true);
                break;
            case GeneralEvent.SuccesGetNews:
                view.showLoading(false);
                view.succesGetNews(event.getResponce().getNews());
                break;
            case GeneralEvent.ErrorGetNews:
                view.showLoading(false);
                view.showMsj(event.getErrorMessage());
                break;
        }

    }
}
