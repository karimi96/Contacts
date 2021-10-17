package com.example.myapplicationnnnnnnnnnnnnnnnnnnnnnnnnnn;

public class Contact {
    String title;
    String phone;
    int image;

    public Contact(String title, String dis, int image) {
        this.title = title;
        this.phone = dis;
        this.image = image;
    }

    public Contact() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDis() {
        return phone;
    }

    public void setDis(String dis) {
        this.phone = dis;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
