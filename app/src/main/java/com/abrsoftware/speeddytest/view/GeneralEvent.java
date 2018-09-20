package com.abrsoftware.speeddytest.view;

import com.abrsoftware.speeddytest.view.homeView.HomeMVP.NewsResponce;

/**
 * Created by AbrWin on 29/10/17.
 */

public class GeneralEvent {
    public final static int onSignUpError = 1;
    public final static int onBeUserResolvableError = 2;
    public final static int onGooglePlayServicesFailed = 3;
    public final static int onShowMsjError = 4;
    public final static int onSignUpSuccess = 7;
    public final static int onCompleteDataUser = 8;
    public final static int onSignInSuccess = 9;
    public final static int onSignInError = 10;
    public final static int onErrorDataBase = 11;
    public final static int GeneralError = 12;
    public final static int SuccesGetNews = 13;
    public final static int ErrorGetNews = 14;
    public final static int showLoading = 15;

    public int eventType;
    public String errorMessage;
    public int statusCode;
    public NewsResponce responce;

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

    public NewsResponce getResponce() {
        return responce;
    }

    public void setResponce(NewsResponce responce) {
        this.responce = responce;
    }
}
