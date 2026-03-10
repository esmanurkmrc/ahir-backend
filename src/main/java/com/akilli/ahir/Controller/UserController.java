package com.akilli.ahir.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.akilli.ahir.Model.User;
import com.akilli.ahir.Service.UserService;

@CrossOrigin(origins = "http://localhost:3000") // React bağlantısı için
@RestController
@RequestMapping("/api/users") // Ana endpoint
public class UserController {

    private final UserService userService;

    // Constructor injection
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 1️⃣ Yeni Kullanıcı Kaydı
    @PostMapping("/kayit")
    public ResponseEntity<User> kaydet(@RequestBody User user) {
        User yeniUser = userService.kaydet(user);
        return ResponseEntity.ok(yeniUser);
    }

    // 2️⃣ Giriş Yapma (Login)
    @PostMapping("/giris")
    public ResponseEntity<?> girisYap(@RequestBody User loginUser) {
        // Email ile kullanıcıyı bul
        User user = userService.findByEmail(loginUser.getEmail());

        if (user != null && user.getSifre().equals(loginUser.getSifre())) {
            return ResponseEntity.ok(user); // Başarılı giriş
        } else {
            // Kullanıcı yok veya şifre hatalı
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("E-posta veya şifre hatalı!");
        }
    }

    // 3️⃣ Tüm Kullanıcıları Listele
    @GetMapping("/liste")
    public ResponseEntity<List<User>> tumKullanicilar() {
        List<User> kullanicilar = userService.tumKullanicilar();
        return ResponseEntity.ok(kullanicilar);
    }

    // 4️⃣ ID ile Kullanıcı Getir
    @GetMapping("/{id}")
    public ResponseEntity<User> kullaniciGetir(@PathVariable Long id) {
        return userService.kullaniciGetir(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 5️⃣ Kullanıcı Sil
    @DeleteMapping("/sil/{id}")
    public ResponseEntity<String> sil(@PathVariable Long id) {
        userService.sil(id);
        return ResponseEntity.ok("Kullanıcı başarıyla silindi.");
    }
}