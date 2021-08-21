package sg.edu.rp.c346.id20022404.mynotesapp;

import java.io.Serializable;

public class Note implements Serializable {

    private int id;
    private String title;
    private String description;
    private String pin;

    public Note(int id, String title, String description, String pin) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.pin = pin;

    }

    public int getId() {
        return id;
    }

    public Note setId(int id) {
        this.id = id;
        return this;
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

    public String getPin() {

        return pin;
    }

    public void setPin(String pin) {

        this.pin = pin;
    }

    /*
    @NonNull
    @Override
    public String toString() {
        String starsString = "";
        for(int i = 0; i < stars; i++){
            starsString += "*";
        }
        return name + "\n" + description + " - " + area + "\n" + starsString;

    }
    */
}

