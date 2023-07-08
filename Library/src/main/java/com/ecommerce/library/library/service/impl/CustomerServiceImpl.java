package com.ecommerce.library.library.service.impl;

import com.ecommerce.library.library.dto.CustomerDto;
import com.ecommerce.library.library.model.CartItem;
import com.ecommerce.library.library.model.Customer;
import com.ecommerce.library.library.model.ShoppingCart;
import com.ecommerce.library.library.repository.CustomerRepository;
import com.ecommerce.library.library.repository.RoleRepository;
import com.ecommerce.library.library.repository.ShoppingCartRepository;
import com.ecommerce.library.library.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private RoleRepository repository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        Customer customer = new Customer();
        ShoppingCart cart = customer.getShoppingCart();
        if (cart == null) {
            cart = new ShoppingCart();
        }
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setUsername(customerDto.getUsername());
        customer.setPassword(customerDto.getPassword());
        customer.setRoles(Arrays.asList(repository.findByName("CUSTOMER")));
        Customer customerSave = customerRepository.save(customer);
        customer.setId(customer.getId());
        cart.setCustomer(customer);
        shoppingCartRepository.save(cart);
        return mapperDTO(customerSave);
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    @Override
    public Customer saveInfor(Customer customer) {
        Customer customer1 = customerRepository.findByUsername(customer.getUsername());
        customer1.setAddress(customer.getAddress());
        customer1.setCity(customer.getCity());
        customer1.setCountry(customer.getCountry());
        customer1.setPhoneNumber(customer.getPhoneNumber());
        return customerRepository.save(customer1);
    }

    private CustomerDto mapperDTO(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setPassword(customer.getPassword());
        customerDto.setUsername(customer.getUsername());
        return customerDto;
    }
}