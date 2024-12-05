package ma.youcode.ebanking.dtos.request;


import jakarta.validation.constraints.NotNull;
import ma.senane.utilities.validation.annotations.Unique;
import ma.youcode.ebanking.entities.Role;
import ma.youcode.ebanking.entities.User;

import java.io.Serializable;
import java.util.Set;

public record UserRequestDTO(
        @NotNull String username,
        @NotNull String email,
        @NotNull String password,
        Set<Role> roles
) implements Serializable {
}
