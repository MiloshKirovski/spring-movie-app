package mk.finki.ukim.wp.lab.Repository.inmemory;

import mk.finki.ukim.wp.lab.bootstrap.DataHolder;
import mk.finki.ukim.wp.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LocationRepository {
    public List<Location> findAll() {
        return DataHolder.locations;
    }

    public Optional<Location> findLocation(Long id) {
        return DataHolder.locations.stream().filter(i -> i.getId().equals(id)).findFirst();
    }
}
