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

import finastra.banking.test.model.Customer;

@Component
public class CustomerDao {
	
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


	public List<Customer> getCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		List<Integer> accounts = new ArrayList<Integer>();
		try {
			connection();
			PreparedStatement prepStat = connection.prepareStatement("select * from customer");
			rs = prepStat.executeQuery();
			while (rs.next()) {
				PreparedStatement prepStat2 = connection.prepareStatement("select * from customer_x_account where customerID ='" + rs.getInt("customerID") + "'");
				ResultSet rs2 = prepStat2.executeQuery();
				Customer tempCustomer = new Customer();
				tempCustomer.setName(rs.getString("name"));
				tempCustomer.setCustomerID(rs.getInt("customerID"));
				tempCustomer.setAddress(rs.getString("address"));
				tempCustomer.setBirthday(rs.getDate("birthday"));
				while (rs2.next()) {
					accounts.add(rs2.getInt("accountNumber"));
				}
				tempCustomer.setAccounts(accounts);
				customers.add(tempCustomer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}
	
	public Customer getCustomerById(int customerId) {
		Customer tempCustomer = new Customer();
		try {
			connection();
			PreparedStatement prepStat = connection.prepareStatement("select * from customer where customerID ='" + customerId + "'");
			rs = prepStat.executeQuery();
			PreparedStatement prepStat2 = connection.prepareStatement("select * from customer_x_account where customerID ='" + customerId + "'");
			ResultSet rs2 = prepStat2.executeQuery();
			while (rs.next()) {
				tempCustomer.setCustomerID(rs.getInt("customerID"));
				tempCustomer.setAddress(rs.getString("address"));
				tempCustomer.setName(rs.getString("name"));
				tempCustomer.setBirthday(rs.getDate("birthday"));
			}
			while (rs2.next()) {
				List<Integer> accounts = new ArrayList<Integer>();
				accounts.add(rs2.getInt("accountNumber"));
				tempCustomer.setAccounts(accounts);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tempCustomer;
	}

}
