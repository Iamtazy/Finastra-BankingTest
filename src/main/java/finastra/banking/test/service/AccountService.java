package finastra.banking.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import finastra.banking.test.model.Account;
import finastra.banking.test.persist.AccountDao;

@Service
public class AccountService {
	
	public List<Account> getAccounts() {
		List<Account> accounts = AccountDao.getAccounts();
		return accounts;
	}

}
