package com.abrsoftware.speeddytest.view;

import com.abrsoftware.speeddytest.model.Qoute;

import java.util.List;

/**
 * Created by AbrWin on 23/05/19.
 */

public class GeneralEvent {
    public final static int SUCCES_RESPONCE = 1;
    public final static int ERROR_RESPONCE = 2;
    public final static int ON_BE_USER_RESOLVABLE_ERROR = 3;
    public final static int ON_GOOGLE_PLAY_SERVICES_FAILED = 4;
    public final static int ON_SHOW_MSJ_ERROR = 5;
    public final static int ON_SIGN_IN_SUCCESS = 6;
    public final static int ON_SIGN_IN_ERROR = 7;
    public final static int ERROR_GET_NEWS = 8;
    public final static int SHOW_LOADING = 9;

    public int eventType;
    public String errorMessage;
    public int statusCode;
    public List<Qoute> responce;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<Qoute> getResponce() {
        return responce;
    }

    public void setResponce(List<Qoute> responce) {
        this.responce = responce;
    }
}
