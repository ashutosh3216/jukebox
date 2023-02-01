package org.example.activity;


import org.example.DaoImpl.*;
import org.example.DaoInterface.*;
import org.example.ModelClass.Episode;
import org.example.ModelClass.Podcast;
import org.example.ModelClass.Song;
import org.example.ModelClass.User;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class MainActivity {
    static PlaylistDao pd = new PlaylistDaoImpl();
    static UserDao ud = new UserDaoImpl();
    static PodcastDao podD = new PodcastDaoImpl();
    static EpisodeDao epiD = new EpisodeDaoImpl();
    static SongDao sd = new SongDaoImpl();
    static String name;
    static int id;
    static Scanner scanner = new Scanner(System.in);
    static Boolean userCheck = false;

    static int count = 0;

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException, SQLException, ClassNotFoundException {


        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
  //      SampleWork.handler();
        System.out.println("::::::::::W E  A R E  P L E A S E D  T O  S E R V E  Y O U::::::::");
   //     SampleWork.handler();
        System.out.println("::::::::::::::::Thank you for choosing juke box:::::::::::::::::::");

        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println();

        System.out.println("Do you want to :");
        System.out.println("(1) Login   (2) Register");
        System.out.println();
        System.out.println("-> Enter 1 to Login or Enter 2 to Register ");

        int caseLogin = scanner.nextInt();
        switch (caseLogin) {
            case 1:
                login();
                break;
            case 2:
                signup();
                System.out.println();
                login();
                break;
            case 3:
                exit();
                break;
            default:
                System.out.println("Invalid Case");
        }
        homeMenu();

    }


    public static void login() {
        System.out.println(":::::::::::::::::::::::: Login Page ::::::::::::::::::::::::::::::");
        System.out.println("Enter UserNAme :");
        String userName = scanner.next();
        System.out.println("Enter Password :");

        String password = scanner.next();
        if (ud.checkUser(userName, password)) {
            id = ud.getuserId(userName);
            System.out.println("Login Success");
            userCheck = true;
        } else {
            System.out.println("credentials are not valid : Authentication Failure");
            count = count + 1;
            if (count < 3) {
                System.out.println("Attempt no :" + count);
                System.out.println("More than 3 attempt Program will exit Automatically ");
                System.out.println("Do you want to :");
                System.out.println("{------------(1) Login Again   (2) Register   (3) Exit}");
                int caseLogin = scanner.nextInt();
                switch (caseLogin) {
                    case 1:
                        login();
                        break;
                    case 2:
                        signup();
                        System.out.println();
                        login();
                        break;
                    case 3:
                        exit();
                        break;
                    default:
                        System.out.println("Invalid Case");
                }
            } else {
                System.out.println("You have exceeded login limit " + count);
                System.exit(0);
            }
        }
    }

    public static void signup() {
        System.out.println("::::::::::::::::::::::::: Sign UP Page ::::::::::::::::::::::::::::::");
        System.out.println("Enter Name :");
        String name = scanner.next();
        System.out.println("Enter userName :");
        String username = scanner.next();
        System.out.println("Enter phoneNumber :");
        String phoneNumber = scanner.next();
        System.out.println("Enter Password :");
        String passwordLogin = scanner.next();

        ud.addUser(new User(username, name, phoneNumber, passwordLogin));
    }

    static void firstCatalog() {
        System.out.println("What you want to play :");
        System.out.println("1) Songs");
        System.out.println("2) Podcast");
        System.out.println("3) Exit");
        System.out.println();
        System.out.println("xxxxxxxxxxx  Enter 1 for Song or Enter 2 for Podcast xxxxxxxxxxxxxx");
    }

    static void homeMenu() throws UnsupportedAudioFileException, LineUnavailableException, IOException, SQLException, ClassNotFoundException {
        if (userCheck) {
            System.out.println("---------------------------------------------------------------------");
            System.out.println(":::::::::::::::::::::::Welcome To Juke Box ::::::::::::::::::::::::::");
            System.out.println("---------------------------------------------------------------------");

            firstCatalog();

            int homeButton = scanner.nextInt();
            switch (homeButton) {
                case 1:

                    SongOperations();

                    break;

                case 2:
                    podOperations();
                    break;
                case 3:
                    exit();
                    break;
            }

        }
    }

    static void SongOperations() throws UnsupportedAudioFileException, LineUnavailableException, IOException, SQLException, ClassNotFoundException {
        System.out.println("Operations ");
        System.out.println("1. Play Songs");
        System.out.println("2. Search Songs");
        System.out.println("3. Add To Playlist");
        System.out.println("4. Go Back");
        System.out.println("5. Exit");

        System.out.println("Enter 1 to Play Songs 2 to Search Songs 3 to Add Playlist , 4 to go Back");

        int songOper = scanner.nextInt();
        switch (songOper) {
            case 1:
                playSong();
                break;
            case 2:
                songSearch();
                break;
            case 3:
                addSongToPlayList();
                break;
            case 4:
                homeMenu();
                break;
            case 5:
                exit();
                break;
        }


    }

    static void podOperations() throws UnsupportedAudioFileException, LineUnavailableException, IOException, SQLException, ClassNotFoundException {
        System.out.println("Operations ");
        System.out.println("1. Play Podcast");
        System.out.println("2. Search Podcast");
        System.out.println("3. Add To Playlist");
        System.out.println("4. Go Back");
        System.out.println("5. Exit");
        System.out.println("Enter 1 to Play Podcast 2 to Search Podcast 3 to Add Playlist ,4 to go Back");
        int podOper = scanner.nextInt();
        switch (podOper) {
            case 1:
                playPodcast();
                break;
            case 2:
                PodcastSearch();
                break;
            case 3:
                addPodcastToPlayList();
                break;
            case 4:
                homeMenu();
                break;
            case 5:
                exit();
                break;
        }
    }

    public static void addSongToPlayList() throws UnsupportedAudioFileException, LineUnavailableException, IOException, SQLException, ClassNotFoundException {
        System.out.println("Playlist :");
        System.out.println("1) Add to existing Playlist");
        System.out.println("2) Add to new Playlist");
        System.out.println("3) Go Back ");
        System.out.println("4) Exit ");
        int playlistchoice = scanner.nextInt();
        switch (playlistchoice) {
            case 1:
                Set<String> playlist = pd.getAllPlaylist(id);
                for (String s : playlist) {
                    System.out.println(s);
                }
                System.out.println("Enter Playlist to add :");
                String playlistName = scanner.next();
                List<Song> songs = sd.getAllSongs();
                for (Song s : songs) {
                    System.out.println(s.getSong_id() + " " + s.getSong_name());
                }
                int i = 0;
                while (i == 0) {
                    System.out.println("Enter ID to Add To PlayList");
                    int sid = scanner.nextInt();

                    SongDao sd = new SongDaoImpl();
                    if (sd.getSongPresentById(sid)) {
                        for (Song s : songs) {
                            if (sid == s.getSong_id()) {
                                pd.addSongToPlayList(sid, id, 1, playlistName);
                            }
                        }
                        System.out.println("Enter 0 to continue else Enter anything else ");
                        int flag = scanner.nextInt();
                        i = flag;
                    }
                    else{
                        System.out.println("Song is not Present Try another songs mentioned in list ");
                        addSongToPlayList();
                    }
                }
                SongOperations();
                break;
            case 2:
                System.out.println("Enter new Playlist Name");
                String playnewList = scanner.next();
                List<Song> song = sd.getAllSongs();
                for (Song s : song) {
                    System.out.println(s.getSong_id() + " " + s.getSong_name());
                }
                int j = 0;
                while (j == 0) {
                    System.out.println("Enter ID to Add To PlayList");
                    int sid = scanner.nextInt();
                    for (Song s : song) {
                        if (sid == s.getSong_id()) {
                            pd.addSongToPlayList(sid, id, 1, playnewList);
                        }
                    }
                    System.out.println("Enter 0 to continue else Enter anything else ");
                    int flag = scanner.nextInt();
                    j = flag;
                }
                SongOperations();
                break;
            case 3:
                SongOperations();
                break;
            case 4:
                exit();
                break;
        }
    }

    static void playSong() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        List<Song> songList = sd.getAllSongs();
        System.out.println();
        System.out.format("%-10s   %-25s   %-20s", "Song ID", "Song Name", "Artist Name");
        System.out.println();
        for (Song song : songList) {
            System.out.format("%-10s   %-25s   %-20s", song.getSong_id(), song.getSong_name(), song.getArtist());
            System.out.println();

        }
        System.out.println("Enter id to Play Song :");
        int id = scanner.nextInt();
        if (sd.getSongPresentById(id)) {
            for (Song song : songList) {
                if (id == song.getSong_id()) {
                    PlayActivity play = new PlayActivity();
                    play.PlaySong(song.getUathorrl(), songList);
                }
            }
        } else {
            System.out.println("That Song is not Present in the Juke Box ");
            System.out.println("Enter 1 to play again 0 to exit");

            int playAgain = scanner.nextInt();
            switch (playAgain) {
                case 1:
                    playSong();
                    break;
                case 0:
                    exit();
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    static void songSearch() throws UnsupportedAudioFileException, LineUnavailableException, IOException, SQLException, ClassNotFoundException {
        System.out.println("Search By ");
        System.out.println("1. Artist");
        System.out.println("2. Genera");
        System.out.println("3. Playlist");
        System.out.println("4. Alphabets");
        System.out.println("5. Go Back");
        System.out.println("6. Exit");
        int songSearch;
        songSearch = scanner.nextInt();
        switch (songSearch) {
            case 1:
                songByArtist();
                break;
            case 2:
                songByGen();
                break;
            case 3:
                songByPlaylist();
                break;
            case 4:
                songByAlphabetes();
                break;
            case 5:
                SongOperations();
            case 6:
                exit();
                break;
        }

    }

    static void songByAlphabetes() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        List<Song> songs = sd.getAllSongs();
        Comparator<Song> comparator = (o1, o2) -> {


            return o1.getSong_name().compareTo(o2.getSong_name());

        };
        songs.sort(comparator);
        System.out.println("---Sorted List by Name--------------");
        for (Song song : songs) {
            System.out.println(song.getSong_id() + "  " + song.getSong_name() + " ");

        }
        System.out.println("Enter id to play the songs :");
        int sid = scanner.nextInt();
        int flag = 0;
        String url = "";
        for (Song song : songs) {
            if (song.getSong_id() == sid) {
                url = song.getUathorrl();
                flag = 1;
            }
        }
        if (flag == 1) {
            PlayActivity play = new PlayActivity();
            play.PlaySongGen(url, songs);
        } else {
            System.out.println(" That Song is Not Available ");
            System.out.println("Enter 1 to play again 0 to exit");

            int playAgain = scanner.nextInt();
            switch (playAgain) {
                case 1:
                    songByAlphabetes();
                    break;
                case 0:
                    exit();
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }

    }

    static void songByGen() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        PlayActivity play = new PlayActivity();
        Set<String> set = sd.getAllGenra();
        System.out.println("---Total genra List--------------");
        for (String s : set) {
            System.out.println(s);
        }
        // List<Song> songs = sd.getAllSongs();

        //  List<Song> filterList = new ArrayList<>();

        System.out.println("Enter Genra to be Filtered :");
        String genra = scanner.next();

        List<Song> filterList = sd.getAllSongsByGenra(genra);
        for (Song song : filterList) {
            System.out.println(song.getSong_id() + "  " + song.getSong_name() + " " + song.getGenre());
        }
        System.out.println("Enter Id to Play Music");
        int song_id = scanner.nextInt();

        int flag = 0;
        String url = "";

        for (Song song : filterList) {
            if (song_id == song.getSong_id()) {

                flag = 1;
                url = song.getUathorrl();
            }

        }
        if (flag == 1) {
            play.PlaySongGen(url, filterList);
        } else {
            System.out.println(" That Song is Not Available ");
            System.out.println("Enter 1 to play again 0 to exit");

            int playAgain = scanner.nextInt();
            switch (playAgain) {
                case 1:
                    songByGen();
                    break;
                case 0:
                    exit();
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    static void songByArtist() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Set<String> set = sd.getAllArtist();
        System.out.println("---Total Artist List--------------");
        for (String s : set) {
            System.out.println(s);
        }

        List<Song> songs = sd.getAllSongs();
        List<Song> filterList = new ArrayList<>();
        System.out.println("Enter artist to be Filtered :");

        String artist = scanner.next();

        for (Song song : songs) {
            if (artist.equalsIgnoreCase(song.getArtist())) {
                filterList.add(song);
            }
        }
        for (Song song : filterList) {
            System.out.format("%1s %10s %10s", song.getSong_id(), song.getSong_name(), song.getArtist());
            System.out.println();
        }
        System.out.println("Enter Id to Play Music");
        int song_id = scanner.nextInt();
        int flag = 0;
        String url = "";

        for (Song song : filterList) {
            if (song_id == song.getSong_id()) {

                flag = 1;
                url = song.getUathorrl();
            }

        }
        if (flag == 1) {
            PlayActivity play = new PlayActivity();
            play.PlaySongGen(url, filterList);
        } else {
            System.out.println(" That Song is Not Available ");
            System.out.println("Enter 1 to play again 0 to exit");

            int playAgain = scanner.nextInt();
            switch (playAgain) {
                case 1:
                    songByArtist();
                    break;
                case 0:
                    exit();
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    static void songByPlaylist() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Set<String> set = pd.getAllPlaylist(id);
        System.out.println("---Playlist Available--------------");
        for (String s : set) {
            System.out.println(s);
        }
        System.out.println("Enter the Playlist to Filter :");
        String playlist = scanner.next();
        Set<Integer> sond_id = pd.getAllPlaylistSong(playlist, id);
        List<Song> songs = sd.getAllSongs();
        List<Song> filteredlist = new ArrayList<>();
        for (Integer sid : sond_id) {
            for (Song s : songs) {
                if (s.getSong_id() == sid) {
                    filteredlist.add(s);
                }
            }
        }
        System.out.println();
        System.out.format("%-10s   %-25s   %-20s", "Song ID", "Song Name", "Artist Name");
        System.out.println();
        for (Song s : filteredlist) {
            System.out.format("%-10s   %-25s   %-20s", s.getSong_id(), s.getSong_name(), s.getArtist());
            System.out.println();
        }
        System.out.println("Enter id to play song :");
        int Id = scanner.nextInt();
        int flag = 0;
        String url = "";
        for (Song song : filteredlist) {
            if (Id == song.getSong_id()) {

                flag = 1;
                url = song.getUathorrl();
            }

        }
        if (flag == 1) {
            PlayActivity play = new PlayActivity();
            play.PlaySongGen(url, filteredlist);
        } else {
            System.out.println(" That Song is Not Available ");
            System.out.println("Enter 1 to play again 0 to exit");

            int playAgain = scanner.nextInt();
            switch (playAgain) {
                case 1:
                    songByPlaylist();
                    break;
                case 0:
                    exit();
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    static void playPodcast() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        List<Podcast> podcast = podD.getAllPodcasts();
        List<Episode> episodes = epiD.getAllEpisodes();
        List<Episode> epifilter = new ArrayList<>();
        for (Podcast p : podcast) {
            System.out.println(p.getId() + " " + p.getAlbumName());
        }
        System.out.println("Enter Podcast id to be played :");

        int podid = scanner.nextInt();
        for (Episode p : episodes) {
            if (podid == p.getId()) {
                epifilter.add(p);
            }
        }
        System.out.println();
        System.out.format("%-10s   %-25s   %-20s", "Episode ID", "Episode Name", "Artist Name");
        System.out.println();
        for (Episode p : epifilter) {
            System.out.format("%-10s   %-25s   %-20s", p.getEpisodeId(), p.getEpisodeName(), p.getArtistName());
            System.out.println();
        }
        float episodeId = scanner.nextFloat();
        int flag = 0;
        String url = "";
        for (Episode p : epifilter) {
            if (p.getEpisodeId() == episodeId) {

                flag = 1;
                url = p.getUrl();
            }

        }
        if (flag == 1) {
            PlayActivity play = new PlayActivity();
            play.PlayPodcast(url, epifilter);
        } else {
            System.out.println(" That Podcast is Not Available ");
            System.out.println("Enter 1 to play again 0 to exit");
            int playAgain = scanner.nextInt();
            switch (playAgain) {
                case 1:
                    playPodcast();
                    break;
                case 0:
                    exit();
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }


    static void playPodcastbyArtist() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        List<Podcast> podcast = podD.getAllPodcasts();
        Set<String> artists = new TreeSet<>();
        List<Episode> episodes = epiD.getAllEpisodes();
        List<Episode> epifilter = new ArrayList<>();
        for (Podcast p : podcast) {
            // System.out.println(p.getId() + " " + p.getArtistName());
            artists.add(p.getArtistName());
        }

        for (String s : artists) {
            System.out.println(s);
        }
        System.out.println("Enter artist to be played :");
        String artistName = scanner.next();
        for (Episode p : episodes) {
            if (artistName.equalsIgnoreCase(p.getArtistName())) {
                epifilter.add(p);
            }
        }

        for (Episode p : epifilter) {
            System.out.println(p.getEpisodeId() + " " + p.getEpisodeName());
        }
        System.out.println("Enter Episode id to Play");
        float episodeId = scanner.nextFloat();
        int flag = 0;
        String url = "";
        for (Episode p : epifilter) {
            if (p.getEpisodeId() == episodeId) {

                flag = 1;
                url = p.getUrl();
            }

        }

        if (flag == 1) {
            PlayActivity play = new PlayActivity();
            play.PlayPodcast(url, epifilter);
        } else {
            System.out.println(" That Podcast is Not Available ");
            System.out.println("Enter 1 to play again 0 to exit");

            int playAgain = scanner.nextInt();
            switch (playAgain) {
                case 1:
                    playPodcastbyArtist();
                    break;
                case 0:
                    exit();
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    static void PodcastSearch() throws UnsupportedAudioFileException, LineUnavailableException, IOException, SQLException, ClassNotFoundException {
        System.out.println("Search By ");
        System.out.println("1. Artist");
        System.out.println("2. Playlist");
        System.out.println("3. Go Back");
        System.out.println("3. Exit");
        int songSearch;
        songSearch = scanner.nextInt();
        switch (songSearch) {
            case 1:
                playPodcastbyArtist();
                break;
            case 2:
                podcastByPlaylist();
                break;
            case 3:
                podOperations();
                break;
            case 4:
                exit();
                break;
        }

    }

    static void podcastByPlaylist() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Set<String> set = pd.getAllPlaylistPodcast();
        for (String s : set) {
            System.out.println(s);
        }
        System.out.println("Enter the Playlist to Filter :");
        String playlist = scanner.next();
        Set<Float> episodesList = pd.getAllPlaylistEpisodes(playlist, id);
        List<Episode> episodes = epiD.getAllEpisodes();
        List<Episode> filteredlist = new ArrayList<>();
        for (Float sid : episodesList) {
            for (Episode s : episodes) {
                if (s.getEpisodeId() == sid) {
                    filteredlist.add(s);
                }
            }
        }
        for (Episode s : filteredlist) {
            System.out.println(s.getEpisodeId() + "   " + s.getEpisodeName());
        }
        System.out.println("Enter id to play podcast :");
        float Id = scanner.nextFloat();

        int flag = 0;
        String url = "";
        for (Episode song : filteredlist) {


            if (Id == song.getEpisodeId()) {
                url = song.getUrl();
                flag = 1;
            }


        }

        if (flag == 1) {
            PlayActivity play = new PlayActivity();
            play.PlayPodcast(url, filteredlist);
        } else {
            System.out.println(" That Podcast is Not Available ");
            System.out.println("Enter 1 to play again 0 to exit");

            int playAgain = scanner.nextInt();
            switch (playAgain) {
                case 1:
                    podcastByPlaylist();
                    break;
                case 0:
                    exit();
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    public static void addPodcastToPlayList() throws UnsupportedAudioFileException, LineUnavailableException, IOException, SQLException, ClassNotFoundException {
        System.out.println("Playlist :");
        System.out.println("1) Add to existing Playlist");
        System.out.println("2) Add to new Playlist");
        System.out.println("3) Go Back ");
        System.out.println("4) Exit");
        int playlistchoice = scanner.nextInt();
        switch (playlistchoice) {
            case 1:
                Set<String> playlist = pd.getAllPlaylistPodcast();
                for (String s : playlist) {
                    System.out.println(s);
                }
                System.out.println("Enter Playlist to add :");
                String playlistName = scanner.next();
                List<Episode> ep = epiD.getAllEpisodes();
                for (Episode s : ep) {
                    System.out.println(s.getEpisodeId() + " " + s.getEpisodeName());
                }
                int i = 0;
                while (i == 0) {
                    System.out.println("Enter ID to Add To PlayList");
                    float sid = scanner.nextFloat();
                    for (Episode s : ep) {
                        if (sid == s.getEpisodeId()) {
                            pd.addPodcastToPlayList(sid, id, 2, playlistName);
                        }
                    }
                    System.out.println("Enter 0 to continue else Enter anything else ");
                    int flag = scanner.nextInt();
                    i = flag;
                }
                podOperations();
                break;
            case 2:
                System.out.println("Enter new Playlist Name");
                String playnewList = scanner.next();
                List<Episode> episodes = epiD.getAllEpisodes();
                for (Episode eps : episodes) {
                    System.out.println(eps.getEpisodeId() + " " + eps.getEpisodeName());
                }
                int j = 0;
                while (j == 0) {
                    System.out.println("Enter ID to Add To PlayList");
                    float sid = scanner.nextFloat();
                    for (Episode s : episodes) {
                        if (sid == s.getEpisodeId()) {
                            pd.addPodcastToPlayList(sid, id, 2, playnewList);
                        }
                    }
                    System.out.println("Enter 0 to continue else Enter anything else ");
                    int flag = scanner.nextInt();
                    j = flag;
                }
                podOperations();
                break;
            case 3:
                podOperations();
                break;
            case 4:
                exit();
                break;
        }
    }

    static void exit() {

        System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");

        System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");

        System.exit(0);
    }

}