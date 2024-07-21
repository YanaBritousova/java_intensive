package org.example.dao.impl;

import org.example.dao.RecordDAO;
import org.example.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import org.example.models.Records;

public class RecordDAOImpl implements RecordDAO<Records,Long> {

    @Override
    public Optional<Records> get(Long id) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM record WHERE id=?")) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Records record = new Records();
                record.setId(rs.getLong("id"));
                record.setIdClient(rs.getLong("client_id"));
                record.setIdService(rs.getLong("service_id"));
                record.setDate(rs.getDate("date"));
                return Optional.of(record);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void update(Long id, Date date) {
        String updateQuery = "UPDATE record SET date = (?) WHERE id = (?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(updateQuery)) {
            ps.setDate(1, new java.sql.Date(date.getTime()));
            ps.setLong(2,id);
            ps.executeUpdate();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void save(Records record) {
        String insertQuery = "INSERT INTO record " + "(client_id,service_id,date) VALUES " + "(?,?,?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(insertQuery)) {
            ps.setLong(1,record.getIdClient());
            ps.setLong(2,record.getIdService());
            ps.setDate(3, new java.sql.Date(record.getDate().getTime()));

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String deleteQuery = "DELETE FROM record WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(deleteQuery)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
