package com.ominext.auth.payload.response;

import lombok.Data;

@Data
public class JwtResponse {
    private Integer status;
    private String token;
}
