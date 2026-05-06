package com.akilli.ahir.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.akilli.ahir.Model.User;
import com.akilli.ahir.Service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users") 
public class UserController {

    private final UserService userService;

    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    
    @PostMapping("/kayit")
    public ResponseEntity<User> kaydet(@RequestBody User user) {
        User yeniUser = userService.kaydet(user);
        return ResponseEntity.ok(yeniUser);
    }

  
    @PostMapping("/giris")
    public ResponseEntity<?> girisYap(@RequestBody User loginUser) {
       
        User user = userService.findByEmail(loginUser.getEmail());

        if (user != null && user.getSifre().equals(loginUser.getSifre())) {
            return ResponseEntity.ok(user); 
        } else {
           
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("E-posta veya şifre hatalı!");
        }
    }

   
    @GetMapping("/liste")
    public ResponseEntity<List<User>> tumKullanicilar() {
        List<User> kullanicilar = userService.tumKullanicilar();
        return ResponseEntity.ok(kullanicilar);
    }

  
    @GetMapping("/{id}")
    public ResponseEntity<User> kullaniciGetir(@PathVariable Long id) {
        return userService.kullaniciGetir(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

   
    @DeleteMapping("/sil/{id}")
    public ResponseEntity<String> sil(@PathVariable Long id) {
        userService.sil(id);
        return ResponseEntity.ok("Kullanıcı başarıyla silindi.");
    }
}