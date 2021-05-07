package com.isi.znd.kra.controller;

import com.isi.znd.kra.dto.AuthenticationResponse;
import com.isi.znd.kra.dto.LoginRequest;
import com.isi.znd.kra.dto.RegisterRequest;
import com.isi.znd.kra.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return new ResponseEntity<>("user register sucess", HttpStatus.OK );
    }

    @GetMapping("accountVerification/{token}")
    public  ResponseEntity<String> verifiyAccount(@PathVariable String token){
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Succesfully",HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
       return  authService.login(loginRequest);
    }

}
