package com.akilli.ahir.Repository;

import com.akilli.ahir.Model.User; // User modelini buraya dahil ettik
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Email ile kullanıcı bulma metodu
    User findByEmail(String email);
}