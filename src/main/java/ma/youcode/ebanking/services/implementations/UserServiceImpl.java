package ma.youcode.ebanking.services.implementations;

import ma.youcode.ebanking.dtos.request.UserRequestDTO;
import ma.youcode.ebanking.dtos.response.UserResponseDTO;
import ma.youcode.ebanking.entities.Role;
import ma.youcode.ebanking.entities.User;
import ma.youcode.ebanking.exceptions.custom.PasswordUnchangedException;
import ma.youcode.ebanking.exceptions.custom.UnauthorizedActionException;
import ma.youcode.ebanking.exceptions.custom.UsernameAlreadyExistsException;
import ma.youcode.ebanking.mappers.UserMapper;
import ma.youcode.ebanking.repositories.interfaces.RoleRepository;
import ma.youcode.ebanking.repositories.interfaces.UserRepository;
import ma.youcode.ebanking.services.interfaces.UserService;

import ma.youcode.ebanking.utils.enums.RoleName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import jakarta.persistence.EntityNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    @Override
    public UserResponseDTO register(UserRequestDTO requestDTO) {

        if (isExists(requestDTO.username())) {
            throw new UsernameAlreadyExistsException(requestDTO.username());
        }

        User user = fromRequestDTO(requestDTO);
        user.setPassword(passwordEncoder.encode(requestDTO.password()));
        user.addRole(getUserRole());
        return toResponseDTO(userRepository.save(user));

    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private Role getUserRole() {
        return roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new EntityNotFoundException("Role not found."));
    }

    @Override
    public UserResponseDTO update(UserRequestDTO requestDTO, String username) {

        if (!username.equals(getAuthUserName())) {
            throw new UnauthorizedActionException("You can only update your own account.");
        }

        User user = getUserByUsername(username);

        if (!passwordEncoder.matches(requestDTO.password(), user.getPassword())) {
            throw new PasswordUnchangedException("Old password is incorrect.");
        }
        if (passwordEncoder.matches(requestDTO.newPassword(), user.getPassword())) {
            throw new PasswordUnchangedException("The new password must be different from the old password.");
        }
        user.setPassword(encodePassword(requestDTO.newPassword()));

        return toResponseDTO(userRepository.save(user));
    }

    @Override
    public UserResponseDTO delete(String username) {

        User user = getUserByUsername(username);
        userRepository.delete(user);
        return toResponseDTO(user);

    }

    @Override
    public UserResponseDTO read(String username) {
        User user = getUserByUsername(username);
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

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
    }

    @Override
    public UserResponseDTO editRoles(String username, UserRequestDTO requestDTO) {

        User user = getUserByUsername(username);

        user.getRoles().clear();
        Set<Role> roles = assignNewRoles(requestDTO);
        user.getRoles().addAll(roles);

        return toResponseDTO(userRepository.save(user));
    }

    private Set<Role> assignNewRoles(UserRequestDTO requestDTO) {
        Set<Role> roles = new HashSet<>();
        for (RoleName role : requestDTO.roles()) {
            roles.add(roleRepository.findByName(RoleName.valueOf(role.name())).orElseThrow(() -> new EntityNotFoundException("Role not found.")));
        }
        return roles;
    }


    @Override
    public String getAuthUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    private boolean isExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
