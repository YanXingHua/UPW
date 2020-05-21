package com.example.upw.ui.sustainability;

import java.util.List;

public class Sustainability {
    private List<String> Title;
    private String text;
    private String image;

    public Sustainability(List<String> title, String text, String image) {
        Title = title;
        this.text = text;
        this.image = image;
    }

    public List<String> getTitle() {
        return Title;
    }

    public void setTitle(List<String> title) {
        Title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
