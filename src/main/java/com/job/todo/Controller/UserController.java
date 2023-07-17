package com.job.todo.Controller;

import com.job.todo.JWT.JwtUtils;
import com.job.todo.Model.ToDoUser;
import com.job.todo.Repository.UserRepository;
import com.job.todo.Request.LoginRequest;
import com.job.todo.Request.SignInRequest;
import com.job.todo.Response.MessageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager authenticate;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignInRequest signInRequest) {


        if (userRepository.existsByUsername(signInRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        // Create new user's account
        ToDoUser user = new ToDoUser(signInRequest.getUsername(),
                encoder.encode(signInRequest.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/login")
    public String generateToken(@Valid @RequestBody LoginRequest authRequest) throws Exception {
        try {
            authenticate.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtils.generateToken(authRequest.getUsername());
    }
}
