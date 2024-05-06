package oth.librafolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import oth.librafolio.model.User;

import java.util.List;

/**
 * @author Ontheheavens
 * @since 02.05.2024
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
    User findByUsername(String username);

    @Query(value = "SELECT * FROM users WHERE role = 'ADMIN'", nativeQuery = true)
    List<User> getAllAdmins();

}
