package com.music.providers;

import com.database.DataBase;
import com.music.ressources.Music;
import com.user.providers.UserProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MusicProvider {

    private Connection connection;

    public MusicProvider() throws Exception {
        this.connection =  DataBase.getConnection();
    }

    public List<Music> getAllMusicsByArtist(int id) throws Exception{
        String query = "SELECT * FROM Music WHERE music_artist='%1$d';";
        List<Music> allMusics = new ArrayList<Music>();
        UserProvider userProvider = new UserProvider();
        ResultSet result = connection.createStatement().executeQuery(String.format(query, id));
        while (result.next()){
            allMusics.add(new Music(result.getInt("music_id"),result.getString("music_title"),result.getLong("music_duration"),userProvider.findUserById(result.getInt("music_artist")),result.getInt("music_style"),result.getString("music_uri")));
        }
        return allMusics;
    }

    public Music findMusicById(int id) throws Exception{
        String query = "SELECT * FROM Music WHERE music_id='%1$d';";
        UserProvider userProvider = new UserProvider();
        ResultSet result = connection.createStatement().executeQuery(String.format(query, id));
        if (result.first()){
            return new Music(result.getInt("music_id"),result.getString("music_title"),result.getLong("music_duration"),userProvider.findUserById(result.getInt("music_artist")),result.getInt("music_style"),result.getString("music_uri"));
        }
        return null;
    }

    public boolean addMusic(Music music) throws Exception{
        String query = "INSERT INTO Music (music_title,music_style,music_duration,music_artist,music_uri) VALUES ('%1$s','%2$d','%3$d','%4$d','%5$s');" ;
        int result = connection.createStatement().executeUpdate(query.format(query, music.getTitle(),music.getMusicType(),music.getDuration(),music.getArtist().getId(),music.getUri()));
        return result > 0 ? true : false;
    }

    public boolean UpdateMusicById(Music music) throws Exception{
        String query = "UPDATE Music SET music_title='%2$s',music_style='%3$s',music_duration='%4$d',music_artist='%5$d',music_uri='%6$s' WHERE music_id='%1$d';";
        int result = connection.createStatement().executeUpdate(query.format(query,music.getId(),music.getTitle(),music.getMusicType(),music.getDuration(),music.getArtist().getId(),music.getUri()));
        return result > 0 ? true : false ;
    }

    public boolean deleteMusicById(int id) throws Exception{
        String query = "DELETE FROM Music WHERE music_id='%1$d';";
        int result = connection.createStatement().executeUpdate(query.format(query,id));
        return result > 0 ? true : false ;
    }



}
