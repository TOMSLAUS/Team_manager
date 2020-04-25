package model;


import java.time.LocalDate;


public class Player implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private  String lastName;
	private  String jerseyNumber;
	private  String phoneNumber;
	private  String birthDayStr;
	private  LocalDate birthDay;
	private  int gamesPlayed;
	private  int playoffGamesPlayed;
	private  int pricePerTime;
	private  int moneyOwed;
	private final int playerId;
	private int line;
    //private final LocalDate firstGame; - idk what this is

	// Constructor
	public Player(PlayerBuilder builder) {
		this.playerId = builder.playerId;
		this.name = builder.name;
		this.lastName = builder.lastName;
		this.jerseyNumber = builder.jerseyNumber;
		this.phoneNumber = builder.phoneNumber;
		this.gamesPlayed = builder.gamesPlayed;
		this.playoffGamesPlayed = builder.playoffGamesPlayed;
		this.birthDay = builder.birthDay;
		this.birthDayStr = builder.birthDayStr;
		this.pricePerTime = builder.pricePerTime;
		this.moneyOwed = builder.moneyOwed;
	}

	// Getters
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	
	public String getLastName() {
		return lastName;
	}	
	public void setLastName(String lastname) {
		this.lastName=lastName;
	}
	
	
	public String getJerseyNumber() {
		return jerseyNumber;
	}
	public void setJerseyNumber(String jerseyNumber) {
		this.jerseyNumber=jerseyNumber;
	}
	
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber=phoneNumber;
	}
	
	
	public String getBirthDayStr() {
		return birthDayStr;
	}
	public void setBirthDayStr(String birthDayStr) {
		this.birthDayStr=birthDayStr;
	}
	
	
	public LocalDate getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(LocalDate birthDay) {
		this.birthDay=birthDay;
	}
	
	
	public int getPlayerId() {
		return playerId;
	}
	public int getGamesPlayed() { return gamesPlayed; }
	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed=gamesPlayed;
	}
	
	
	public int getPlayoffGamesPlayed() {
		return playoffGamesPlayed;
	}
	public void setPlayoffGamesPlayed(int playoffGamesPlayed) {
		this.playoffGamesPlayed=playoffGamesPlayed;
	}
	
	
	public int getPricePerTime() {
		return pricePerTime;
	}
	public void setPricePerTime(int pricePerTime) {
		this.pricePerTime=pricePerTime;
	}
	
	
	public int getMoneyOwed() {
		return moneyOwed;
	}
	public void setMoneyOwed(int moneyOwed) {
		this.moneyOwed=moneyOwed;
	}
	
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line=line;
	}

	////// Builder//////////

	public static class PlayerBuilder {
		// remember to declare playerID, name, lastName as FINAL
		// Reason: putting final tells compiler these fields are required and I think these fields are required
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

		// I'm leaving this to implement later - risbah
//		public PlayerBuilder(int playerId, String name, String lastName){
//			this.playerId = playerId;
//			this.name = name;
//			this.lastName = lastName;
//		}

		public PlayerBuilder setPlayerId(int playerId){
			this.playerId = playerId;
			return this;
		}

		public PlayerBuilder setName(String name){
			this.name = name;
			return this;
		}

		public PlayerBuilder setLastName(String lastName){
			this.lastName = lastName;
			return this;
		}

		public PlayerBuilder setJerseyNumber(String jerseyNumber) {
			this.jerseyNumber = jerseyNumber;
			return this;
		}

		public PlayerBuilder setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}

		public PlayerBuilder setBirthDayStr(String birthDayStr) {
			this.birthDayStr = birthDayStr;
			return this;
		}

		public PlayerBuilder setBirthDay(LocalDate birthDay) {
			this.birthDay = birthDay;
			this.birthDayStr = birthDay.toString();
			return this;
		}

		public PlayerBuilder setGamesPlayed(int gamesPlayed) {
			this.gamesPlayed = gamesPlayed;
			return this;
		}

		public PlayerBuilder setPlayoffGamesPlayed(int playoffGamesPlayed) {
			this.playoffGamesPlayed = playoffGamesPlayed;
			return this;
		}

		public PlayerBuilder setPricePerTime(int pricePerTime) {
			this.pricePerTime = pricePerTime;
			return this;
		}

		public PlayerBuilder setMoneyOwed(int moneyOwed) {
			this.moneyOwed = moneyOwed;
			return this;
		}



		public Player build() {
			Player player = new Player(this);
			// validatePlayerObject(player);
			return player;
		}

		// private void validatePlayerObject(Player player){}

	}


	public static java.sql.Date convertDate(LocalDate date) {	
		return java.sql.Date.valueOf(date);
	}
	
	
	public static LocalDate convertToLocalDate(java.sql.Date date) {
		 return date.toLocalDate();
	}

//	//public void convertDateToStr(LocalDate date) {
//		this.birthDayStr = date.toString();
//	}

}
