package com.abrsoftware.speeddytest.view.homeView.HomeMVP;
import com.abrsoftware.speeddytest.model.Qoute;
import com.abrsoftware.speeddytest.view.GeneralEvent;

import java.util.List;

public class HomeMvp {
    public interface View {
        void showLoading(boolean show);

        void succesGetQoutes(List<Qoute> qoutes);

        void showMsj(String msj);
    }

    interface Presenter {
        void getQuotes();

        void oncreate();

        void ondestroy();

        void onEventMainThread(GeneralEvent event);

    }

    interface Repository{
        void getQuotes();
    }
}
