package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDAO implements DAO<User> {

    @Override
    public void update(User player, String[] params){
        PreparedStatement statement = null;

        try {
            statement = DAO.connect().prepareStatement("UPDATE Teams SET name=? , lastName=? , phoneNumber=? , jerseyNumber=? , birthDay=? WHERE playerId=?");

          /* statement.setString(1, player.getName());
            statement.setString(2, player.getLastName());
            // Picture statement should go here!!!
            // TODO
            statement.setString(3, player.getPhoneNumber());
            statement.setString(4, player.getJerseyNumber());
            statement.setDate(5, Player.convertDate(player.getBirthDay()));
            statement.setInt(6, player.getPlayerId());
            statement.executeUpdate();*/
        } catch (SQLException e) {
            //Main.showError("Error 0006", "Please try again.");
            e.printStackTrace();
        }
    }

    @Override
    public void save(User user){
        PreparedStatement statement = null;
/*
        try {
            statement = DAO.connect().prepareStatement("INSERT INTO players (name, lastName,phoneNumber,jerseyNumber,birthDay,pricePerTime) VALUES (?,?,?,?,?,?)");
            statement.setString(1, player.getName());
            statement.setString(2, player.getLastName());
            // Picture statement should go here!!! #TODO
            statement.setString(3, player.getPhoneNumber());
            statement.setString(4, player.getJerseyNumber());
            statement.setDate(5, Player.convertDate(player.getBirthDay()));
            statement.setInt(6, player.getPricePerTime());
            statement.executeUpdate();
        } catch (SQLException e) {
            //Main.showError("Error 0005", "Please try again.");
            e.printStackTrace();
        }*/
    }
        
        public boolean getUser(User user) {
            PreparedStatement statement = null;
            
                    try {
                    	statement = DAO.connect().prepareStatement("SELECT * From Teams WHERE email=? AND password=?");
                    	statement.setString(1, user.getEmail());
                    	statement.setString(2, user.getPassword());
                    	ResultSet rs = statement.executeQuery(); 
                    	
                    	while(rs.next()) {
                    		System.out.println("username and password matches");
                    		
                    		user.setTeamId(rs.getInt(1));
                    		System.out.println(user.getTeamId());
                    		return true;
                    	}
                    	
                    } catch (SQLException e) {
                        //Main.showError("Error 0005", "Please try again.");
                        e.printStackTrace();
                    }
					return false;
        }

	
    }



