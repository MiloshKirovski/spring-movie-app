package mk.finki.ukim.wp.lab.Repository.jpa;

import mk.finki.ukim.wp.lab.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepositoryJpa extends JpaRepository<Event, Long> {
    List<Event> findEventByDescriptionContainingOrNameContaining(String text1, String text2);
    Optional<Event> findEventByName(String name);
    Optional<Event> deleteByName(String name);
    List<Event> findAllByLocation_Id(Long locationId);
}
