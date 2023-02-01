package org.example.DaoImpl;



import org.example.DaoInterface.PodcastDao;
import org.example.ModelClass.Podcast;
import org.example.DBConnection.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PodcastDaoImpl implements PodcastDao {


    public List<Podcast> getAllPodcasts(){
        List<Podcast> pods=new ArrayList<>();
        try(Connection con=new DBConnection().getConnection()){
            Statement statement = con.createStatement();
            String sqlUpdate = ("Select * from Podcast");
          //  System.out.println(sqlUpdate);
            ResultSet set = statement.executeQuery(sqlUpdate);
            while (set.next()) {
                pods.add(new Podcast(set.getInt("id"), set.getString("albumName"), set.getInt("NoOfepisodes"), set.getString("artistName"), set.getString("duration")));
            }}
        catch(SQLException e){
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return pods;
    }


}
