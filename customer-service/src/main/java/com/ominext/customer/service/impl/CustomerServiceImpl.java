package com.ominext.customer.service.impl;

import com.ominext.common.model.request.CustomerRequest;
import com.ominext.common.model.response.CustomerResponse;
import com.ominext.customer.entity.Customer;
import com.ominext.customer.repository.CustomerRepository;
import com.ominext.customer.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse getById(Long id) {
        customerRepository.findById(id).orElse(null);
        CustomerResponse response = new CustomerResponse();
        response.setId(id);
        response.setUsername("pick");
        response.setPassword("gunny");
        response.setPhone("0367201994");
        response.setRole("MEMBER");
        return response;
    }

    @Override
    public void save(CustomerRequest request) {
        Customer customer = new Customer();
        customer.setUsername(request.getUsername());
        customer.setPassword(request.getPassword());
        customer.setAddress(request.getAddress());
        customer.setGmail(request.getGmail());
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setPhone(request.getPhone());
        System.out.println("save success");
    }

    @Override
    public CustomerResponse findUsername(String username) {
        CustomerResponse response = new CustomerResponse();
        response.setUsername("pick");
        response.setPassword("gunny");
        response.setPhone("0367201994");
        response.setRole("MEMBER");
        return response;
    }
}
