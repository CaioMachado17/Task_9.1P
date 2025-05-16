package com.example.task91p;

public class Advert {
    int id;
    String type, name, phone, description, date, location;
    double latitude, longitude;

    public Advert(int id, String type, String name, String phone, String description, String date, String location, double latitude, double longitude) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.date = date;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
