package dao;

import models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;



public interface UserDao extends JpaRepository<User,Integer> {

    @Transactional
    Optional<User> findByUsername(String username);
}
