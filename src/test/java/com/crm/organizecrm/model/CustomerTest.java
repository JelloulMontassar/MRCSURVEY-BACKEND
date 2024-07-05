package com.crm.organizecrm.model;

import com.crm.organizecrm.OrganizeCrmApplication;
import com.crm.organizecrm.enumirators.Status;
import com.crm.organizecrm.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class CustomerTest {
    @Autowired
    private CustomerRepository customerRepository;


    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
    }

    @Test
    public void testDefaultConstructor() {
        Customer customer = new Customer();
        assertNull(customer.getId());
        assertNull(customer.getName());
        assertNull(customer.getLastName());
        assertNull(customer.getEmail());
        assertNull(customer.getPhone_number());
        assertNull(customer.getStatus());
    }

    @Test
    public void testSaveAndFetchCustomer() {
        // Create and save a new customer
        Customer customer = new Customer(null, "John", "Doe", "john.doe@example.com", "1234567890", Status.Client);
        customer = customerRepository.save(customer);

        // Fetch the customer by ID
        Optional<Customer> fetchedCustomer = customerRepository.findById(customer.getId());

        // Verify that the customer was fetched correctly
        assertTrue(fetchedCustomer.isPresent());
        assertEquals("John", fetchedCustomer.get().getName());
        assertEquals("Doe", fetchedCustomer.get().getLastName());
        assertEquals("john.doe@example.com", fetchedCustomer.get().getEmail());
        assertEquals("1234567890", fetchedCustomer.get().getPhone_number());
        assertEquals(Status.Client, fetchedCustomer.get().getStatus());
    }
    @Test
    public void testAllArgsConstructor() {
        Customer customer = new Customer(1L, "John", "Doe", "john.doe@example.com", "1234567890", Status.Client);
        assertEquals(1L, customer.getId());
        assertEquals("John", customer.getName());
        assertEquals("Doe", customer.getLastName());
        assertEquals("john.doe@example.com", customer.getEmail());
        assertEquals("1234567890", customer.getPhone_number());
        assertEquals(Status.Client, customer.getStatus());
    }

    @Test
    public void testSettersAndGetters() {
        customer.setId(1L);
        customer.setName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhone_number("1234567890");
        customer.setStatus(Status.Client);

        assertEquals(1L, customer.getId());
        assertEquals("John", customer.getName());
        assertEquals("Doe", customer.getLastName());
        assertEquals("john.doe@example.com", customer.getEmail());
        assertEquals("1234567890", customer.getPhone_number());
        assertEquals(Status.Client, customer.getStatus());
    }

    @Test
    public void testNonNullEmail() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            customer.setEmail(null);
        });
        assertEquals("email is marked non-null but is null", exception.getMessage());
    }

    @Test
    public void testEmailNotNullInConstructor() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new Customer(1L, "John", "Doe", null, "1234567890", Status.Client);
        });
        assertEquals("email is marked non-null but is null", exception.getMessage());
    }
}