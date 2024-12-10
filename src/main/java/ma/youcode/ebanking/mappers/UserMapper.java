package ma.youcode.ebanking.mappers;

import ma.senane.utilities.mappers.GenericMapper;
import ma.youcode.ebanking.entities.Role;
import org.mapstruct.Mapper;
import ma.youcode.ebanking.dtos.request.UserRequestDTO;
import ma.youcode.ebanking.dtos.response.UserResponseDTO;
import ma.youcode.ebanking.entities.User;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<User, UserResponseDTO, UserRequestDTO> {

    @Override
    @Mapping(source = "roles", target = "roles", qualifiedByName = "mapToRoles")
    User fromRequestDTO(UserRequestDTO userRequestDTO);

    @Named("mapToRoles")
    default Set<Role> mapToRoles(Set<String> roles) {
        if (roles == null || roles.isEmpty()) {
            return null;
        }

        return roles.stream()
                .map(Role::new)
                .collect(Collectors.toSet());
    }
}
