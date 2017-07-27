package finastra.banking.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import finastra.banking.test.model.Customer;
import finastra.banking.test.persist.CustomerDao;

@Service
public class CustomerService {
	
	@Autowired
	CustomerDao customerDao;

	public List<Customer> getCustomers() {
		List<Customer> customers = customerDao.getCustomers();
		return customers;
	}
	
	public Customer getCustomerById(int customerId) {
		Customer customer = customerDao.getCustomerById(customerId);
		return customer;
	}

}
