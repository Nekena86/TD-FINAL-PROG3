package org.td2.prog_3.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.td2.prog_3.Services.AccountService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class AccountController {

    private AccountService AccountService;

    public AccountController(AccountService compteService) {
        this.AccountService = compteService;
    }

    @GetMapping("/collectivities/{id}/financialAccounts")
    public ResponseEntity<List<Map<String, Object>>> getFinancialAccounts(
            @PathVariable("id") Long collectivityId,
            @RequestParam(value = "at", required = false) String atDate) {

        List<Map<String, Object>> response = AccountService.getFinancialAccounts(collectivityId, atDate);
        return ResponseEntity.ok(response);
    }
}
