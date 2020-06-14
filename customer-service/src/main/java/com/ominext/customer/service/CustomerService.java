package com.ominext.customer.service;

import com.ominext.customer.payload.request.CustomerRequest;
import com.ominext.customer.payload.response.CustomerResponse;

public interface CustomerService {
    CustomerResponse getById(Long id);
    void save(CustomerRequest request);
    CustomerResponse findUsername(String username);
}
