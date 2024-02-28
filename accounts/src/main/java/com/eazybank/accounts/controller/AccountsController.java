package com.eazybank.accounts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hi World";
    }
}