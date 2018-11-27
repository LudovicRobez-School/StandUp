package com.event.services;


import com.event.controllers.EventController;
import com.event.models.Event;
import com.user.controllers.UserController;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.List;

@Path("/restapi/event")
public class EventService {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON )
    public Response GetAllEvents(){
        try{
            return Response.ok(new EventController().getAllEvents()).build();
        } catch (Exception e){
            return Response.status(404).build();
        }
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON )
    public Response GetEvent (@PathParam("id") int id){
        try{
            Event event = new EventController().findEventById(id);
            if (event == null){
                return Response.status(400).build();
            }
            return Response.ok(event).build();
        } catch (Exception e){
            return Response.status(404).build();
        }
    }


    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON )
    public Response addEvent(String eventJson){
        try{
            JSONObject jsonEvent = new JSONObject(eventJson);
            EventController eventProvider = new EventController();
            UserController userProvider = new UserController();
            Event event = new Event(jsonEvent.getString("name") ,userProvider.findUserbyId(jsonEvent.getInt("userId")),new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(jsonEvent.getString("date")),jsonEvent.getLong("longitude"),jsonEvent.getLong("latitude"));
            eventProvider.addEvent(event);
            return Response.status(202).entity(event).build();
        } catch (Exception e){
            return Response.status(404).build();
        }
    }

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON )
    public Response deleteEvent(String eventJson){
        try{
            JSONObject jsonEvent = new JSONObject(eventJson);
            EventController eventProvider = new EventController();
            UserController userProvider = new UserController();
            Event event = new Event(jsonEvent.getString("name") ,userProvider.findUserbyId(jsonEvent.getInt("userId")),new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").parse(jsonEvent.getString("date")),jsonEvent.getLong("longitude"),jsonEvent.getLong("latitude"));
            eventProvider.deleteEvent(event);
            return Response.ok(event).build();
        } catch (Exception e){
            return Response.status(404).build();
        }
    }
}
