package ru.ageev.mapper;

import ru.ageev.dao.CustomerDao;
import ru.ageev.models.Customer;

public class CustomerMapper {
    public static Customer getCustomer(CustomerDao customerDao) {
        Customer customer = new Customer();

        customer.setFirstName(customerDao.getName());
        customer.setLastName(customerDao.getLastname());

        return customer;
    }
}