package com.isi.znd.kra.repository;

import com.isi.znd.kra.model.Post;
import com.isi.znd.kra.model.User;
import com.isi.znd.kra.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}