package ma.youcode.ebanking.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.senane.utilities.dtos.SuccessDTO;
import ma.youcode.ebanking.dtos.request.UserRequestDTO;
import ma.youcode.ebanking.dtos.response.UserResponseDTO;
import ma.youcode.ebanking.services.interfaces.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import static ma.senane.utilities.response.Response.success;


@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

private final UserService userService;

    @GetMapping("/get/{id}")
    public ResponseEntity<SuccessDTO> handleGetUser(@PathVariable Long id) {
        UserResponseDTO response = userService.read(id);
    return success(200 , "Retrieved." , "user" , response);
    }
    @PostMapping("/login")
    public ResponseEntity<SuccessDTO> handleLogin(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return success(200 , "Authenticated Successfully.");
    }

    @GetMapping("/all")
    public ResponseEntity<SuccessDTO> handleGetAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserResponseDTO> response = userService.readAll(pageable);
            return success(200, "Retrieved.", "users", response);

    }

    @PostMapping("/register")
    public ResponseEntity<SuccessDTO> handleRegister(@Valid @RequestBody UserRequestDTO requestDTO ){
         UserResponseDTO response = userService.register(requestDTO);
        return success(201 , "Created." ,  "user" , response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SuccessDTO> handleUpdate(@PathVariable Long id ,@Valid  @RequestBody UserRequestDTO requestDTO ){
        UserResponseDTO response = userService.update(requestDTO , id);
        return success(200 , "Updated." ,  "user" , response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SuccessDTO> handleDelete(@PathVariable Long id){
        UserResponseDTO response = userService.delete(id);
        return success(200 , "Deleted." , "user" , response);
    }
}