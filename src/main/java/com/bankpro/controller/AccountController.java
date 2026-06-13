package com.bankpro.controller;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.*;

@RestController
@RequestMapping("/bank-api/accounts")
@Tag(name = "Accounts and Transactions REST endpoints", description = "Account Controller")
public class AccountController {

    @GetMapping
    public List<Map<String, Object>> getAllAccounts() {
        List<Map<String, Object>> accounts = new ArrayList<>();
        Map<String, Object> account1 = new HashMap<>();
        account1.put("id", 1);
        account1.put("accountNumber", "ACC-001");
        account1.put("owner", "John Doe");
        account1.put("balance", 5000.00);
        accounts.add(account1);
        return accounts;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getAccountById(@PathVariable int id) {
        Map<String, Object> account = new HashMap<>();
        account.put("id", id);
        account.put("accountNumber", "ACC-00" + id);
        account.put("owner", "John Doe");
        account.put("balance", 5000.00);
        return account;
    }

    @PostMapping
    public Map<String, Object> createAccount(@RequestBody Map<String, Object> account) {
        account.put("id", 2);
        account.put("message", "Account created successfully");
        return account;
    }
}
