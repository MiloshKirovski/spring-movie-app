package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.Repository.inmemory.LocationRepository;
import mk.finki.ukim.wp.lab.Repository.jpa.LocationRepositoryJpa;
import mk.finki.ukim.wp.lab.model.Location;
import mk.finki.ukim.wp.lab.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepositoryJpa inMemoryLocationRepository;

    public LocationServiceImpl(LocationRepositoryJpa inMemoryLocationRepository) {
        this.inMemoryLocationRepository = inMemoryLocationRepository;
    }

    @Override
    public List<Location> findAll() {
        return inMemoryLocationRepository.findAll();
    }
}
