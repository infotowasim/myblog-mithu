package com.myblog_mithu.entity;


// Develop Sign Up feature

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter  // it was following the older version of lombok. don't put @Data it ll not work
@Getter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 60)
    private String name;


}
