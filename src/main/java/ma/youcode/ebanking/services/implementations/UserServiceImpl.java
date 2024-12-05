    package ma.youcode.ebanking.services.implementations;

    import ma.youcode.ebanking.dtos.request.UserRequestDTO;
    import ma.youcode.ebanking.dtos.response.UserResponseDTO;
    import ma.youcode.ebanking.entities.User;
    import ma.youcode.ebanking.mappers.UserMapper;
    import ma.youcode.ebanking.repositories.interfaces.UserRepository;
    import ma.youcode.ebanking.security.AuthProvider;
    import ma.youcode.ebanking.services.interfaces.UserService;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Lazy;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;
    import lombok.AllArgsConstructor;
    import jakarta.persistence.EntityNotFoundException;
    import java.util.List;

    @Service
    @AllArgsConstructor
    public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDTO register(UserRequestDTO requestDTO) {

        User user = fromRequestDTO(requestDTO);
        return toResponseDTO(userRepository.save(user));

    }

    @Override
    public UserResponseDTO update(UserRequestDTO requestDTO, Long userId) {
        return null;
    }

    @Override
    public UserResponseDTO delete(Long userId) {

        User user = getUserById(userId);
        userRepository.delete(user);
        return toResponseDTO(user);

    }

    @Override
    public UserResponseDTO read(Long userId) {
        User user = getUserById(userId);
        return toResponseDTO(user);
    }

    @Override
    public List<UserResponseDTO> readAll() {
        List<User> users = userRepository.findAll();
        return toResponseDTOs(users);
    }

    @Override
    public Page<UserResponseDTO> readAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(userMapper::toResponseDTO);
    }

    private UserResponseDTO toResponseDTO(User user) {
        return userMapper.toResponseDTO(user);
    }

    private User fromRequestDTO(UserRequestDTO requestDTO) {
        return userMapper.fromRequestDTO(requestDTO);
    }

    private List<UserResponseDTO> toResponseDTOs(List<User> users) {
        return userMapper.toResponseDTOs(users);
    }


    public User getUserById(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("User not found."));
    }

}
