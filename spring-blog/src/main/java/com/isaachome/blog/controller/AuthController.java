package com.isaachome.blog.controller;
import com.isaachome.blog.payload.JwtResponseDto;
import com.isaachome.blog.payload.LoginDto;
import com.isaachome.blog.payload.RegisterDto;
import com.isaachome.blog.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private  final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/login","/signin"})
    public ResponseEntity<JwtResponseDto> login(@RequestBody LoginDto loginDto){
        var response= new JwtResponseDto();
        response.setAccessToken(authService.login(loginDto));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value={"/register","/signup"})
    public  ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        return  new ResponseEntity<>(authService.register(registerDto),HttpStatus.OK);
    }
}
