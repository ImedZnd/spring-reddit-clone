package com.isi.znd.kra.repository;

import com.isi.znd.kra.model.Comment;
import com.isi.znd.kra.model.Post;
import com.isi.znd.kra.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}