
import org.example.DaoImpl.EpisodeDaoImpl;
import org.example.DaoImpl.PlaylistDaoImpl;
import org.example.DaoImpl.SongDaoImpl;
import org.example.DaoImpl.UserDaoImpl;

import org.example.DaoInterface.EpisodeDao;
import org.example.DaoInterface.PlaylistDao;
import org.example.DaoInterface.SongDao;
import org.example.DaoInterface.UserDao;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;


public class MainActivityTest {


    @Test
    public void CheckUser() {
        UserDao ud = new UserDaoImpl();
        assertTrue(ud.checkUser("ashu@123", "qwerty"));
    }

    @Test
    public void CheckNonUser() {
        UserDao ud = new UserDaoImpl();
        assertFalse(ud.checkUser("ashu@12", "qwert"));
    }

    @Test
    public void CheckSongById() {
        SongDao sd = new SongDaoImpl();
        assertTrue(sd.getSongPresentById(1001));
    }

    @Test
    public void CheckPlaylistPresent() {
        PlaylistDao pd = new PlaylistDaoImpl();
        assertTrue(pd.isPlayListPresent("ashu1", 101));
    }

    @Test
    public void CheckPlaylistAbsent() {
        PlaylistDao pd = new PlaylistDaoImpl();
        assertFalse(pd.isPlayListPresent("ashu6", 101));
    }

    @Test
    public void checkEPisodeNamebyID() {
        EpisodeDao ed = new EpisodeDaoImpl();
        assertEquals("The figth", ed.getEpisodeIdbyName(1.3f));
    }
}
