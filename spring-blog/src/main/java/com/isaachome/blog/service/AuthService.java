package com.isaachome.blog.service;

import com.isaachome.blog.payload.LoginDto;
import com.isaachome.blog.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
