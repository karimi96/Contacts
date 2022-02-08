package com.example.contacts.activitys;

public class Contact {
    public int id;
    public String title;
    public String phone;
    public int image;


    public Contact(String title, String dis, int image) {
        this.title = title;
        this.phone = dis;
        this.image = image;
    }

    public Contact() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
