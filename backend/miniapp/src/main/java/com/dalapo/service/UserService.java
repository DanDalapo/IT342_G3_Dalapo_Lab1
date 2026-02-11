package com.dalapo.service;

import com.dalapo.entity.UserEntity;
import com.dalapo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserEntity create(UserEntity user) {
        if (repository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        return repository.save(user);
    }

    public List<UserEntity> getAll() {
        return repository.findAll();
    }

    public UserEntity getById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserEntity update(Integer id, UserEntity userDetails) {
        UserEntity existingUser = getById(id);

        existingUser.setFirstName(userDetails.getFirstName());
        existingUser.setLastName(userDetails.getLastName());
        existingUser.setDateOfBirth(userDetails.getDateOfBirth());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setPassword(userDetails.getPassword());
        existingUser.setRegistered(userDetails.isRegistered());

        return repository.save(existingUser);
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        repository.deleteById(id);
    }

    public UserEntity loginUser(String email, String password) {
        Optional<UserEntity> userOpt = repository.findByEmail(email);
        
        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}