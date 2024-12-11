package ma.youcode.ebanking.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.senane.utilities.entities.BaseEntity;
import ma.youcode.ebanking.utils.enums.RoleName;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity<Long> {

    @Column(name = "role_name" , nullable = false , unique = true)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role name must be not null.")
    private RoleName name;
};