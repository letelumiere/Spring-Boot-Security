package com.tutorial.springbootsecurity.domain;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.tutorial.springbootsecurity.domain.model.Role;
import com.tutorial.springbootsecurity.domain.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor 
@Transactional
@Slf4j
public class UserService implements UserDetailsService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if(user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }else{
            log.info("User found in the database {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        log.info("authorities are {}", authorities);
        log.info("authorities are {}", user.toString());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public Page<User> findAllUserWithPageable(int pageNum){
        PageRequest pageable = PageRequest.of(pageNum, pageNum + 10);
        Page<User> user = userRepository.findAll(pageable);
        return user;
    }

    public User findOneUserById(String id){
        return userRepository.findOneUserById(id);
    }

    public List<User> findAllUserByName(String nickname){
        return userRepository.findAllUsersByNickName(nickname);
    }
    public User saveUser(User user){
        log.info("Saving new user {} to the database", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Role saveRole(Role role){
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    public void addToRoleToUser(String username, String roleName){
        log.info("Adding role {} to the user {}", roleName, username);
        User user = userRepository.findUserByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    public User getUser(String username){
        log.info("Fetching user {}", username);
        return userRepository.findUserByUsername(username);
    }

    public List<User>getUsers(){
        log.info("Fetching all users");
        return userRepository.findAll();
    }

}