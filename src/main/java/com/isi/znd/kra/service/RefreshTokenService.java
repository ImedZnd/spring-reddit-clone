package com.isi.znd.kra.service;

import com.isi.znd.kra.model.RefreshToken;
import com.isi.znd.kra.model.exeptions.SpringRedditException;
import com.isi.znd.kra.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken generateRefreshToken(){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());
        return refreshToken;
    }

    void validateRefreshToken(String token){
        refreshTokenRepository.findByToken(token)
                .orElseThrow(()-> new SpringRedditException("Invalid refresh token"));
    }



    public void deleteRefreshToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}
