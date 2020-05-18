package DAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.InputStream;
import java.util.Properties;


public interface DAO<T> {

	public static Connection connect() {
		String fileName = "src/main/resources/property/mysql.properties";
		String url, dbName, user, password;
		Connection connection = null;
		Properties properties = new Properties();

		try(InputStream in = new FileInputStream(fileName)){
			properties.load(in);
			url = properties.getProperty("db.url");
			dbName = properties.getProperty("db.name");
			user = properties.getProperty("db.user");
			password = properties.getProperty("db.password");

			try {
				connection = DriverManager.getConnection(url + dbName, user, password);
			}
			catch (SQLException e) {
				System.out.println("Error connecting to database!");
				e.printStackTrace();
			}
		}
		catch(IOException e){
			System.out.println("Error loading " + fileName + "!");
			e.printStackTrace();
		}
		return connection;
	}

	void update(T t, String[] params);
	void save(T t);

}
