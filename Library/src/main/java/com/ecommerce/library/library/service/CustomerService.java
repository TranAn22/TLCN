package com.ecommerce.library.library.service;

import com.ecommerce.library.library.dto.CustomerDto;
import com.ecommerce.library.library.model.Customer;

public interface CustomerService {

    CustomerDto save(CustomerDto customerDto);

    Customer findByUsername(String username);

    Customer saveInfor(Customer customer);
}