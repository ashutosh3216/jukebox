package org.example.DaoImpl;


import org.example.DaoInterface.SongDao;
import org.example.ModelClass.Song;
import org.example.DBConnection.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SongDaoImpl implements SongDao {

    Set<String> genra = new TreeSet<>();
    Set<String> artist = new TreeSet<>();

    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from songs");
            while (resultSet.next()) {
                songs.add(new Song(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return songs;
    }

    public Set<String> getAllGenra() {
        try (Connection con = DBConnection.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from songs");
            while (resultSet.next()) {
                genra.add(resultSet.getString("genreOfSong"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return genra;
    }

    public List<Song> getAllSongsByGenra(String genra) {
        List<Song> songs = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from songs where genreOfSong='" + genra + "'");
            while (resultSet.next()) {
                songs.add(new Song(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return songs;
    }


    public Set<String> getAllArtist() {
        try (Connection con = DBConnection.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from songs");
            while (resultSet.next()) {
                artist.add(resultSet.getString("artistName"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return artist;
    }

    public boolean getSongPresentById(int songID) {
        boolean x = false;
        List<Song> s;
        s=getAllSongs();
        for (Song song:s)
        {
            if(songID==song.getSong_id())
            {
                x=true;
            }
        }

        return x;
    }


}
