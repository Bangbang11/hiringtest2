package com.example.pretest.user.dto.request;

import lombok.Data;

@Data
public class UserRequest {

    private String userName;

    private String password;

    private String fullName;

    private String nik;

    private String address;

    private String avatar;
}
