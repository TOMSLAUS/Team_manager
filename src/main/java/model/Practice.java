package model;

import model.Player;
import model.Player.PlayerBuilder;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import DAO.DAO;
import controller.LoginController;
import javafx.scene.control.DatePicker;

public class Practice {

	private int practiceId;
	private LocalDate practiceDate;
	private int numberOfLines;
	private ArrayList<ArrayList<Player>> players = new ArrayList();
	
	User user = LoginController.user;
	private DatePicker practiceDatePicker;
	public Practice() {
	}

	public ArrayList getPlayerList() {
		return players;
	}
	
	public int getPlayerListSize() {
		return players.size();
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
	
	public LocalDate GetPracticeDate() {
		return this.practiceDate;
	}

	public void addToPlayerList(ArrayList<Player> ... player) {
		for (ArrayList i : player) {
			players.add(i);
			//System.out.println(i.get(0));
		}
	}

	public static java.sql.Date convertToSqlDate(LocalDate date) {
		return java.sql.Date.valueOf(date);
	}

	public static LocalDate convertToLocalDate(java.sql.Date date) {
		return date.toLocalDate();
	}

}
