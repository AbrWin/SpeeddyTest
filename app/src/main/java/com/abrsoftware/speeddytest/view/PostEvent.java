package com.abrsoftware.speeddytest.view;
import com.abrsoftware.speeddytest.helper.GreenRoboHelper;

/**
 * Created by AbrWin on 29/1017.
 */

public class PostEvent {

    public GreenRoboHelper eventBus;

    public PostEvent() {
        this.eventBus = GreenRoboHelper.getInstance();
    }

    /**
     * Post one event
     *
     * @param type
     */
    public void postEvent(int type) {
        postEvent(type, null);
    }

    /**
     * Post event in presenter with message error
     *
     * @param type
     * @param errorMessage
     */
    public void postEvent(int type, String errorMessage) {
        GeneralEvent loginEvent = new GeneralEvent();
        loginEvent.setEventType(type);
        if (errorMessage != null) {
            loginEvent.setErrorMessage(errorMessage);
        }
        eventBus.post(loginEvent);
    }

    public void postEvent(int type, int status) {
        GeneralEvent loginEvent = new GeneralEvent();
        loginEvent.setEventType(type);
        loginEvent.setStatusCode(status);
        eventBus.post(loginEvent);
    }

}
