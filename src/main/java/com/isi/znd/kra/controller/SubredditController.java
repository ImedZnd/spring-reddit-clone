package com.isi.znd.kra.controller;


import com.isi.znd.kra.dto.SubredditDto;
import com.isi.znd.kra.service.SubredditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class SubredditController {
    private final SubredditService subredditService;

    @PostMapping
    public ResponseEntity<SubredditDto> createSubreddit(@RequestBody SubredditDto subredditDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subredditService.save(subredditDto));
    }

    @GetMapping
    public ResponseEntity<List<SubredditDto>> getAllSubreddits() {
        ResponseEntity<List<SubredditDto>> x =  ResponseEntity
                .status(HttpStatus.OK)
                .body(subredditService.getAll());
        System.out.println("getAllSubreddits()");
        return x;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubredditDto> getSubreddits(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subredditService.getSubreddit(id));
    }


}
