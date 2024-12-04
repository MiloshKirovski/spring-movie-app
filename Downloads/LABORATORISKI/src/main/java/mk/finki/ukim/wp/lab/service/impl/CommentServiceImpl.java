package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.Repository.jpa.CommentRepositoryJpa;
import mk.finki.ukim.wp.lab.model.Comment;
import mk.finki.ukim.wp.lab.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepositoryJpa commentRepositoryJpa;

    public CommentServiceImpl(CommentRepositoryJpa commentRepositoryJpa) {
        this.commentRepositoryJpa = commentRepositoryJpa;
    }

    @Override
    public Comment addComment(Comment comment) {
        return commentRepositoryJpa.save(comment);
    }
}
