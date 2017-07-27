package finastra.banking.test.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import finastra.banking.test.model.Account;
import finastra.banking.test.model.Customer;

@Component
public class AccountDao {
	
	private Connection connection = null;
    private Statement st=null;
    private ResultSet rs=null;
    private DataSource dss;
	
	
	public void	connection() throws SQLException{
    	try {
    		
    		Class.forName("com.mysql.jdbc.Driver");
    		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/finastratest?verifyServerCertificate=false&useSSL=true", "root","finastra");
    		System.out.println("siker");
    	  } catch (Exception e) {
    		
    		System.out.println(e.getMessage());
    	  }
    }

	public List<Account> getAccounts() {
		List<Account> accounts = new ArrayList<Account>();
		try {
			connection();
			PreparedStatement prepStat = connection.prepareStatement("select * from account");
			rs = prepStat.executeQuery();
			while (rs.next()) {
				Account tempAccount = new Account();
				tempAccount.setAccountNumber(rs.getInt("accountNumber"));
				tempAccount.setBalance(rs.getDouble("balance"));
				tempAccount.setCurrency(rs.getString("currency"));
				tempAccount.setCustomer(rs.getInt("customer"));
				accounts.add(tempAccount);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}
	
	public Account getAccountByAccountNumber(int accountNumber) {
		Account tempAccount = new Account();
		try {
			connection();
			PreparedStatement prepStat = connection.prepareStatement("select * from account where accountNumber ='" + accountNumber + "'");
			rs = prepStat.executeQuery();
			while (rs.next()) {
				tempAccount.setAccountNumber(rs.getInt("accountNumber"));
				tempAccount.setBalance(rs.getDouble("balance"));
				tempAccount.setCurrency(rs.getString("currency"));
				tempAccount.setCustomer(rs.getInt("customer"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tempAccount;
	}
	
	public boolean withdrawFromAccount(int accountNumber, double amount) {
		try {
			connection();
			PreparedStatement prepStat = connection.prepareStatement("update account set balance = (balance - '"+amount+"') where accountNumber = '"+accountNumber+"'");
			prepStat.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
