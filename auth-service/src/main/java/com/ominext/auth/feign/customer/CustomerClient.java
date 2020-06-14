package com.ominext.auth.feign.customer;

import com.ominext.common.model.request.CustomerRequest;
import com.ominext.common.model.response.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "customer-service")
public interface CustomerClient {

    @GetMapping("/customers/customer/{id}")
    CustomerResponse getById(@PathVariable Long id);

    @GetMapping("/customers/customer/{username}")
    CustomerResponse findByUsername(@PathVariable String username);

    @PostMapping("/customers/customer")
    String create(@RequestBody CustomerRequest customerRequest);
}
