package com.example.calculationapp.data;

public class NoteData {
    public String ID;
    public String title;
    public String date;
    public String content;

    public NoteData(String id, String title, String date, String content) {
        this.ID = id;
        this.title = title;
        this.date = date;
        this.content = content;
    }

    public String getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }
}
