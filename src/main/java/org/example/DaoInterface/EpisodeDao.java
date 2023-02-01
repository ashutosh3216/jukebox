package org.example.DaoInterface;




import org.example.ModelClass.Episode;

import java.util.List;

public interface EpisodeDao {
    public List<Episode> getAllEpisodes();
    public String getEpisodeIdbyName(float id);

}
