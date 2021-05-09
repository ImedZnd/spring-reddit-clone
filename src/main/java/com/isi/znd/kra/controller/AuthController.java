package com.isi.znd.kra.controller;

import com.isi.znd.kra.dto.AuthenticationResponse;
import com.isi.znd.kra.dto.LoginRequest;
import com.isi.znd.kra.dto.RefreshTokenRequest;
import com.isi.znd.kra.dto.RegisterRequest;
import com.isi.znd.kra.service.AuthService;
import com.isi.znd.kra.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return new ResponseEntity<>("user register sucess", OK );
    }

    @GetMapping("accountVerification/{token}")
    public  ResponseEntity<String> verifiyAccount(@PathVariable String token){
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Succesfully", OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
       return  authService.login(loginRequest);
    }

    @PostMapping("/refresh/token")
    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }

}
