package com.ominext.customer.service;

import com.ominext.common.model.request.CustomerRequest;
import com.ominext.common.model.response.CustomerResponse;

public interface CustomerService {
    CustomerResponse getById(Long id);
    void save(CustomerRequest request);
    CustomerResponse findUsername(String username);
}
