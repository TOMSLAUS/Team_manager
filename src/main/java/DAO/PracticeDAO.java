package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import controller.LoginController;
import model.Practice;
import model.User;

public class PracticeDAO {

User user = LoginController.user;

public void addNewPractice2(Practice practice) {

	Connection con = DAO.connect();
	PreparedStatement statement = null, statement2 = null;
	Statement stm = null;
	int lastPracticeId = 0;
	java.sql.Date sqlDate = Practice.convertToSqlDate(practice.getPracticeDate());

	try {
		statement = con.prepareStatement("INSERT INTO Practice ( practiceDate ,numberOfLines, teamID) VALUES (?,?,?)",Statement.RETURN_GENERATED_KEYS);
		statement.setDate(1, sqlDate);
		System.out.println(sqlDate.toString());
		statement.setInt(2, 2);
		statement.setInt(3, user.getTeamId());
		statement.executeUpdate();

		stm = con.createStatement();
		
		
		
		 ResultSet res = statement.getGeneratedKeys();
	      while (res.next()) {
	        lastPracticeId=Integer.parseInt(res.getString(1));
	        System.out.println(lastPracticeId);
	      }
	      
	      
		ResultSet rs = stm.executeQuery("SELECT practiceId FROM Practice");
		while (rs.next()) {
			lastPracticeId = rs.getInt(1);
		}

		for (int i = 0; i < practice.players21.size(); i++) {

			statement2 = con.prepareStatement(
					"INSERT INTO practiceAttendance (playerId , practiceId , Line) VALUES (?,?,?);");
			statement2.setInt(1, practice.players21.get(i).getPlayerId());
			statement2.setInt(2, lastPracticeId);
			statement2.setInt(3, 1);
			statement2.executeUpdate();
		}
		for (int i = 0; i < practice.players22.size(); i++) {

			statement2 = con.prepareStatement(
					"INSERT INTO practiceAttendance (	playerId ,practiceId, Line) VALUES (?,?,?);");
			statement2.setInt(1, practice.players22.get(i).getPlayerId());
			statement2.setInt(2, lastPracticeId);
			statement2.setInt(3, 2);
		
			statement2.executeUpdate();

		}

	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void addNewPractice3(Practice practice) {

	Connection con = DAO.connect();
	PreparedStatement statement = null, statement2 = null;
	Statement stm = null;
	int lastPracticeId = 0;
	java.sql.Date sqlDate = Practice.convertToSqlDate(practice.getPracticeDate());

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

		for (int i = 0; i < practice.players31.size(); i++) {

			statement2 = con.prepareStatement(
					"INSERT INTO practiceAttendance (	playerId , practiceId , Line) VALUES (?,?,?);");
			statement2.setInt(1, practice.players31.get(i).getPlayerId());
			statement2.setInt(2, lastPracticeId);
			statement2.setInt(3, 1);
			statement2.executeUpdate();
		}
		for (int i = 0; i < practice.players32.size(); i++) {

			statement2 = con.prepareStatement(
					"INSERT INTO practiceAttendance (	playerId ,practiceId, Line) VALUES (?,?,?);");
			statement2.setInt(1, practice.players32.get(i).getPlayerId());
			statement2.setInt(2, lastPracticeId);
			statement2.setInt(3, 2);
			statement2.executeUpdate();

		}

		for (int i = 0; i < practice.players33.size(); i++) {

			statement2 = con.prepareStatement(
					"INSERT INTO practiceAttendance (	playerId ,practiceId, Line) VALUES (?,?,?);");
			statement2.setInt(1, practice.players33.get(i).getPlayerId());
			statement2.setInt(2, lastPracticeId);
			statement2.setInt(3, 3);
			statement2.executeUpdate();

		}

	} catch (Exception e) {
		e.printStackTrace();
	}
}

public void addNewPractice4(Practice practice) {

	Connection con = DAO.connect();
	PreparedStatement statement = null, statement2 = null;
	Statement stm = null;
	int lastPracticeId = 0;
	java.sql.Date sqlDate = Practice.convertToSqlDate(practice.getPracticeDate());

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

		for (int i = 0; i < practice.players41.size(); i++) {

			statement2 = con.prepareStatement(
					"INSERT INTO practiceAttendance (	playerId , practiceId , Line) VALUES (?,?,?);");
			statement2.setInt(1, practice.players41.get(i).getPlayerId());
			statement2.setInt(2, lastPracticeId);
			statement2.setInt(3, 1);
			statement2.executeUpdate();
		}
		for (int i = 0; i < practice.players42.size(); i++) {

			statement2 = con.prepareStatement(
					"INSERT INTO practiceAttendance (	playerId ,practiceId, Line) VALUES (?,?,?);");
			statement2.setInt(1, practice.players42.get(i).getPlayerId());
			statement2.setInt(2, lastPracticeId);
			statement2.setInt(3, 2);
			statement2.executeUpdate();

		}

		for (int i = 0; i < practice.players43.size(); i++) {

			statement2 = con.prepareStatement(
					"INSERT INTO practiceAttendance (	playerId ,practiceId, Line) VALUES (?,?,?);");
			statement2.setInt(1, practice.players43.get(i).getPlayerId());
			statement2.setInt(2, lastPracticeId);
			statement2.setInt(3, 3);
			statement2.executeUpdate();

		}

		for (int i = 0; i < practice.players44.size(); i++) {

			statement2 = con.prepareStatement(
					"INSERT INTO practiceAttendance (	playerId ,practiceId, Line) VALUES (?,?,?);");
			statement2.setInt(1, practice.players44.get(i).getPlayerId());
			statement2.setInt(2, lastPracticeId);
			statement2.setInt(3, 4);
			statement2.executeUpdate();

		}

	} catch (Exception e) {
		e.printStackTrace();
	}
}

}
