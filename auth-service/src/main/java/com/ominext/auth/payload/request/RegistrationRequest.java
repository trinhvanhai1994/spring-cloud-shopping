package com.ominext.auth.payload.request;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String username;
    private String password;
    private String address;
    private String phone;
    private String firstName;
    private String lastName;
}
