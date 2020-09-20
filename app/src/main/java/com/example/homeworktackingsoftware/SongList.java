package com.example.homeworktackingsoftware;

public class SongList {
    //1st step : add song ID and make constructor and getters and setters as it is
    String songID;
    String songName;
    String path;

    public SongList(String songID , String songName, String path) {
        this.songID = songID;
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

    public String getSongID() {
        return songID;
    }

    public void setSongID(String songID) {
        this.songID = songID;
    }
}

