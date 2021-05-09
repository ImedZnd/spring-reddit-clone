package com.isi.znd.kra.service;

import com.isi.znd.kra.dto.CommentsDto;
import com.isi.znd.kra.mapper.CommentMapper;
import com.isi.znd.kra.model.Comment;
import com.isi.znd.kra.model.NotificationEmail;
import com.isi.znd.kra.model.Post;
import com.isi.znd.kra.model.User;
import com.isi.znd.kra.model.exeptions.PostNotFoundException;
import com.isi.znd.kra.repository.CommentRepository;
import com.isi.znd.kra.repository.PostRepository;
import com.isi.znd.kra.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CommentService {
    private static final String POST_URL = "";
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    public void save(CommentsDto commentsDto){
        Post post = postRepository.findById(commentsDto.getId()).
                orElseThrow(()-> new PostNotFoundException(commentsDto.getId().toString()));
        Comment comment = commentMapper.map(commentsDto , post,authService.getCurrentUser());
        commentRepository.save(comment);
        String message = mailContentBuilder.build(post.getUser().getUsername()+"posted a comment on you post"+POST_URL);
        sendCommentNotification(message,post.getUser());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername()+"commented on you post",user.getEmail(),message));
    }

    public List<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
    }

    public List<CommentsDto> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException(userName));

        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(toList());
        }
}
