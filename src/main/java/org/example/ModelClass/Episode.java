package org.example.ModelClass;

public class Episode {
    int id;
    float episodeId;
    String episodeName;
    String artistName;
    String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(float episodeId) {
        this.episodeId = episodeId;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Episode(int id, float episodeId, String episodeName, String artistName, String url) {
        this.id = id;
        this.episodeId = episodeId;
        this.episodeName = episodeName;
        this.artistName = artistName;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "id=" + id +
                ", episodeId=" + episodeId +
                ", episodeName='" + episodeName + '\'' +
                ", artistName='" + artistName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
