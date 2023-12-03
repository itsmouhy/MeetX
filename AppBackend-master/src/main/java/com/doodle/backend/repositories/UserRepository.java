package com.doodle.backend.repositories;

import com.doodle.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findOneByEmailAndPassword(String email, String password);
    User findByEmail(String email);
    User findByUsername(String username);


    //User findOneByEmailAndPassword2(String email, String password);

}
