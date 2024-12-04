package mk.finki.ukim.wp.lab.Repository.jpa;

import mk.finki.ukim.wp.lab.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepositoryJpa extends JpaRepository<Comment, Long> {
}
