package com.example.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.login.entity.Customer;
import com.example.login.repository.CustomerRepository;
import com.example.login.security.JwtUtil;


@Service
public class CustomerService {

	@Autowired
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public String login(String email, String password) {
        Customer customer = repository.findByEmail(email)
                .orElseThrow(() -> new IllegalStateException("Email not found"));

        if (!new BCryptPasswordEncoder().matches(password, customer.getPassword())) {
            throw new IllegalStateException("Invalid password");
        }

        return JwtUtil.generateToken(email);
    }

    public void register(Customer customer) {
        customer.setPassword(new BCryptPasswordEncoder().encode(customer.getPassword()));
        repository.save(customer);
    }
}
