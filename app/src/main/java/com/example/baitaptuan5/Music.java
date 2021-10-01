package com.example.baitaptuan5;

public class Music {
    private String author;
    private String songName;
    private int image;

    public Music(String author, String songName, int image) {
        this.author = author;
        this.songName = songName;
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
