package com.abrsoftware.speeddytest.helper;

/**
 * Created by AbrWin on 05/04/17.
 */

public interface Eventbus {
    void register(Object subscribe);
    void unregister(Object subscribe);
    void post(Object event);
}
