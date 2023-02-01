package org.example.ModelClass;

public class Playlist {
    int songId;
    int userId;
    int playListId;
    String playListName;
    float episodeId;

    public float getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(float episodeId) {
        this.episodeId = episodeId;
    }

    public Playlist(int userId, int playListId, String playListName, float episodeId) {
        this.userId = userId;
        this.playListId = playListId;
        this.playListName = playListName;
        this.episodeId = episodeId;
    }

    public Playlist(int songId, int userId, int playListId, String playListName) {
        this.songId = songId;
        this.userId = userId;
        this.playListId = playListId;
        this.playListName = playListName;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPlayListId() {
        return playListId;
    }

    public void setPlayListId(int playListId) {
        this.playListId = playListId;
    }

    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }
}
