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
				PreparedStatement prepStat2 = connection.prepareStatement("select * from customer_x_account where accountNumber ='" + rs.getInt("accountNumber") + "'");
				ResultSet rs2 = prepStat2.executeQuery();
				tempAccount.setAccountNumber(rs.getInt("accountNumber"));
				tempAccount.setBalance(rs.getDouble("balance"));
				tempAccount.setCurrency(rs.getString("currency"));
				if (rs2.next()) {
					tempAccount.setCustomer(rs2.getInt("customerID"));
				}
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
			PreparedStatement prepStat2 = connection.prepareStatement("select * from customer_x_account where accountNumber ='" + accountNumber + "'");
			ResultSet rs2 = prepStat2.executeQuery();
			while (rs.next()) {
				tempAccount.setAccountNumber(rs.getInt("accountNumber"));
				tempAccount.setBalance(rs.getDouble("balance"));
				tempAccount.setCurrency(rs.getString("currency"));
			}
			if (rs2.next()) {
				tempAccount.setCustomer(rs2.getInt("customerID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tempAccount;
	}

}
