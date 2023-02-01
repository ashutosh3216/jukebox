package org.example.DaoImpl;


import org.example.DaoInterface.PlaylistDao;
import org.example.DBConnection.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;

import static org.example.DBConnection.DBConnection.getConnection;


public class PlaylistDaoImpl implements PlaylistDao {
    Set<String> playlist = new TreeSet<>();
    Set<Integer> songlist = new TreeSet<>();
    Set<Float> epilist = new TreeSet<>();


    public boolean addSongToPlayList(int songid, int userid, int playlistid, String playlistName) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String sqlUpdate = ("Insert into Playlist (songId,userid,playlistId,playlistName) values (" + songid + "," + userid + "," + playlistid + ",'" + playlistName + "')");
        statement.executeUpdate(sqlUpdate);
        return true;
    }
    public Set<String> getAllPlaylist(int userid) {
        try (Connection con = DBConnection.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from Playlist where playlistId=1 and userid="+userid);
            while (resultSet.next()) {
                playlist.add(resultSet.getString("playlistName"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return playlist;
    }

    public Set<String> getAllPlaylistPodcast() {
        try (Connection con = DBConnection.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from Playlist where playlistId=2");
            while (resultSet.next()) {
                playlist.add(resultSet.getString("playlistName"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return playlist;
    }

    public boolean addPodcastToPlayList(float episode_id, int userid, int playlistid, String playlistName) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String sqlUpdate = ("Insert into Playlist (episodeId,userid,playlistId,playlistName) values (" + episode_id + "," + userid + "," + playlistid + ",'" + playlistName + "')");
        statement.executeUpdate(sqlUpdate);
        return true;
    }
    public Set<Integer> getAllPlaylistSong(String playlistName,int UserID) {
        try (Connection con = DBConnection.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from Playlist where playlistName='"+playlistName+"' and userid="+UserID);
            while (resultSet.next()) {
                songlist.add(resultSet.getInt("songid"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return songlist;
    }

    public Set<Float> getAllPlaylistEpisodes(String playlistName,int UserID) {
        try (Connection con = DBConnection.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from Playlist where playlistName='"+playlistName+"' and userid="+UserID);
            while (resultSet.next()) {
                epilist.add(resultSet.getFloat("episodeId"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return epilist;
    }
    public boolean isPlayListPresent(String playlists,int id)
    {
        boolean x=false;

        playlist=getAllPlaylist(id);
        for (String s:playlist)
        {
         if(s.equalsIgnoreCase(playlists))
         {
             x=true;
         }
        }
        return x;
    }
}
