package com.example.pretest.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "UserProfiles")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "nik")
    private String nik;

    @Column(name = "address")
    private String address;

    @Column(name = "avatar")
    private String avatar;
}
