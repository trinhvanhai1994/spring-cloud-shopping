package com.ominext.customer.repository;

import com.ominext.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUsername(String username);
}
