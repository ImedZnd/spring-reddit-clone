package com.isi.znd.kra.controller;

import com.isi.znd.kra.dto.VoteDto;
import com.isi.znd.kra.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/votes/")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class VoteController {

    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<Void> vote(@RequestBody VoteDto voteDto) {
        System.out.println(voteDto);
        voteService.vote(voteDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}