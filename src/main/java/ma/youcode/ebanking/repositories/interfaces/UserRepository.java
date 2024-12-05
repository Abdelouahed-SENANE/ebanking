package ma.youcode.ebanking.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import ma.youcode.ebanking.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
}