package com.ominext.common.model.request;

import lombok.Data;

@Data
public class CustomerRequest {
    private String username;
    private String password;
    private String gmail;
    private String address;
    private String phone;
    private String firstName;
    private String lastName;
}
