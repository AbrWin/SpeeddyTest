package com.abrsoftware.speeddytest.helper;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by AbrWin on 23/05/19.
 */

public class GreenRoboHelper implements Eventbus {
    EventBus eventBus;

    public static class SingletonHolder {
        private static final GreenRoboHelper INSTANCE = new GreenRoboHelper();
    }

    public static GreenRoboHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public GreenRoboHelper() {
        this.eventBus = EventBus.getDefault();
    }


    @Override
    public void register(Object subscribe) {
        this.eventBus.register(subscribe);
    }

    @Override
    public void unregister(Object subscribe) {
        this.eventBus.unregister(subscribe);
    }

    @Override
    public void post(Object event) {
        this.eventBus.post(event);
    }
}
