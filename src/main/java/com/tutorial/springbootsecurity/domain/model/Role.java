package com.tutorial.springbootsecurity.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.*;
@Data
@Document(collection = "role")
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @Field(name = "_id")
    private String _id;

    @Indexed
    @Field(name = "id")
    private int id;

    @Field(name = "name")
    private String name;    
}