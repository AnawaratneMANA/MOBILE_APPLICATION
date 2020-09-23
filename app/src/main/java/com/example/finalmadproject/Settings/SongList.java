package com.example.finalmadproject.Settings;

public class SongList {
    //1st step : add song ID and make constructor and getters and setters as it is
    String songID;
    String songName;
    //added
    String status;
    String path;
    //cleared and added
    public SongList(String songID, String songName, String status, String path) {
        this.songID = songID;
        this.songName = songName;
        this.status = status;
        this.path = path;
    }

    public String getSongID() {
        return songID;
    }

    public void setSongID(String songID) {
        this.songID = songID;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

