package com.abrsoftware.speeddytest.view;
import com.abrsoftware.speeddytest.helper.GreenRoboHelper;
import com.abrsoftware.speeddytest.model.Qoute;
import java.util.List;


/**
 * Created by AbrWin on 23/05/19.
 */

public class PostEvent {

    private final GeneralEvent loginEvent;
    public GreenRoboHelper eventBus;

    public PostEvent() {
        this.eventBus = GreenRoboHelper.getInstance();
        loginEvent = new GeneralEvent();
    }

    /**
     * Post one event
     *
     * @param type
     */
    public void postEvent(int type) {
        postEvent(type, "");
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

    public void postEvent(int type, List<Qoute> responce) {
        loginEvent.setEventType(type);
        loginEvent.setResponce(responce);
        eventBus.post(loginEvent);
    }

}
