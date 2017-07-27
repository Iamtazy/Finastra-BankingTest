package finastra.banking.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import finastra.banking.test.model.Customer;
import finastra.banking.test.service.CustomerService;


@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
	@RequestMapping(value = "listCustomers")
	public List<Customer> listAccounts(){
		System.out.println(customerService.getCustomers().get(0).toString());
		System.out.println(customerService.getCustomerById(1));
		return customerService.getCustomers();
	}


}
