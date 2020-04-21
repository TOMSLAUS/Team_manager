package DAO;

import model.Player;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controller.LoginController;

public class PlayerDAO implements DAO<Player> {
	User user= LoginController.user;
	
    @Override
    public void update(Player player, String[] params){
        PreparedStatement statement = null;

        try {
            statement = DAO.connect().prepareStatement("UPDATE players SET name=? , lastName=? , phoneNumber=? , jerseyNumber=? , birthDay=? WHERE playerId=?");

            statement.setString(1, player.getName());
            statement.setString(2, player.getLastName());
            // Picture statement should go here!!!
            // TODO
            statement.setString(3, player.getPhoneNumber());
            statement.setString(4, player.getJerseyNumber());
            statement.setDate(5, Player.convertDate(player.getBirthDay()));
            statement.setInt(6, player.getPlayerId());
            statement.executeUpdate();

        } catch (Exception e) {
            //Main.showError("Error 0006", "Please try again.");
            e.printStackTrace();
        }
    }

    @Override
    public void save(Player player){
        PreparedStatement statement = null;
        int id=0;
        try {
        	statement = DAO.connect().prepareStatement("INSERT INTO players (teamId , name , lastName , phoneNumber , jerseyNumber , birthDay ) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, user.getTeamId());
            statement.setString(2, player.getName());
            statement.setString(3, player.getLastName());
            // Picture statement should go here!!! #TODO
            statement.setString(4, player.getPhoneNumber());
            statement.setString(5, player.getJerseyNumber());
            statement.setDate(6, Player.convertDate(player.getBirthDay()));  
            statement.executeUpdate();
                     
            ResultSet res = statement.getGeneratedKeys();
            while (res.next()) {
             id= Integer.parseInt(res.getString(1));
             }

            statement = DAO.connect().prepareStatement("INSERT INTO playerMoney (playerId, pricePerTime ) VALUES (?,?)");
            statement.setInt(1, id);
            statement.setInt(2, player.getPricePerTime());
            statement.executeUpdate();
        } catch (SQLException e) {
            //Main.showError("Error 0005", "Please try again.");
            e.printStackTrace();
        }
    }
}
