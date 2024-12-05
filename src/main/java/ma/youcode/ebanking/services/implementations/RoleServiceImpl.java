    package ma.youcode.ebanking.services.implementations;

    import ma.youcode.ebanking.dtos.request.RoleRequestDTO;
    import ma.youcode.ebanking.dtos.response.RoleResponseDTO;
    import ma.youcode.ebanking.entities.Role;
    import ma.youcode.ebanking.mappers.RoleMapper;
    import ma.youcode.ebanking.repositories.interfaces.RoleRepository;
    import ma.youcode.ebanking.services.interfaces.RoleService;

    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.stereotype.Service;
    import lombok.AllArgsConstructor;
    import jakarta.persistence.EntityNotFoundException;
    import java.util.List;

    @Service
    @AllArgsConstructor
    public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;


    @Override
    public RoleResponseDTO create(RoleRequestDTO requestDTO) {

        Role role = fromRequestDTO(requestDTO);
        return toResponseDTO(roleRepository.save(role));

    }

    @Override
    public RoleResponseDTO update(RoleRequestDTO requestDTO, Long roleId) {
        return null;
    }

    @Override
    public RoleResponseDTO delete(Long roleId) {

        Role role = getRoleById(roleId);
        roleRepository.delete(role);
        return toResponseDTO(role);

    }

    @Override
    public RoleResponseDTO read(Long roleId) {
        Role role = getRoleById(roleId);
        return toResponseDTO(role);
    }

    @Override
    public List<RoleResponseDTO> readAll() {
        List<Role> roles = roleRepository.findAll();
        return toResponseDTOs(roles);
    }

    @Override
    public Page<RoleResponseDTO> readAll(Pageable pageable) {
        Page<Role> roles = roleRepository.findAll(pageable);
        return roles.map(roleMapper::toResponseDTO);
    }

    private RoleResponseDTO toResponseDTO(Role role) {
        return roleMapper.toResponseDTO(role);
    }

    private Role fromRequestDTO(RoleRequestDTO requestDTO) {
        return roleMapper.fromRequestDTO(requestDTO);
    }

    private List<RoleResponseDTO> toResponseDTOs(List<Role> roles) {
        return roleMapper.toResponseDTOs(roles);
    }


    public Role getRoleById(Long roleId) {
    return roleRepository.findById(roleId)
        .orElseThrow(() -> new EntityNotFoundException("Role not found."));
    }

}
