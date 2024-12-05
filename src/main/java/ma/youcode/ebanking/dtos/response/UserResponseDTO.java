package ma.youcode.ebanking.dtos.response;

import ma.youcode.ebanking.entities.Role;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public record UserResponseDTO(
        String username,
        Set<Role> roles
) implements Serializable {}