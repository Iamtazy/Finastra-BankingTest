package finastra.banking.test.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class CustomerDao {
	
	private Connection connection = null;
    private Statement st=null;
    private ResultSet rs=null;
    private DataSource dss;
    
    
	public void	connection() throws SQLException{
    	try {
    		
    		Class.forName("com.mysql.jdbc.Driver");
    		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/finastra_banking?verifyServerCertificate=false&useSSL=true", "root","finastra");
    		System.out.println("siker");
    	  } catch (Exception e) {
    		
    		System.out.println(e.getMessage());
    	  }
    }

}
