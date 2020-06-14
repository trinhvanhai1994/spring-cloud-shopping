package com.ominext.customer.controller;

import com.ominext.common.model.request.CustomerRequest;
import com.ominext.common.model.response.CustomerResponse;
import com.ominext.customer.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

//    @GetMapping("/customer/{id}")
//    public CustomerResponse get(@PathVariable Long id) {
//        return service.getById(id);
//    }

    @PostMapping("/customer")
    public String create(@RequestBody CustomerRequest request) {
        service.save(request);
        return "successfully";
    }

    @GetMapping("/customer/{username}")
    public CustomerResponse findByUsername(@PathVariable String username) {
        return service.findUsername(username);
    }
}
