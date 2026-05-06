package com.akilli.ahir.Service;

import com.akilli.ahir.Model.User;
import com.akilli.ahir.Repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    
    public User kaydet(User user) {
        return userRepository.save(user);
    }

    
    public List<User> tumKullanicilar() {
        return userRepository.findAll();
    }

    
    public Optional<User> kullaniciGetir(Long id) {
        return userRepository.findById(id);
    }

    
    public void sil(Long id) {
        userRepository.deleteById(id);
    }
}