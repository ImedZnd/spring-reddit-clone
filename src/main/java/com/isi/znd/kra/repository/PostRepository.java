package com.isi.znd.kra.repository;

import com.isi.znd.kra.model.Post;
import com.isi.znd.kra.model.Subreddit;
import com.isi.znd.kra.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}