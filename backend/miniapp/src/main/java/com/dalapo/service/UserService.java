package com.dalapo.service;

import com.dalapo.entity.UserEntity;
import com.dalapo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public UserEntity create(UserEntity user){

        if (userRepo.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email is already registered");
        }

        UserEntity savedUser = userRepo.save(user);

        return savedUser;
    }

    public UserEntity update(Integer id, UserEntity updated) {
        UserEntity userUpdate = userRepo.findById(id).orElse(null);
        if (userUpdate == null) return null;

        if (updated.getFirstname() != null) userUpdate.setFirstname(updated.getFirstname());
        if (updated.getLastName() != null) userUpdate.setLastName(updated.getLastName());
        if (updated.getDateOfBirth() != null) userUpdate.setDateOfBirth(updated.getDateOfBirth());
        if (updated.getEmail() != null) userUpdate.setEmail(updated.getEmail());
        
        if (updated.getPassword() != null && !updated.getPassword().isEmpty()) {
            userUpdate.setPassword(updated.getPassword());
        }

        return userRepo.save(userUpdate);
    }

    public UserEntity loginUser(String email, String password) {
        UserEntity user = userRepo.findByEmail(email);

        if (user != null && user.getPassword() != null) {
            if (user.getPassword().equals(password)) {
                return user; 
            }
        }
        return null; 
    }
}