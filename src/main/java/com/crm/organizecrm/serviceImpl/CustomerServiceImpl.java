package com.crm.organizecrm.serviceImpl;

import com.crm.organizecrm.exception.CustomerNotFoundException;
import com.crm.organizecrm.model.Customer;
import com.crm.organizecrm.repository.CustomerRepository;
import com.crm.organizecrm.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        existingCustomer.setName(customer.getName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setPhone_number(customer.getPhone_number());
        existingCustomer.setStatus(customer.getStatus());
        return customerRepository.save(existingCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        customerRepository.delete(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
