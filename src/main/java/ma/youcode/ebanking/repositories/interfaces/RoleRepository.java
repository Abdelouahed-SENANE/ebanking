package ma.youcode.ebanking.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import ma.youcode.ebanking.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{}