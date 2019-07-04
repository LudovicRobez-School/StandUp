package com.user.services;

import com.user.providers.UserProvider;
import com.user.ressources.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/restapi/user")
public class UserService {

    private final UserProvider userProvider;

    @Autowired
    public UserService(UserProvider userProvider) {
        this.userProvider = userProvider;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON )
    public Response findUser(@PathParam("id") int id) {
        try {
            User user = new UserProvider().findUserById(id);
            if (user == null){
                return Response.status(400).build();
            }
            return Response.ok(user).build();
        } catch (Exception e){
            return Response.status(404).build();
        }
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON )
    public Response addUser (String userJson){
        try {
            JSONObject jsonUser = new JSONObject(userJson);
            UserProvider userProvider = new UserProvider();
            User user = new User(jsonUser.getString("email") ,jsonUser.getString("password"),jsonUser.getString("username"),jsonUser.getString("firstName"), jsonUser.getString("lastName"));
            userProvider.addUser(user);
            return Response.status(201).entity(user).build();
        }catch (Exception e){
            return Response.status(404).build();
        }
    }

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON )
    public Response updateUser (String userJson){
        try {
            JSONObject jsonUser = new JSONObject(userJson);
            UserProvider userProvider = new UserProvider();
            User user = new User(jsonUser.getString("email") ,jsonUser.getString("password"),jsonUser.getString("username"),jsonUser.getString("firstName"), jsonUser.getString("lastName"));
            userProvider.UpdateUserById(user);
            return Response.ok(user).build();
        }catch (Exception e){
            return Response.status(404).build();
        }
    }

}
