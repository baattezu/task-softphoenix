package org.softwarephoenix.testzadanie.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.softwarephoenix.testzadanie.entities.DTO.LoginDTO;
import org.softwarephoenix.testzadanie.entities.DTO.RegisterDTO;
import org.softwarephoenix.testzadanie.entities.User;
import org.softwarephoenix.testzadanie.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private final SecurityContextRepository contextRepository = new HttpSessionSecurityContextRepository();



    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
        if (!isValidRegister(registerDTO)) {
            return ResponseEntity.badRequest().body("Неправильно введенны данные, не все заполнено");
        }
        if(userService.existsByUsername(registerDTO.getUsername())){
            return new ResponseEntity<>("Логин уже существует!", HttpStatus.BAD_REQUEST);
        }
        if(!registerDTO.getPassword1().equals(registerDTO.getPassword2())){
            return new ResponseEntity<>("Пароли не совпадают!", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword1()));
        String[] names = registerDTO.getFullName().split(" ");
        user.setLastName(names[0]);
        user.setFirstName(names[1]);
        user.setPatronymic(names[2]);
        userService.save(user);
        return new ResponseEntity<>("Вы успешно зарегистрировались!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDto, HttpServletRequest request, HttpServletResponse response){
        try {
            if(!userService.existsByUsername(loginDto.getUsername())){
                return new ResponseEntity<>("Логина не существует", HttpStatus.BAD_REQUEST);
            }
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(), loginDto.getPassword())
            );
            SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
            emptyContext.setAuthentication(authentication);
            SecurityContextHolder.setContext(emptyContext);
            contextRepository.saveContext(emptyContext, request, response);
            return new ResponseEntity<>("Вы вошли в систему!",HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неправильный пароль");
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Аутентификация провалилась: " + e.getMessage());
        }
    }
    private boolean isValidRegister(RegisterDTO registerDTO) {
        if (registerDTO.getUsername() == null || registerDTO.getUsername().isEmpty()) {
            return false;
        }
        String password1 = registerDTO.getPassword1();
        String password2 = registerDTO.getPassword2();
        if (password1 == null || password1.isEmpty() ||
                password2 == null || password2.isEmpty() ) {
            return false;
        }
        String fullName = registerDTO.getFullName();
        if (fullName == null || fullName.isEmpty() || fullName.split(" ").length != 3) {
            return false;
        }
        return true;
    }
    @GetMapping("/info")
    public ResponseEntity<String> getInfoAboutLoggedIn(Authentication authentication){

        return new ResponseEntity<>(authentication.getName(), HttpStatus.OK);
    }
}
