package model;

import java.time.LocalDate;
import java.util.ArrayList;

import controller.LoginController;
import javafx.scene.control.DatePicker;
import model.practiceAttendance.Builder;

public class PracticeAttendance {

	private int practiceId;
	private int playerId;
	private int line;
	
	public practiceAttendance() {
	}

	
	
	public practiceAttendance(Builder builder) {
		this.practiceId = builder.practiceId;
		this.playerId = builder.playerId;
		this.line = builder.line;
	}
	
	public static class Builder {
		private int practiceId;
		private int playerId;
		private int line;

		public Builder setPracticeId(int practiceId) {
			this.practiceId = practiceId;
			return this;
		}

		public Builder setPlayerId(int playerId) {
			this.playerId = playerId;
			return this;
		}

		public Builder setLine(int line) {
			this.line = line;
			return this;
		}

		public practiceAttendance build() {
			practiceAttendance practice = new practiceAttendance(this);
			return practice;
		}

	}

	public int getPracticeId() {
		return this.practiceId;
	}

	public void setPracticeId(int practiceId) {
		this.practiceId = practiceId;
	}
	
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	
	public int getPlayerId() {
		return this.playerId;
	}

	public int getLine() {
		return this.line;
	}

	public void setLine(int line) {
		this.line = line;
	}
	
}