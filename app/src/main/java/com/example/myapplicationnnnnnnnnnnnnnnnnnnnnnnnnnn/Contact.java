package com.example.myapplicationnnnnnnnnnnnnnnnnnnnnnnnnnn;

import java.util.ArrayList;

public class Contact {
    String title;
    String phone;
    ArrayList<String> tit;
    ArrayList<String> ph;
    int image;


    public Contact(ArrayList<String> tit, ArrayList<String> ph,int image) {
        this.tit = tit;
        this.ph = ph;
        this.image = image;
    }
//
//        public Contact(String title, String dis, int image) {
//        this.title = title;
//        this.phone = dis;
//        this.image = image;
//    }

//    public Contact(String title, String phone) {
//        this.title = title;
//        this.phone = phone;
//    }

//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDis() {
//        return phone;
//    }
//
//    public void setDis(String dis) {
//        this.phone = dis;
//    }
//
//    public int getImage() {
//        return image;
//    }
//
//    public void setImage(int image) {
//        this.image = image;
//    }


    public ArrayList<String> getTit() {
        return tit;
    }

    public void setTit(ArrayList<String> tit) {
        this.tit = tit;
    }

    public ArrayList<String> getPh() {
        return ph;
    }

    public void setPh(ArrayList<String> ph) {
        this.ph = ph;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
