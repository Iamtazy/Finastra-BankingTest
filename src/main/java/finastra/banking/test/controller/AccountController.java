package finastra.banking.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import finastra.banking.test.model.Account;
import finastra.banking.test.service.AccountService;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	
	@RequestMapping(value = "listAccounts")
	public List<Account> listAccounts(){
		System.out.println(accountService.getAccountByAccountNumber(1234));
		System.out.println(accountService.getAccounts().get(0).toString());
		return accountService.getAccounts();
	}

}
