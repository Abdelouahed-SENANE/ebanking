package ma.youcode.ebanking.repositories.interfaces;

import ma.youcode.ebanking.utils.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import ma.youcode.ebanking.entities.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findByName(RoleName name);
}