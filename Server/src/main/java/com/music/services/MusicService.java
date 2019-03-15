package com.music.services;

import com.music.providers.MusicProvider;
import com.music.ressources.Music;
import com.user.providers.UserProvider;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/restapi/music")
public class MusicService {

    @GET
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMusics(String musicJson) {
        try{
            JSONObject jsonMusic = new JSONObject(musicJson);
            return Response.ok(new MusicProvider().getAllMusicsByArtist(jsonMusic.getInt("artist"))).build();
        } catch (Exception e){
            return Response.status(404).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findMusic(@PathParam("id") int id) {
        try {
            Music music = new MusicProvider().findMusicById(id);
            if (music == null){
                return Response.status(400).build();
            }
            return Response.ok(music).build();
        } catch (Exception e){
            return Response.status(404).build();
        }
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMusic (String musicJson){
        try {
            JSONObject jsonMusic = new JSONObject(musicJson);
            MusicProvider musicProvider = new MusicProvider();
            UserProvider userProvider = new UserProvider();
            Music music = new Music(jsonMusic.getInt("id"),jsonMusic.getString("title"),jsonMusic.getLong("duration"),userProvider.findUserById(jsonMusic.getInt("artist")),jsonMusic.getInt("style"),jsonMusic.getString("uri"));
            musicProvider.addMusic(music);
            return Response.status(201).entity(music).build();
        }catch (Exception e){
            return Response.status(404).build();
        }
    }

    @POST
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMusic (@PathParam("id") int id){
        try {
            new MusicProvider().deleteMusicById(id);
            return Response.ok().build();
        }catch (Exception e){
            return Response.status(404).build();
        }
    }

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON )
    public Response updateMusic (String musicJson){
        try {
            JSONObject jsonMusic = new JSONObject(musicJson);
            MusicProvider musicProvider = new MusicProvider();
            UserProvider userProvider = new UserProvider();
            Music music = new Music(jsonMusic.getInt("id"),jsonMusic.getString("title"),jsonMusic.getLong("duration"),userProvider.findUserById(jsonMusic.getInt("artist")),jsonMusic.getInt("style"),jsonMusic.getString("uri"));
            musicProvider.UpdateMusicById(music);
            return Response.ok(music).build();
        }catch (Exception e){
            return Response.status(404).build();
        }
    }
}
