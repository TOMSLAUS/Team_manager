package model;

import model.Player;
import model.Player.PlayerBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import DAO.DAO;
import controller.LoginController;

public class Practice {

	private int practiceId;
	private LocalDate practiceDate;
	private int numberOfLines;
	public ArrayList<Player> players41 = new ArrayList<Player>();
	public ArrayList<Player> players42 = new ArrayList<Player>();
	public ArrayList<Player> players43 = new ArrayList<Player>();
	public ArrayList<Player> players44 = new ArrayList<Player>();
	public ArrayList<Player> players31 = new ArrayList<Player>();
	public ArrayList<Player> players32 = new ArrayList<Player>();
	public ArrayList<Player> players33 = new ArrayList<Player>();
	public ArrayList<Player> players21 = new ArrayList<Player>();
	public ArrayList<Player> players22 = new ArrayList<Player>();
	User user = LoginController.user;
	
	public Practice() {
	}

	public Practice(Builder builder) {
		this.practiceId = builder.practiceId;
		this.practiceDate = builder.practiceDate;
		this.numberOfLines = builder.numberOfLines;
	}
	
	public static class Builder {
		private int practiceId;
		private LocalDate practiceDate;
		private int numberOfLines;

		public Builder setPracticeId(int practiceId) {
			this.practiceId = practiceId;
			return this;
		}

		public Builder setPracticeDate(LocalDate practiceDate) {
			this.practiceDate = practiceDate;
			return this;
		}

		public Builder setNumberOfLines(int numberOfLines) {
			this.numberOfLines = numberOfLines;
			return this;
		}

		public Practice build() {
			Practice practice = new Practice(this);
			// validatePlayerObject(player);
			return practice;
		}

	}

	public int getPracticeId() {
		return this.practiceId;
	}

	public void setPracticeId(int practiceId) {
		this.practiceId = practiceId;
	}

	public int getNumberOfLines() {
		return this.numberOfLines;
	}

	public void setNumberOfLines(int numberOfLines) {
		this.numberOfLines = numberOfLines;
	}
	
	public LocalDate getPracticeDate() {
		return this.practiceDate;
	}

	public void setPracticeDate(LocalDate practiceDate) {
		this.practiceDate = practiceDate;
	}

	// #TODO SataisÄ«t datubÄ?zÄ“ trigerus un lai ieliekas pareizi datubÄ?zÄ“.
	public void addNewPractice2(LocalDate practiceDatePicker) {

		Connection con = DAO.connect();
		PreparedStatement statement = null, statement2 = null;
		Statement stm = null;
		int lastPracticeId = 0;
		java.sql.Date sqlDate = Practice.convertToSqlDate(practiceDatePicker);

		try {
			statement = con.prepareStatement("INSERT INTO Practice ( practiceDate ,numberOfLines, teamID) VALUES (?,?,?)");
			statement.setDate(1, sqlDate);
			statement.setInt(2, 2);
			statement.setInt(3, user.getTeamId());
			statement.executeUpdate();

			stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT practiceId FROM Practice");
			while (rs.next()) {
				lastPracticeId = rs.getInt(1);
			}

			for (int i = 0; i < this.players21.size(); i++) {

				statement2 = con.prepareStatement(
						"INSERT INTO practiceAttendance (playerId , practiceId , Line) VALUES (?,?,?);");
				statement2.setInt(1, players21.get(i).getPlayerId());
				statement2.setInt(2, lastPracticeId);
				statement2.setInt(3, 1);
				statement2.executeUpdate();
			}
			for (int i = 0; i < players22.size(); i++) {

				statement2 = con.prepareStatement(
						"INSERT INTO practiceAttendance (	playerId ,practiceId, Line) VALUES (?,?,?);");
				statement2.setInt(1, players22.get(i).getPlayerId());
				statement2.setInt(2, lastPracticeId);
				statement2.setInt(3, 2);
			
				statement2.executeUpdate();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addNewPractice3(LocalDate practiceDatePicker) {

		Connection con = DAO.connect();
		PreparedStatement statement = null, statement2 = null;
		Statement stm = null;
		int lastPracticeId = 0;
		java.sql.Date sqlDate = this.convertToSqlDate(practiceDatePicker);

		try {
			statement = con.prepareStatement("INSERT INTO Practice ( practiceDate ,numberOfLines, teamID) VALUES (?,?,?)");
			statement.setDate(1, sqlDate);
			statement.setInt(2, 3);
			statement.setInt(3, user.getTeamId());
			statement.executeUpdate();

			stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT practiceId FROM Practice");
			while (rs.next()) {
				lastPracticeId = rs.getInt(1);
			}

			for (int i = 0; i < this.players31.size(); i++) {

				statement2 = con.prepareStatement(
						"INSERT INTO practiceAttendance (	playerId , practiceId , Line) VALUES (?,?,?);");
				statement2.setInt(1, players31.get(i).getPlayerId());
				statement2.setInt(2, lastPracticeId);
				statement2.setInt(3, 1);
				statement2.executeUpdate();
			}
			for (int i = 0; i < players32.size(); i++) {

				statement2 = con.prepareStatement(
						"INSERT INTO practiceAttendance (	playerId ,practiceId, Line) VALUES (?,?,?);");
				statement2.setInt(1, players32.get(i).getPlayerId());
				statement2.setInt(2, lastPracticeId);
				statement2.setInt(3, 2);
				statement2.executeUpdate();

			}

			for (int i = 0; i < players33.size(); i++) {

				statement2 = con.prepareStatement(
						"INSERT INTO practiceAttendance (	playerId ,practiceId, Line) VALUES (?,?,?);");
				statement2.setInt(1, players33.get(i).getPlayerId());
				statement2.setInt(2, lastPracticeId);
				statement2.setInt(3, 3);
				statement2.executeUpdate();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addNewPractice4(LocalDate practiceDatePicker) {

		Connection con = DAO.connect();
		PreparedStatement statement = null, statement2 = null;
		Statement stm = null;
		int lastPracticeId = 0;
		java.sql.Date sqlDate = this.convertToSqlDate(practiceDatePicker);

		try {
			statement = con.prepareStatement("INSERT INTO Practice ( practiceDate ,numberOfLines, teamID) VALUES (?,?,?)");
			statement.setDate(1, sqlDate);
			statement.setInt(2, 4);
			statement.setInt(3, user.getTeamId());
			statement.executeUpdate();

			stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT practiceId FROM Practice");
			while (rs.next()) {
				lastPracticeId = rs.getInt(1);
			}

			for (int i = 0; i < this.players41.size(); i++) {

				statement2 = con.prepareStatement(
						"INSERT INTO practiceAttendance (	playerId , practiceId , Line) VALUES (?,?,?);");
				statement2.setInt(1, players41.get(i).getPlayerId());
				statement2.setInt(2, lastPracticeId);
				statement2.setInt(3, 1);
				statement2.executeUpdate();
			}
			for (int i = 0; i < players42.size(); i++) {

				statement2 = con.prepareStatement(
						"INSERT INTO practiceAttendance (	playerId ,practiceId, Line) VALUES (?,?,?);");
				statement2.setInt(1, players42.get(i).getPlayerId());
				statement2.setInt(2, lastPracticeId);
				statement2.setInt(3, 2);
				statement2.executeUpdate();

			}

			for (int i = 0; i < players43.size(); i++) {

				statement2 = con.prepareStatement(
						"INSERT INTO practiceAttendance (	playerId ,practiceId, Line) VALUES (?,?,?);");
				statement2.setInt(1, players43.get(i).getPlayerId());
				statement2.setInt(2, lastPracticeId);
				statement2.setInt(3, 3);
				statement2.executeUpdate();

			}

			for (int i = 0; i < players44.size(); i++) {

				statement2 = con.prepareStatement(
						"INSERT INTO practiceAttendance (	playerId ,practiceId, Line) VALUES (?,?,?);");
				statement2.setInt(1, players44.get(i).getPlayerId());
				statement2.setInt(2, lastPracticeId);
				statement2.setInt(3, 4);
				statement2.executeUpdate();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static java.sql.Date convertToSqlDate(LocalDate date) {
		return java.sql.Date.valueOf(date);
	}

	public static LocalDate convertToLocalDate(java.sql.Date date) {
		return date.toLocalDate();
	}

}
