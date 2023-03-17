package com.tfe.fournil.repository;

import com.tfe.fournil.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    //liste de user
    // findByEmail(String email) REVIENT AU MEME QUE select * from user where email = 'mail que tu recois en paramertre'
    List<User> findByEmail(String email);

    User findByUsername(String username);

}
