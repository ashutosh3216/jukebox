package org.example.ModelClass;

public class Podcast {
    int id;
    String albumName;
    int noOfEpisode;
    String artistName;
    String duration;

    public Podcast(int id, String albumName, int noOfEpisode, String artistName, String duration) {
        this.id = id;
        this.albumName = albumName;
        this.noOfEpisode = noOfEpisode;
        this.artistName = artistName;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getNoOfEpisode() {
        return noOfEpisode;
    }

    public void setNoOfEpisode(int noOfEpisode) {
        this.noOfEpisode = noOfEpisode;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
