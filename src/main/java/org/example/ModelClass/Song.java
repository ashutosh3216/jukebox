package org.example.ModelClass;

public class Song {
    int song_id;
    String song_name;
    String genre;
    String uauthorrl;
    String artist;
    String duration;

    public Song(int song_id, String song_name, String artist, String genre, String duration, String uauthorrl) {
        this.song_id = song_id;
        this.song_name = song_name;
        this.genre = genre;
        this.uauthorrl = uauthorrl;
        this.duration = duration;
        this.artist = artist;

    }



    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getUathorrl() {
        return uauthorrl;
    }

    public void setUathorrl(String uauthorrl) {
        this.uauthorrl = uauthorrl;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Song{" +
                "song_id=" + song_id +
                ", song_name='" + song_name + '\'' +
                ", genre='" + genre + '\'' +
                ", path='" + uauthorrl + '\'' +
                ", artist='" + artist + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
