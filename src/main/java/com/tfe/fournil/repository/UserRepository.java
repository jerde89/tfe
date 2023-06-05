package com.tfe.fournil.repository;

import com.tfe.fournil.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface User repository.
 */
public interface UserRepository extends JpaRepository<User,Long> {
    /**
     * Find by email list.
     *
     * @param email the email
     * @return the list
     */
//liste de user
    // findByEmail(String email) REVIENT AU MEME QUE select * from user where email = 'mail que tu recois en paramertre'
    List<User> findByEmail(String email);

    /**
     * Find by username user.
     *
     * @param username the username
     * @return the user
     */
    User findByUsername(String username);

}
