package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

	
	
	public static Connection connect() {
		String url = "jdbc:mysql://sql240.main-hosting.eu:3306/", user = "u994816388_players",
				password = "parole123456789", db = "u994816388_players";
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url + db, user, password);

		} catch (SQLException e) {
			System.out.println("Error connecting to database!");
		}
		return connection;
	}
}
