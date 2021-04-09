package com.jwt.security.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

}
