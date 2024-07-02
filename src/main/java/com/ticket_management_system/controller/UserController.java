package com.ticket_management_system.controller;

import com.ticket_management_system.model.dto.LoginDto;
import com.ticket_management_system.model.dto.LoginResponse;
import com.ticket_management_system.model.dto.UserDto;
import com.ticket_management_system.repository.UserRepository;
import com.ticket_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/SignUp")
    public String signUp(@RequestBody UserDto userDto) {
        try {
            String username = userDto.getUsername();
            String password = userDto.getPassword();
            String role = userDto.getRole();

            String encryptedPassword = bCryptPasswordEncoder.encode(password);
            userDto.setPassword(encryptedPassword);

            userService.SaveSignup(userDto);
            return "SignUp Successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }




    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        try {
            String loginUsername = loginDto.getLoginUsername();
            String loginPassword = loginDto.getLoginPassword();
            String loginRole = loginDto.getLoginRole();
            List<Object[]> userNameAndPassword = userRepository.getLoginUsernameAndPassword(loginRole, loginUsername);

            if (!userNameAndPassword.isEmpty()) {
                Object[] user = userNameAndPassword.get(0);
                String username = (String) user[0];
                String storedPassword = (String) user[1];
                String role = (String) user[2];
                Integer userid = (Integer) user[3];
                if (bCryptPasswordEncoder.matches(loginPassword, storedPassword)) {
                    LoginResponse loginResponse = new LoginResponse(role, userid);
                    return ResponseEntity.ok().body(loginResponse);
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password.");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
