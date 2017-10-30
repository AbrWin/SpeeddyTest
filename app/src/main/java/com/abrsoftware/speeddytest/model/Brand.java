package com.abrsoftware.speeddytest.model;

import java.io.Serializable;

/**
 * Created by AbrWin on 29/10/17.
 */

public class Brand implements Serializable{
    private String name;
    private String resume;
    private boolean isChecked;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
}
