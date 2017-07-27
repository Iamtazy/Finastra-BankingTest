package finastra.banking.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import finastra.banking.test.model.Account;
import finastra.banking.test.service.AccountService;

@Controller
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	
	@RequestMapping(value = "listAccounts")
	public List<Account> listAccounts(){
		System.out.println(accountService.getAccountByAccountNumberService(1234));
		System.out.println(accountService.getAccountsService().get(0).toString());
		return accountService.getAccountsService();
	}
	
	@RequestMapping(value = "withdrawMoney")
	public void withdrawFromAccountController(@RequestParam(value = "accNum", required=true) int accNum, @RequestParam(value = "amount", required=true) double amount){
		accountService.withdrawFromAccountService(accNum, amount);
	}

}
