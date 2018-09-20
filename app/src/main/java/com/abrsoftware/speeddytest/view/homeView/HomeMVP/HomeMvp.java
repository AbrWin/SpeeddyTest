package com.abrsoftware.speeddytest.view.homeView.HomeMVP;

import com.abrsoftware.speeddytest.model.News;
import com.abrsoftware.speeddytest.view.GeneralEvent;

import java.util.List;

public class HomeMvp {
    public interface View {
        void showLoading(boolean show);

        void succesGetNews(List<News> news);

        void showMsj(String msj);
    }

    interface Presenter {
        void getNewsEs();

        void getNewsEn();

        void oncreate();

        void ondestroy();

        void onEventMainThread(GeneralEvent event);

    }

    interface Repository{
        void getNewsEs();

        void getNewsEn();
    }
}
