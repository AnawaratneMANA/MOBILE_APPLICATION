package com.example.homeworktackingsoftware;

public class SongList {
    String songName;
    String path;

    public SongList(String songName, String path) {
        this.songName = songName;
        this.path = path;
    }

    public SongList() {
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }



    public void setPath(String path) {
        this.path = path;
    }
    public String getPath() {
        return path;
    }
}
