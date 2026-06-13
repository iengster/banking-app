package com.bankpro.controller;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.*;

@RestController
@RequestMapping("/bank-api/customers")
@Tag(name = "Customer REST endpoints", description = "Customer Controller")
public class CustomerController {

    @GetMapping
    public List<Map<String, Object>> getAllCustomers() {
        List<Map<String, Object>> customers = new ArrayList<>();
        Map<String, Object> customer = new HashMap<>();
        customer.put("id", 1);
        customer.put("name", "John Doe");
        customer.put("email", "john.doe@bankpro.com");
        customer.put("phone", "555-1234");
        customers.add(customer);
        return customers;
    }

    @GetMapping("/{id}")
    public Map<String, Object> getCustomerById(@PathVariable int id) {
        Map<String, Object> customer = new HashMap<>();
        customer.put("id", id);
        customer.put("name", "John Doe");
        customer.put("email", "john.doe@bankpro.com");
        customer.put("phone", "555-1234");
        return customer;
    }
}
