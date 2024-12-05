package ma.youcode.ebanking.services.interfaces;

import ma.youcode.ebanking.dtos.request.RoleRequestDTO;
import ma.youcode.ebanking.dtos.response.RoleResponseDTO;
import ma.youcode.ebanking.entities.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface RoleService {

    RoleResponseDTO create(RoleRequestDTO requestDTO);
    RoleResponseDTO update(RoleRequestDTO requestDTO , Long roleId);
    RoleResponseDTO delete(Long roleId);
    RoleResponseDTO read(Long roleId);
    Page<RoleResponseDTO> readAll(Pageable pageable);
    List<RoleResponseDTO> readAll();
    Role getRoleById(Long roleId);

}
