package com.tutorial.springbootsecurity.domain;


import com.tutorial.springbootsecurity.domain.model.Role;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

    @Query("{'name':?0}")
    Role findByName(String name);

//    Role saveRole(Role role);
}