package ma.youcode.ebanking.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import ma.youcode.ebanking.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
}