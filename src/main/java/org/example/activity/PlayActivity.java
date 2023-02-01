package org.example.activity;


import org.example.DaoImpl.SongDaoImpl;
import org.example.DaoInterface.SongDao;
import org.example.ModelClass.Episode;
import org.example.ModelClass.Song;
import org.example.constants.Constant;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class PlayActivity {


    public void PlaySong(String url, List<Song> songslist) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        Scanner scanner = new Scanner(System.in);
        try {
            String path = url;
            File file = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            String response = "";

            while (!response.equals("Q")) {
                System.out.println("P = play , T= Pause, S=Stop, L=Loop, F=Forward, B=Reverse, R = Reset, N=Next Song Q = Quit,RE Remaining time,NE NEXT ,PE PRIVIOUS");    /*N=Next Song,*/
                System.out.println("");
                System.out.print("Enter your choice: ");

                response = scanner.next();
                response = response.toUpperCase();

                switch (response) {
                    case (Constant.PLAY): {
                        clip.start();
                        long clip_position = clip.getMicrosecondPosition();
                        long milliseconds = clip_position / 1000;
                        System.out.println("Clip played from: " + milliSecondsToTimer(milliseconds));
                        System.out.println("Songs in queue: ");
                        SongsLeft(url, songslist);
                        break;
                    }
                    case (Constant.PAUSE): {
                        clip.stop();
                        long clip_position = clip.getMicrosecondPosition();
                        long milliseconds = clip_position / 1000;
                        System.out.println("Clip stopped at: " + milliSecondsToTimer(milliseconds));
                        break;
                    }
                    case (Constant.STOP): {
                        clip.setMicrosecondPosition(0);
                        clip.stop();
                        break;
                    }
                    case (Constant.LOOP): {
                        clip.start();
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    }
                    case (Constant.FORWARD_10SEC): {
                        long duration = clip.getFrameLength();

                        long clip_position = clip.getMicrosecondPosition();
                        if ((duration - clip_position) > 10000000) {
                            clip.setMicrosecondPosition(clip.getMicrosecondPosition() + 10000000);
                            clip.start();

                        }
                        long milliseconds = clip.getMicrosecondPosition() / 1000;
                        System.out.println("Clip played from: " + milliSecondsToTimer(milliseconds));
                        break;
                    }
                    case (Constant.REVERSE_10SEC): {
                        clip.start();
                        long clip_position = clip.getMicrosecondPosition();
                        if (clip_position < 10000000) {
                            clip.setMicrosecondPosition(0);
                        } else {
                            clip.setMicrosecondPosition(clip_position - 10000000);
                        }
                        clip.start();
                        long milliseconds = clip_position / 1000;
                        System.out.println("Clip played from: " + milliSecondsToTimer(milliseconds));
                        break;
                    }
                    case (Constant.RESET):
                        clip.setMicrosecondPosition(0);
                        break;
                    case (Constant.ANY_SONG): {

                        SongsLeft(url, songslist);

                        System.out.println("Choose the song to be played next: ");
                        scanner.nextLine();
                        int songId = scanner.nextInt();
                        SongDao dao = new SongDaoImpl();
                        String pathofSong = "";

                        for (Song s :
                                songslist) {
                            if (s.getSong_id() == songId) {
                                pathofSong = s.getUathorrl();
                            }

                        }
                        clip.stop();
                        PlaySong(pathofSong, songslist);
                        break;
                    }
                    case (Constant.QUIT):
                        clip.close();
                        MainActivity.SongOperations();
                        break;
                    case (Constant.REMAIN_TIME):
                        long tot = clip.getMicrosecondLength();
                        long micro = clip.getMicrosecondPosition();//microseconds to
                        System.out.println(clip.getMicrosecondLength() / 1000000);
                        System.out.println("played in seconds:" + micro / 1000000);
                        System.out.println("remaining time for this song:" + (tot - micro) / 1000000);
                        break;
                    case (Constant.NEXT):
                        clip.close();
                        PlayActivity.nextSong(url, songslist, clip);
                        break;
                    case (Constant.BACK):
                        clip.close();
                        PlayActivity.previousSong(url, songslist, clip);

                        break;
                    default:
                        System.out.println("Not a valid response");
                }
            }
        } catch (Exception e) {
            System.out.println("That Song is not available try again with listed id");
        }

    }


    public static String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        if (hours > 0) {
            finalTimerString = hours + ":";
        }
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }
        finalTimerString = finalTimerString + minutes + ":" + secondsString;
        return finalTimerString;
    }


    public static void SongsLeft(String song_name, List<Song> songslist) {
        System.out.println();
        System.out.format("%-10s   %-25s   %-20s", "Song ID", "Song Name", "Artist Name");
        System.out.println();
        songslist.stream().filter(a -> !a.getSong_name().equals(song_name)).forEach(b ->
        {
            System.out.format("%-10s   %-25s   %-20s", b.getSong_id(), b.getSong_name(), b.getArtist());
            System.out.println();
        });
    }


    public static void EpisodesLeft(String episode_name, List<Episode> epislist) {
        System.out.println();
        System.out.format("%-10s   %-25s   %-20s", "Episode ID", "Episode Name", "Artist Name");
        System.out.println();
        epislist.stream().filter(a -> !a.getEpisodeName().equals(episode_name)).forEach(b ->

        {
            System.out.format("%-10s   %-25s   %-20s", b.getEpisodeId(), b.getEpisodeName(), b.getArtistName());
            System.out.println();
        });
    }


    public void PlaySongGen(String url, List<Song> songslist) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        Scanner scanner = new Scanner(System.in);
        try {
            String path = url;
            File file = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            String response = "";

            while (!response.equals("Q")) {
                System.out.println("P = play, T= Pause, S=Stop, L=Loop, F=Forward, B=Reverse, R = Reset, N=Next Song Q = Quit,RE =Remaing Time,ne,pe");    /*N=Next Song,*/
                System.out.print("Enter your choice: ");

                response = scanner.next();
                response = response.toUpperCase();

                switch (response) {
                    case ("P"): {
                        clip.start();
                        long clip_position = clip.getMicrosecondPosition();
                        long milliseconds = clip_position / 1000;
                        System.out.println("Clip played from: " + milliSecondsToTimer(milliseconds));
                        System.out.println("Songs in queue: ");
                        SongsLeft(url, songslist);
                        break;
                    }
                    case ("T"): {
                        clip.stop();
                        long clip_position = clip.getMicrosecondPosition();
                        long milliseconds = clip_position / 1000;
                        System.out.println("Clip stopped at: " + milliSecondsToTimer(milliseconds));
                        break;
                    }
                    case ("S"): {
                        clip.setMicrosecondPosition(0);
                        clip.stop();
                        break;
                    }
                    case ("L"): {
                        clip.start();
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    }
                    case ("F"): {
                        long duration = clip.getFrameLength();

                        long clip_position = clip.getMicrosecondPosition();
                        if ((duration - clip_position) > 10000000) {
                            clip.setMicrosecondPosition(clip.getMicrosecondPosition() + 10000000);
                            clip.start();

                        }
                        long milliseconds = clip.getMicrosecondPosition() / 1000;
                        System.out.println("Clip played from: " + milliSecondsToTimer(milliseconds));
                        break;
                    }
                    case ("B"): {
                        clip.start();
                        long clip_position = clip.getMicrosecondPosition();
                        if (clip_position < 10000000) {
                            clip.setMicrosecondPosition(0);
                        } else {
                            clip.setMicrosecondPosition(clip_position - 10000000);
                        }
                        clip.start();
                        long milliseconds = clip_position / 1000;
                        System.out.println("Clip played from: " + milliSecondsToTimer(milliseconds));
                        break;
                    }
                    case ("R"):
                        clip.setMicrosecondPosition(0);
                        break;
                    case ("N"): {
                        SongsLeft(url, songslist);
                        System.out.println("Choose the song to be played next: ");
                        scanner.nextLine();
                        int songId = scanner.nextInt();
                        SongDao dao = new SongDaoImpl();
                        String pathofSong = "";

                        for (Song s :
                                songslist) {
                            if (s.getSong_id() == songId) {
                                pathofSong = s.getUathorrl();
                            }
                        }
                        clip.stop();
                        PlaySongGen(pathofSong, songslist);
                        break;
                    }
                    case ("Q"):
                        clip.close();
                        MainActivity.songSearch();
                        break;
                    case ("RE"):
                        long tot = clip.getMicrosecondLength();
                        long micro = clip.getMicrosecondPosition();//microseconds to
                        System.out.println(clip.getMicrosecondLength() / 1000000);
                        System.out.println("played in seconds:" + micro / 1000000);
                        System.out.println("remaining time for this song:" + (tot - micro) / 1000000);
                        break;
                    case ("NE"):
                        clip.close();
                        PlayActivity.nextSongGen(url, songslist, clip);
                        break;
                    case ("PE"):
                        clip.close();
                        PlayActivity.previousSongGen(url, songslist, clip);

                        break;
                    default:
                        System.out.println("Not a valid response");
                }
            }
        } catch (Exception e) {
            System.out.println("That Song is not available try again with listed id");
        }

    }


    public void PlayPodcast(String url, List<Episode> songslist) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        Scanner scanner = new Scanner(System.in);
        try {
            String path = url;
            File file = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            String response = "";

            while (!response.equals("Q")) {
                System.out.println("P = play, T= Pause, S=Stop, L=Loop, F=Forward, B=Reverse, R = Reset, N=Next Song Q = Quit, RE = Remaining time,ne,pe");    /*N=Next Song,*/
                System.out.print("Enter your choice: ");

                response = scanner.next();
                response = response.toUpperCase();

                switch (response) {
                    case ("P"): {
                        clip.start();
                        long clip_position = clip.getMicrosecondPosition();
                        long milliseconds = clip_position / 1000;
                        System.out.println("Clip played from: " + milliSecondsToTimer(milliseconds));
                        System.out.println("Songs in queue: ");
                        EpisodesLeft(url, songslist);
                        break;
                    }
                    case ("T"): {
                        clip.stop();
                        long clip_position = clip.getMicrosecondPosition();
                        long milliseconds = clip_position / 1000;
                        System.out.println("Clip stopped at: " + milliSecondsToTimer(milliseconds));
                        break;
                    }
                    case ("S"): {
                        clip.setMicrosecondPosition(0);
                        clip.stop();
                        break;
                    }
                    case ("L"): {
                        clip.start();
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                    }
                    case ("F"): {
                        long duration = clip.getFrameLength();

                        long clip_position = clip.getMicrosecondPosition();
                        if ((duration - clip_position) > 10000000) {
                            clip.setMicrosecondPosition(clip.getMicrosecondPosition() + 10000000);
                            clip.start();

                        }
                        long milliseconds = clip.getMicrosecondPosition() / 1000;
                        System.out.println("Clip played from: " + milliSecondsToTimer(milliseconds));
                        break;
                    }
                    case ("B"): {
                        clip.start();
                        long clip_position = clip.getMicrosecondPosition();
                        if (clip_position < 10000000) {
                            clip.setMicrosecondPosition(0);
                        } else {
                            clip.setMicrosecondPosition(clip_position - 10000000);
                        }
                        clip.start();
                        long milliseconds = clip_position / 1000;
                        System.out.println("Clip played from: " + milliSecondsToTimer(milliseconds));
                        break;
                    }
                    case ("R"):
                        clip.setMicrosecondPosition(0);
                        break;
                    case ("N"): {
                        EpisodesLeft(url, songslist);
                        System.out.println("Choose the song to be played next: ");
                        scanner.nextLine();
                        float songId = scanner.nextFloat();
                        SongDao dao = new SongDaoImpl();
                        String pathofSong = "";

                        for (Episode s :
                                songslist) {
                            if (s.getEpisodeId() == songId) {
                                pathofSong = s.getUrl();
                            }
                        }
                        clip.stop();
                        PlayPodcast(pathofSong, songslist);
                        break;
                    }
                    case ("RE"):
                        long tot = clip.getMicrosecondLength();
                        long micro = clip.getMicrosecondPosition();//microseconds to
                        System.out.println(clip.getMicrosecondLength() / 1000000);
                        System.out.println("played in seconds:" + micro / 1000000);
                        System.out.println("remaining time for this podcast:" + (tot - micro) / 1000000);
                        break;
                    case ("Q"):
                        clip.close();
                        MainActivity.podOperations();
                        break;
                    case ("NE"):
                        clip.close();
                        PlayActivity.nextPod(url, songslist, clip);
                        break;
                    case ("PE"):
                        clip.close();
                        PlayActivity.previousPod(url, songslist, clip);

                        break;
                    default:
                        System.out.println("Not a valid response");
                }
            }
        } catch (Exception e) {
            System.out.println("That Podcast is not available try again with listed id");
        }

    }


    static void nextSong(String url, List<Song> song, Clip clip) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        LinkedList<Song> songs = new LinkedList<>(song);
        ListIterator<Song> iterator = songs.listIterator();

        for (Song s : songs) {
            iterator.next();
            if (s.getUathorrl().equalsIgnoreCase(url)) {

                if (iterator.hasNext()) {

                    PlayActivity activity = new PlayActivity();
                    activity.PlaySong(iterator.next().getUathorrl(), songs);
                    clip.start();
                } else {
                    iterator = songs.listIterator();
                    if (iterator.hasNext()) {
                        PlayActivity activity = new PlayActivity();
                        activity.PlaySong(iterator.next().getUathorrl(), songs);
                        clip.start();
                    }
                }
            }
        }
    }


    static void previousSong(String url, List<Song> song, Clip clip) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        LinkedList<Song> songs = new LinkedList<>(song);
        ListIterator<Song> iterator = songs.listIterator();

        for (Song s : songs) {

            iterator.next();
            if (s.getUathorrl().equalsIgnoreCase(url)) {
                iterator.previous();

                if (iterator.hasPrevious()) {

                    PlayActivity activity = new PlayActivity();
                    activity.PlaySong(iterator.previous().getUathorrl(), songs);
                    clip.start();
                } else {
                    PlayActivity activity = new PlayActivity();
                    activity.PlaySong(songs.getLast().getUathorrl(), songs);
                    clip.start();
                }

            }

        }
    }

    static void nextSongGen(String url, List<Song> song, Clip clip) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        LinkedList<Song> songs = new LinkedList<>(song);
        ListIterator<Song> iterator = songs.listIterator();

        for (Song s : songs) {
            iterator.next();
            if (s.getUathorrl().equalsIgnoreCase(url)) {

                if (iterator.hasNext()) {

                    PlayActivity activity = new PlayActivity();
                    activity.PlaySong(iterator.next().getUathorrl(), songs);
                    clip.start();
                } else {
                    iterator = songs.listIterator();
                    if (iterator.hasNext()) {
                        PlayActivity activity = new PlayActivity();
                        activity.PlaySongGen(iterator.next().getUathorrl(), songs);
                        clip.start();
                    }
                }
            }
        }
    }


    static void previousSongGen(String url, List<Song> song, Clip clip) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        LinkedList<Song> songs = new LinkedList<>(song);
        ListIterator<Song> iterator = songs.listIterator();

        for (Song s : songs) {

            iterator.next();
            if (s.getUathorrl().equalsIgnoreCase(url)) {
                iterator.previous();

                if (iterator.hasPrevious()) {

                    PlayActivity activity = new PlayActivity();
                    activity.PlaySong(iterator.previous().getUathorrl(), songs);
                    clip.start();
                } else {
                    PlayActivity activity = new PlayActivity();
                    activity.PlaySongGen(songs.getLast().getUathorrl(), songs);
                    clip.start();
                }

            }

        }
    }

    static void nextPod(String url, List<Episode> song, Clip clip) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        LinkedList<Episode> songs = new LinkedList<>(song);
        ListIterator<Episode> iterator = songs.listIterator();

        for (Episode s : songs) {
            iterator.next();
            if (s.getUrl().equalsIgnoreCase(url)) {

                if (iterator.hasNext()) {

                    PlayActivity activity = new PlayActivity();
                    activity.PlayPodcast(iterator.next().getUrl(), songs);
                    clip.start();
                } else {
                    iterator = songs.listIterator();
                    if (iterator.hasNext()) {
                        PlayActivity activity = new PlayActivity();
                        activity.PlayPodcast(iterator.next().getUrl(), songs);
                        clip.start();
                    }
                }
            }
        }
    }


    static void previousPod(String url, List<Episode> song, Clip clip) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        LinkedList<Episode> songs = new LinkedList<>(song);
        ListIterator<Episode> iterator = songs.listIterator();

        for (Episode s : songs) {

            iterator.next();
            if (s.getUrl().equalsIgnoreCase(url)) {
                iterator.previous();

                if (iterator.hasPrevious()) {

                    PlayActivity activity = new PlayActivity();
                    activity.PlayPodcast(iterator.previous().getUrl(), songs);
                    clip.start();
                } else {
                    PlayActivity activity = new PlayActivity();
                    activity.PlayPodcast(songs.getLast().getUrl(), songs);
                    clip.start();
                }

            }

        }
    }
}
