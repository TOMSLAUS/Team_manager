package model;

public class PracticeAttendance {

	private int practiceId;
	private int playerId;
	private int line;
	
	public PracticeAttendance() {
	}

	
	
	public PracticeAttendance(Builder builder) {
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

		public PracticeAttendance build() {
			PracticeAttendance practice = new PracticeAttendance(this);
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