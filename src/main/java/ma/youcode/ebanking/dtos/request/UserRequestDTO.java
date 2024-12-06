package ma.youcode.ebanking.dtos.request;


import jakarta.validation.constraints.NotNull;
import ma.senane.utilities.validation.groups.OnCreate;
import ma.senane.utilities.validation.groups.OnUpdate;


import java.io.Serializable;

public record UserRequestDTO(
        @NotNull(groups = OnCreate.class) String username,
        @NotNull(groups = {OnCreate.class, OnUpdate.class}) String password,
        @NotNull(groups = OnUpdate.class , message = "New password must be not null.") String newPassword
) implements Serializable {
}
