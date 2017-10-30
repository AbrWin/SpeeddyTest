package com.abrsoftware.speeddytest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.abrsoftware.speeddytest.view.LoginView.LoginView;
import com.abrsoftware.speeddytest.view.detailBrand.DetailBrandView;
import com.abrsoftware.speeddytest.view.homeView.HomeView;
import com.abrsoftware.speeddytest.view.thankyou.ThankYouView;

/**
 * Created by AbrWin on 29/10/17.
 */

public enum ResponsiveUIstate {
    LOGIN() {
        @Override
        public Fragment execute(MainActivity activity) {
            return setNewFragment(activity, R.id.fragment_container, LoginView.class);
        }
    },
    HOME() {
        @Override
        public Fragment execute(MainActivity activity) {
            return setNewFragment(activity, R.id.fragment_container, HomeView.class);
        }
    },
    DETAILBRAND() {
        @Override
        public Fragment execute(MainActivity activity) {
            return setNewFragment(activity, R.id.fragment_container, DetailBrandView.class);
        }
    },
    THANK_YOU() {
        @Override
        public Fragment execute(MainActivity activity) {
            return setNewFragment(activity, R.id.fragment_container, ThankYouView.class);
        }
    };


    ResponsiveUIstate() {
    }

    public abstract Fragment execute(MainActivity activity);
    public Bundle bundle;

    Fragment setNewFragment(MainActivity activity, int id,
                            Class<? extends Fragment> fragmentClass) {

        try {
            Fragment novoFragment = fragmentClass.newInstance();
            if (bundle != null)
                novoFragment.setArguments(bundle);

            FragmentManager manager = activity.getSupportFragmentManager();
            FragmentTransaction tx = manager.beginTransaction();
            //tx.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            //tx.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out,android.R.anim.fade_in,android.R.anim.fade_out);
            tx.replace(id, novoFragment, fragmentClass.getCanonicalName());
            tx.addToBackStack(null);
            tx.commit();
            return novoFragment;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResponsiveUIstate setState(ResponsiveUIstate newState){
        ResponsiveUIstate state = newState;
        return state;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public ResponsiveUIstate setBundle(Bundle bundle) {
        this.bundle = bundle;
        return this;
    }

}
