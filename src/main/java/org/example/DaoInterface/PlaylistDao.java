package org.example.DaoInterface;


import java.sql.SQLException;
import java.util.Set;

public interface PlaylistDao {
    public boolean addSongToPlayList(int songid, int userid, int playlistid, String playlistName) throws SQLException, ClassNotFoundException;

    public Set<String> getAllPlaylist(int userid);
    public Set<Integer> getAllPlaylistSong(String playlistName,int UserID) ;
    public Set<Float> getAllPlaylistEpisodes(String playlistName,int UserID) ;

    public boolean addPodcastToPlayList(float episode_id, int userid, int playlistid, String playlistName) throws SQLException, ClassNotFoundException;

    public Set<String> getAllPlaylistPodcast();
    public boolean isPlayListPresent(String playlists,int id);
}
