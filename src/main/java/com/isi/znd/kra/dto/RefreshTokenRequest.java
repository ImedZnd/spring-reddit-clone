package com.isi.znd.kra.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRequest {

    @NotBlank
    private String RefreshToken;
    private String username;

}
