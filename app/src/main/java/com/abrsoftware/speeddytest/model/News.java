package com.abrsoftware.speeddytest.model;

import java.io.Serializable;

public class News implements Serializable{
    private int id;
    private  String creationDate;
    private String urlImagen;
    private String urlImagenHD;
    private String title;
    private String description;
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getUrlImagenHD() {
        return urlImagenHD;
    }

    public void setUrlImagenHD(String urlImagenHD) {
        this.urlImagenHD = urlImagenHD;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
