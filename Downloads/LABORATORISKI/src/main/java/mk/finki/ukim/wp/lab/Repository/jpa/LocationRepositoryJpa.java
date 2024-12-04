package mk.finki.ukim.wp.lab.Repository.jpa;

import mk.finki.ukim.wp.lab.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepositoryJpa extends JpaRepository<Location, Long> {

}
