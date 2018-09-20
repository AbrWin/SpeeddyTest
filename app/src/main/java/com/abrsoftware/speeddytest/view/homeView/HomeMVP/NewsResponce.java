package com.abrsoftware.speeddytest.view.homeView.HomeMVP;

import com.abrsoftware.speeddytest.model.News;

import java.util.List;

public class NewsResponce {
    private String mensaje;
    private List<News> news;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }
}
