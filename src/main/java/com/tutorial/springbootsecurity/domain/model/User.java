package com.tutorial.springbootsecurity.domain.model;


import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.*;

@Data
@Document(collection = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Indexed(name = "id")
    private String id;

    @Field(name = "name")
    private String name;

    @Field(name = "username")
    private String username;

    @Field(name = "password")
    private String password;

    @Field(name = "roles")
    private Collection<Role> roles = new ArrayList<>();
}