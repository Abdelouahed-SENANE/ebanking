package ma.youcode.ebanking.dtos.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import ma.senane.utilities.validation.groups.OnCreate;
import ma.senane.utilities.validation.groups.OnUpdate;
import ma.youcode.ebanking.utils.groups.OnAdminEditRole;


import java.io.Serializable;
import java.util.List;
import java.util.Set;

public record UserRequestDTO(
        @NotNull(groups = OnCreate.class) String username,
        @NotNull(groups = {OnCreate.class, OnUpdate.class}) String password,
        @NotNull(groups = OnUpdate.class , message = "New password must be not null.") String newPassword,
        @NotNull(groups = OnAdminEditRole.class) Set<@Pattern(groups = OnAdminEditRole.class, regexp = "ROLE_[A-Z]+" , message = "Invalid role format.") String> roles
) implements Serializable {}
