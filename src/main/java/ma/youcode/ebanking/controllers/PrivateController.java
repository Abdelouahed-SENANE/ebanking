package ma.youcode.ebanking.controllers;

import ma.senane.utilities.dtos.SuccessDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.*;

import static ma.senane.utilities.response.Response.success;


@RestController
@RequestMapping("/api/private/me")
public class PrivateController {

    @GetMapping("/balance")
    public ResponseEntity<SuccessDTO> getBalance() {
        return success(200, "Success", "myBalance", 2400.66);
    }

    @GetMapping("/cards")
    public ResponseEntity<SuccessDTO> getMyCards() {
        List<Long> cards = Arrays.asList(
                1234567812345678L,
                2345678923456789L,
                3456789034567890L,
                4567890145678901L
        );

        return success(200, "Success", "myCards", cards);
    }

    @GetMapping("/loans")
    public ResponseEntity<SuccessDTO> getMyLoans() {
        List<Map<String, Object>> loans = Arrays.asList(
                Map.of(
                        "loanId", 101,
                        "amount", 15000.00,
                        "interestRate", 3.5,
                        "startDate", LocalDate.of(2022, 5, 15),
                        "endDate", LocalDate.of(2027, 5, 15),
                        "status", "Active"
                ),
                Map.of(
                        "loanId", 102,
                        "amount", 5000.00,
                        "interestRate", 4.2,
                        "startDate", LocalDate.of(2021, 3, 10),
                        "endDate", LocalDate.of(2026, 3, 10),
                        "status", "Paid Off"
                )
        );
        return success(200, "Success", "myLoans", loans);
    }

    
    @GetMapping("/account")
    public ResponseEntity<SuccessDTO> getMyAccount() {
        Map<String, Object> accountInfo = Map.of(
                "accountId", "123456789",
                "accountHolder", "John Doe",
                "accountType", "Savings",
                "balance", 2400.66,
                "currency", "USD",
                "createdDate", LocalDate.of(2018, 7, 15),
                "status", "Active"
        );

        return success(200, "Success", "myAccount", accountInfo);
    }
}
