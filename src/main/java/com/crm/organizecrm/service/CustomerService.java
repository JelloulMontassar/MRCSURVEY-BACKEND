package com.crm.organizecrm.service;

import com.crm.organizecrm.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customer);
    void deleteCustomer(Long id);
    Customer getCustomerById(Long id);
    List<Customer> getAllCustomers();
}
