package com.dalapo.controller;

import com.dalapo.entity.UserEntity;
import com.dalapo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserEntity u) {
        try {
            UserEntity createdUser = service.create(u);
            return ResponseEntity.ok(createdUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntity loginDetails) {
        UserEntity user = service.loginUser(loginDetails.getEmail(), loginDetails.getPassword());
        
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(401).body("Invalid Email or Password");
        }
    }

    @GetMapping
    public List<UserEntity> getAll() { 
        return service.getAll(); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) { 
        try {
            return ResponseEntity.ok(service.getById(id)); 
        } catch (RuntimeException e) {
             return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UserEntity user) {
        try {
            return ResponseEntity.ok(service.update(id, user));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("User deleted");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}