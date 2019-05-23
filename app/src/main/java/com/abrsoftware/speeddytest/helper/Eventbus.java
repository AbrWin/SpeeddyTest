package com.abrsoftware.speeddytest.helper;

/**
 * Created by AbrWin on 23/05/19.
 */

public interface Eventbus {
    void register(Object subscribe);
    void unregister(Object subscribe);
    void post(Object event);
}
