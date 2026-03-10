package com.akilli.ahir.Service;

import com.akilli.ahir.Model.User;
import com.akilli.ahir.Repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 🔹 GİRİŞ İÇİN: Email ile kullanıcı bulma
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // 🔹 Yeni kullanıcı kaydetme
    public User kaydet(User user) {
        return userRepository.save(user);
    }

    // 🔹 Tüm kullanıcıları listeleme
    public List<User> tumKullanicilar() {
        return userRepository.findAll();
    }

    // 🔹 Tekil kullanıcı getirme
    public Optional<User> kullaniciGetir(Long id) {
        return userRepository.findById(id);
    }

    // 🔹 Kullanıcı silme
    public void sil(Long id) {
        userRepository.deleteById(id);
    }
}