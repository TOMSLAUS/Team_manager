package DAO;

import model.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlayerDAO implements DAO<Player> {

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
        } catch (SQLException e) {
            //Main.showError("Error 0006", "Please try again.");
            e.printStackTrace();
        }
    }

    @Override
    public void save(Player player){
        PreparedStatement statement = null;

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
        }
    }
}
