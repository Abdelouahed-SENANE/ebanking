package ma.youcode.ebanking.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.senane.utilities.dtos.SuccessDTO;
import ma.senane.utilities.validation.groups.OnCreate;
import ma.senane.utilities.validation.groups.OnUpdate;
import ma.youcode.ebanking.dtos.request.UserRequestDTO;
import ma.youcode.ebanking.dtos.response.UserResponseDTO;
import ma.youcode.ebanking.services.interfaces.UserService;
import ma.youcode.ebanking.utils.groups.OnAdminEditRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static ma.senane.utilities.response.Response.success;


@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

private final UserService userService;

    @GetMapping("/get/{username}")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ResponseEntity<SuccessDTO> handleGetUser(@PathVariable String username) {
        UserResponseDTO response = userService.read(username);
    return success(200 , "Retrieved." , "user" , response);
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessDTO> handleLogin(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return success(200 , "Authenticated Successfully.");
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<SuccessDTO> handleGetAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserResponseDTO> response = userService.readAll(pageable);
            return success(200, "Retrieved.", "users", response);

    }

    @PostMapping("/register")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ResponseEntity<SuccessDTO> handleRegister(@Validated(OnCreate.class) @RequestBody UserRequestDTO requestDTO ){
         UserResponseDTO response = userService.register(requestDTO);
        return success(201 , "Created." ,  "user" , response);
    }

    @PreAuthorize(value = "hasRole('ROLE_USER')")
    @PutMapping("/update/{username}")
    public ResponseEntity<SuccessDTO> handleUpdate(@PathVariable String username ,@Validated(OnAdminEditRole.class)  @RequestBody UserRequestDTO requestDTO ){
        UserResponseDTO response = userService.update(requestDTO , username);
        return success(200 , "Updated." ,  "user" , response);
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<SuccessDTO> handleDelete(@PathVariable String username){
        UserResponseDTO response = userService.delete(username);
        return success(200 , "Deleted." , "user" , response);
    }
}