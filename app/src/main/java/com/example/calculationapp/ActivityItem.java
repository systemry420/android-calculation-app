package com.example.calculationapp;

public class ActivityItem {
    String text;
    int icon;

    public String getText() {
        return text;
    }

    public int getIcon() {
        return icon;
    }

    public ActivityItem(String text, int icon) {
        this.text = text;
        this.icon = icon;
    }
}
