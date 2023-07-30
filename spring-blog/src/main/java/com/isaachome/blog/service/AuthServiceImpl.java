package com.isaachome.blog.service;

import com.isaachome.blog.entity.Role;
import com.isaachome.blog.entity.User;
import com.isaachome.blog.exception.BlogAPIException;
import com.isaachome.blog.exception.ResourceNotFoundException;
import com.isaachome.blog.payload.LoginDto;
import com.isaachome.blog.payload.RegisterDto;
import com.isaachome.blog.repos.RoleRepo;
import com.isaachome.blog.repos.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements   AuthService{
    private  final AuthenticationManager authenticationManager;
    private final  UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private  final RoleRepo roleRepo;

    public AuthServiceImpl(UserRepo userRepo,RoleRepo roleRepo,PasswordEncoder passwordEncoder,AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager =authenticationManager;
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.usernameOrEmail(),loginDto.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "login Successful";
    }

    @Override
    public String register(RegisterDto registerDto) {

        // add check for username exists in database
        if(userRepo.existsByUsername(registerDto.username())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Username is already exists!.");

        }
        // add check for email exists in database
        if(userRepo.existsByEmail(registerDto.email())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Email is already exists!");
        }
        // create new User
        User user = new User();
        user.setEmail(registerDto.email());
        user.setName(registerDto.name());
        user.setUsername(registerDto.username());
        user.setPassword(passwordEncoder.encode(registerDto.password()));
        // find ROlES and set for new User.
        Role userRole = roleRepo.findByName("ROLE_USER").orElseThrow(
                ()->new ResourceNotFoundException("ROLE","role_name",1L));
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);
        userRepo.save(user);
        return "User Register successfully";
    }
}
