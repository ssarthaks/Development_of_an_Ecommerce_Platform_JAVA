package utils;



import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;



import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;



public class DatabaseConnectivity {

	//private static BasicDataSource dataSource;

	

	private static Connection connection;

	

	public static Connection getDbConnection() throws ClassNotFoundException, SQLException

	{

		Class.forName("com.mysql.cj.jdbc.Driver");

		String url="jdbc:mysql://localhost:3306/ecommerce";

		String user="root";

		String password="";

		connection=DriverManager.getConnection(url,user,password);

		return connection;

		

	}

	/*

	 * static { dataSource = new BasicDataSource();

	 * dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

	 * dataSource.setUrl("jdbc:mysql://localhost:3306/wearwell");

	 * dataSource.setUsername("root"); dataSource.setPassword(""); } public static

	 * Connection getDatabaseConnection() throws SQLException { connection =

	 * dataSource.getConnection(); return connection; }

	 */

}