package org.example.DaoImpl;



import org.example.DaoInterface.EpisodeDao;
import org.example.ModelClass.Episode;
import org.example.DBConnection.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EpisodeDaoImpl implements EpisodeDao {



    @Override
    public List<Episode> getAllEpisodes() {
        List<Episode> episodes=new ArrayList<>();
        try(Connection con=new DBConnection().getConnection()){
            Statement statement = con.createStatement();
            String sqlUpdate = ("Select * from Episodes");
            //System.out.println(sqlUpdate);
            ResultSet set = statement.executeQuery(sqlUpdate);
            while (set.next()) {
                episodes.add(new Episode(set.getInt("id"), set.getFloat("episodeId"), set.getString("episodeName"), set.getString("artistName"), set.getString("url")));
            }
        }
        catch (SQLException e){
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return episodes;
    }

    public String getEpisodeIdbyName(float id){
       String ep="";
        List<Episode> episodes=new ArrayList<>();
        episodes=getAllEpisodes();
        for(Episode e:episodes)
        {
            if(id==e.getEpisodeId())
            { ep=e.getEpisodeName();
            return ep;
            }
        }
        return ep;
    }

}
