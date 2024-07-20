package org.example.dao;

import org.example.models.Client;
import org.example.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ClientDAOImpl implements ClientDAO<Client, Long> {

    @Override
    public Optional<Client> get(Long id) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM clients WHERE id=?")) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Client client = new Client();
                client.setId(rs.getLong("id"));
                client.setName(rs.getString("name"));
                return Optional.of(client); // Корректно оборачиваем в Optional
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void save(Client client) {
        String insertQuery = "INSERT INTO clients " + "(name) VALUES " + "(?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(insertQuery)) {
            ps.setString(1, client.getName());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void delete(Long id) {
        String deleteQuery = "DELETE FROM clients WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(deleteQuery)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
