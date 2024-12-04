package mk.finki.ukim.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.wp.lab.model.Event;
import mk.finki.ukim.wp.lab.model.EventBooking;
import mk.finki.ukim.wp.lab.model.Location;
import mk.finki.ukim.wp.lab.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> events = new ArrayList<>();
    public static List<EventBooking> eventBookings = new ArrayList<>();

    public static List<Location> locations = new ArrayList<>();
    public static List<User> users = new ArrayList<>();


    @PostConstruct
    public void init() {
        locations.add(new Location("FavoriteCoffee", "Ruzveltova 2", "10", "A coffee place"));
        locations.add(new Location("FavoriteBurger", "Partizanski Odresi 23", "15", "A burger place"));
        locations.add(new Location("FavoritePizza", "Aleksandar Makedonski 3", "20", "A pizza place"));
        locations.add(new Location("FavoriteBeer", "Teodosij Gologanov", "25", "A beer place"));
        locations.add(new Location("FavoriteIceCream", "Ilinden", "30", "A ice cream place"));


        for (int i = 0; i < 10; i++) {
            events.add(new Event("name" + i, "description" + i, i, i, locations.get(i%locations.size())));
        }

        users.add(new User("milosh.kirovski", "mk", "Milosh", "Kirovski"));
        users.add(new User("riste.stojanov", "rs", "Riste", "Stojanov"));
    }
}
