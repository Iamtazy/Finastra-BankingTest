package finastra.banking.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import finastra.banking.test.model.Account;
import finastra.banking.test.persist.AccountDao;

@Service
public class AccountService {
	
	@Autowired
	private AccountDao accountDao;
	
	public List<Account> getAccountsService() {
		List<Account> accounts = accountDao.getAccounts();
		return accounts;
	}
	
	public Account getAccountByAccountNumberService(int accountNumber) {
		Account account = accountDao.getAccountByAccountNumber(accountNumber);
		return account;
	}
	
	public boolean withdrawFromAccountService(int accountNumber, double amount) {
		if (accountDao.withdrawFromAccount(accountNumber, amount))
			return true;
		return false;
	}
	
	public boolean addMoneyToAccountService(int accountNumber, double amount) {
		if (accountDao.addMoneyToAccount(accountNumber, amount))
			return true;
		return false;
	}

}
