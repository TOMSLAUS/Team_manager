package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import application.MainController;

public class Player implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String name, lastName, jerseyNumber, phoneNumber, birthDayStr;
	private LocalDate birthDay, firstGame;
	private int playerId, gamesPlayed, playoffGamesPlayed, pricePerTime, moneyOwed;


	public Player() {
	}


	public Player(int playerId, String name, String lastName, String jerseyNumber, String phoneNumber, int gamesPlayed,
			int playoffGamesPlayed, LocalDate birthDay, String birthDayStr, int pricePerTime, int moneyOwed) {
		this.playerId = playerId;
		this.name = name;
		this.lastName = lastName;
		this.jerseyNumber = jerseyNumber;
		this.phoneNumber = phoneNumber;
		this.gamesPlayed = gamesPlayed;
		this.playoffGamesPlayed = playoffGamesPlayed;
		this.birthDay = birthDay;
		this.birthDayStr = birthDayStr;
		this.pricePerTime = pricePerTime;
		this.moneyOwed = moneyOwed;
	}


	///////////////////////
	////// Builder//////////
	///////////////////////
	public static class Builder {
		private int playerId;
		private String name;
		private String lastName;
		private String jerseyNumber;
		private String phoneNumber;
		private String birthDayStr;
		private LocalDate birthDay;
		private LocalDate firstGame;
		private int gamesPlayed;
		private int playoffGamesPlayed;
		private int pricePerTime;
		private int moneyOwed;


		public Builder setPlayerId(int playerId) {
			this.playerId = playerId;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder setJerseyNumber(String jerseyNumber) {
			this.jerseyNumber = jerseyNumber;
			return this;
		}

		public Builder setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}

		public Builder setBirthDayStr(String birthDayStr) {
			this.birthDayStr = birthDayStr;
			return this;
		}

		public Builder setBirthDay(LocalDate birthDay) {
			this.birthDay = birthDay;
			this.birthDayStr = birthDay.toString();
			return this;
		}

		public Builder setGamesPlayed(int gamesPlayed) {
			this.gamesPlayed = gamesPlayed;
			return this;
		}

		public Builder setPlayoffGamesPlayed(int playoffGamesPlayed) {
			this.playoffGamesPlayed = playoffGamesPlayed;
			return this;
		}

		public Builder setPricePerTime(int pricePerTime) {
			this.pricePerTime = pricePerTime;
			return this;
		}

		public Builder setMoneyOwed(int moneyOwed) {
			this.moneyOwed = moneyOwed;
			return this;
		}



		public Player build() {

			return new Player(playerId, name, lastName, jerseyNumber, phoneNumber, gamesPlayed, playoffGamesPlayed,
					birthDay, birthDayStr, pricePerTime, moneyOwed);
		}

	}
	////////////////////////////
	//////// Getters and Setters
	////////////////////////////
	////////////////////////////
	////////////////////////////

	public int getPlayerId() {
		return this.playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId=playerId;
	}



	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getJerseyNumber() {
		return this.jerseyNumber;
	}

	public void setJerseyNumber(String jerseyNumber) {
		this.jerseyNumber = jerseyNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}



	public void setBirthDayStr(String birthDayStr) {
		this.birthDayStr = birthDayStr;
	}

	public String getBirthDayStr() {
		return birthDayStr;
	}



	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
		this.birthDayStr = birthDay.toString();
	}

	public LocalDate getBirthDay() {
		return this.birthDay;
	}



	public void setPricePerTime(int pricePerTime) {
		this.pricePerTime = pricePerTime;
	}

	public int getPricePerTime() {
		return pricePerTime;
	}



	public void setMoneyOwed(int moneyOwed) {
		this.moneyOwed = moneyOwed;
	}

	public int getMoneyOwed() {
		return moneyOwed;
	}



	// Get connection from database
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




	// Adds new player to mysql database
	public void addNewPlayer() {

		PreparedStatement statement = null;

		try {
			statement = this.connect().prepareStatement(
					"INSERT INTO players (name, lastName,phoneNumber,jerseyNumber,birthDay,pricePerTime) VALUES (?,?,?,?,?,?)");
			statement.setString(1, this.name);
			statement.setString(2, this.lastName);
			// Picture statement should go here!!! #TODO
			statement.setString(3, this.phoneNumber);
			statement.setString(4, this.jerseyNumber);
			statement.setDate(5, Player.convertDate(this.birthDay));
			statement.setInt(6, this.pricePerTime);
			statement.executeUpdate();
		} catch (SQLException e) {
			MainController.showError("Error 0005", "Please try again.");

		}
	}

	public void editPlayer() {

		Connection connection;
		PreparedStatement statement = null;

		try {
			statement = this.connect().prepareStatement(
					"UPDATE players SET name=? , lastName=? , phoneNumber=? , jerseyNumber=? , birthDay=? WHERE playerId=?");
			;

			statement.setString(1, this.name);
			statement.setString(2, this.lastName);
			// Picture statement should go here!!! #TODO
			statement.setString(3, this.phoneNumber);
			statement.setString(4, this.jerseyNumber);
			statement.setDate(5, Player.convertDate(this.birthDay));
			statement.setInt(6, this.playerId);
			statement.executeUpdate();
		} catch (SQLException e) {
			MainController.showError("Error 0006", "Please try again.");
			e.printStackTrace();

		}
	}


	public static java.sql.Date convertDate(LocalDate date) {
		java.sql.Date sqlDate = java.sql.Date.valueOf(date);
		return sqlDate;
	}

	public void convertDateToStr(LocalDate date) {
		this.birthDayStr = date.toString();
	}



	public static void main(String args[]) {
		/*
		 * Player pl = new Player.Builder() .setName("tomssss") .setLastName("laaaa")
		 * .setPhoneNumber("2474584") .setJerseyNumber("54")
		 * 
		 * .build() ; System.out.println(pl.getName()); pl.birthDay= LocalDate.of( 1995
		 * , 03 , 27 ); pl.convertDate(pl.birthDay); System.out.println(pl.birthDay);
		 * pl.addNewPlayer();
		 * 
		 */}
}
