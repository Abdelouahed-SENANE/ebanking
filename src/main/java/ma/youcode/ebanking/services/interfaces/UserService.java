package ma.youcode.ebanking.services.interfaces;

import ma.youcode.ebanking.dtos.request.UserRequestDTO;
import ma.youcode.ebanking.dtos.response.UserResponseDTO;
import ma.youcode.ebanking.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserService {

    UserResponseDTO register(UserRequestDTO requestDTO);
    UserResponseDTO update(UserRequestDTO requestDTO , Long userId);
    UserResponseDTO delete(Long userId);
    UserResponseDTO read(Long userId);
    Page<UserResponseDTO> readAll(Pageable pageable);
    List<UserResponseDTO> readAll();
    User getUserById(Long userId);

}
