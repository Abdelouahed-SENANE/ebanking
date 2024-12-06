package ma.youcode.ebanking.controllers;

import ma.senane.utilities.dtos.SuccessDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static ma.senane.utilities.response.Response.success;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @GetMapping("/contacts")
    public ResponseEntity<SuccessDTO> contacts() {
        return success(200 , "Welcome in contact page");
    }

    @GetMapping("/notices")
    public ResponseEntity<SuccessDTO> notices() {
        return success(200 , "Welcome in notice page");
    }

}
