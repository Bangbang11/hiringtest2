package com.example.pretest.user.dto.response;

import com.example.pretest.user.model.User;
import com.example.pretest.user.model.UserProfile;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("username")
    private String userName;

    @JsonProperty("fullname")
    private String fullName;

    @JsonProperty("nik")
    private String nik;

    @JsonProperty("address")
    private String address;

    @JsonProperty("avatar")
    private String avatar;

    public UserResponse(User user, UserProfile userProfile) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.fullName = userProfile.getFullName();
        this.nik = userProfile.getNik();
        this.address = userProfile.getAddress();
        this.avatar = userProfile.getAvatar();

    }
}
