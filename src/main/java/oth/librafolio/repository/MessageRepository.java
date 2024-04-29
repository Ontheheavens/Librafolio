package oth.librafolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import oth.librafolio.model.Message;

/**
 * @author Ontheheavens
 * @since 29.04.2024
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "SELECT * FROM message ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Message findRandomMessage();

}