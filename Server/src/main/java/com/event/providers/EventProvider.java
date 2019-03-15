package com.event.providers;

import com.database.DataBase;
import com.event.ressources.Event;
import com.user.providers.UserProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EventProvider {

    private Connection connection;

    public EventProvider() throws Exception {
        this.connection = DataBase.getConnection();
    }

    public List<Event> getAllEvents() throws Exception{
        String query = "SELECT * FROM Event;";
        List<Event> allEvents = new ArrayList<Event>();
        UserProvider userProvider = new UserProvider();
        ResultSet result = connection.createStatement().executeQuery(query);
        while (result.next()){
            allEvents.add(new Event(result.getInt("event_id"), result.getString("event_name"), userProvider.findUserById(result.getInt("event_user")), result.getDate("event_date") , result.getLong("event_longitude"), result.getLong("event_latitude")));
        }
        return allEvents;
    }

    public Event findEventById(int id) throws Exception{
        String query = "SELECT * FROM Event WHERE event_id='%1$d';";
        ResultSet result = connection.createStatement().executeQuery(String.format(query, id));
        if (result.first()){
            UserProvider userProvider = new UserProvider();
            return new Event(result.getInt("event_id"), result.getString("event_name"), userProvider.findUserById(result.getInt("event_user")), result.getDate("event_date") , result.getLong("event_longitude"), result.getLong("event_latitude"));
        }
        return null;
    }

    public boolean addEvent(Event event) throws Exception{
        String query = "INSERT INTO Event (event_name,event_user,event_date,event_longitude,event_latitude) VALUES ('%1$s','%2$d','%3$d','%4$l',%5$l);" ;
        int result = connection.createStatement().executeUpdate(query.format(query, event.getName(), event.getUser().getId(), event.getDate(), event.getLongitude(), event.getLatitude()));
        return result > 0 ? true : false ;
    }

    public boolean deleteEvent(int  id) throws Exception{
        String query = "DELETE FROM Event WHERE event_id='%1$d'";
        int result = connection.createStatement().executeUpdate(query.format(query,id));
        return result > 0 ? true : false ;
    }


}
