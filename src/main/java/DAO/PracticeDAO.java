package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import controller.LoginController;
import model.Player;
import model.Practice;
import model.User;

public class PracticeDAO {

	User user = LoginController.user;

	public void addPractice(Practice practice) {

		int lastPracticeId = 0;
		java.sql.Date sqlDate = Practice.convertToSqlDate(practice.getPracticeDate());
		Connection con = DAO.connect();
		PreparedStatement statement = null, statement2 = null, statement3 = null;
		Statement stm = null;
		ArrayList<ArrayList<Player>> players = practice.getPlayerList();

		try {

			statement = con.prepareStatement(
					"INSERT INTO Practice ( practiceDate ,numberOfLines, teamID) VALUES (?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			statement.setDate(1, sqlDate);
			System.out.println(sqlDate.toString());
			statement.setInt(2, practice.getPlayerListSize());
			statement.setInt(3, user.getTeamId());
			statement.executeUpdate();
			ResultSet rs2;
			ResultSet rs = statement.getGeneratedKeys();
			while (rs.next()) {
				lastPracticeId = Integer.parseInt(rs.getString(1));
				System.out.println(lastPracticeId);
			}

			for (int i = 0; i < players.size(); i++) {
				for (int j = 0; j < players.get(i).size(); j++) {

					try {
						statement = con.prepareStatement("INSERT INTO practiceAttendance (playerId , practiceId , Line) VALUES (?,?,?);");

						statement.setInt(1, players.get(i).get(j).getPlayerId());
						statement.setInt(2, lastPracticeId);
						statement.setInt(3, i + 1);
						statement.executeUpdate();

						statement2 = con.prepareStatement("SELECT pricePerTime , moneyOwed FROM playerMoney WHERE playerId=?;");
						statement2.setInt(1, players.get(i).get(j).getPlayerId());
						rs2=statement2.executeQuery();
		
						
						while (rs2.next()) {
							
						Float pricePerTime = rs2.getFloat(1);
						Float moneyOwed = rs2.getFloat(2);
						statement3 = con.prepareStatement("UPDATE playerMoney SET moneyOwed=? WHERE playerId=?;");
						statement3.setFloat(1, moneyOwed+pricePerTime);
						statement3.setInt(2, players.get(i).get(j).getPlayerId());
						statement3.executeUpdate();
						System.out.println("okkk");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}