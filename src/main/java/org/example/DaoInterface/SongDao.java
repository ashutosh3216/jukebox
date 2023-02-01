package org.example.DaoInterface;


import org.example.ModelClass.Song;

import java.util.List;
import java.util.Set;

public interface SongDao {
    public List<Song> getAllSongs();
    public Set<String> getAllGenra();

    public Set<String> getAllArtist();
    public List<Song> getAllSongsByGenra(String genra);
    public boolean getSongPresentById(int songID);
}
