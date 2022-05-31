package org.crud.repository.impl;

import org.crud.model.Writer;
import org.crud.repository.WriterRepository;
import org.crud.utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcWriterRepositoryImpl implements WriterRepository {
    @Override
    public Writer getById(Long id) {
        try(PreparedStatement preparedStatement = JdbcUtils.getPreparedStatement("SELECT * FROM writer WHERE id = ?")) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Writer writer = new Writer();
                writer.setId(resultSet.getLong(1));
                writer.setFirstName(resultSet.getString(2));
                writer.setLastName(resultSet.getString(3));
                return writer;
            }

        } catch (SQLException e) {
            System.err.println("Error: Writer(id: " + id + ") has not found");
        }

        return null;
    }

    @Override
    public List<Writer> getAll() {
        try(PreparedStatement preparedStatement = JdbcUtils.getPreparedStatement("SELECT * FROM writer")) {

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Writer> writers = new ArrayList<>();
            while (resultSet.next()) {
                Writer writer = new Writer();
                writer.setId(resultSet.getLong(1));
                writer.setFirstName(resultSet.getString(2));
                writer.setLastName(resultSet.getString(3));
                writers.add(writer);
            }

            return writers;

        } catch (SQLException e) {
            System.err.println("Error: Can't get writers");
        }

        return null;
    }

    @Override
    public Writer save(Writer writer) {
        try(PreparedStatement preparedStatement = JdbcUtils.getPreparedStatement("INSERT INTO writer(firstName, lastName) VALUES(?, ?)")) {

            preparedStatement.setString(1, writer.getFirstName());
            preparedStatement.setString(2, writer.getLastName());
            preparedStatement.executeUpdate();

            return writer;

        } catch (SQLException e) {
            System.err.println("Error: new writer has not saved");
        }

        return null;
    }

    @Override
    public Writer update(Writer writer) {
        try(PreparedStatement preparedStatement = JdbcUtils.getPreparedStatement("UPDATE writer SET firstName = ?, lastName = ? WHERE id = ?")) {

            preparedStatement.setString(1, writer.getFirstName());
            preparedStatement.setString(2, writer.getLastName());
            preparedStatement.setLong(3, writer.getId());
            preparedStatement.executeUpdate();

            return writer;

        } catch (SQLException e) {
            System.err.println("Error: writer(id: " + writer.getId() + ") has not updated");
        }

        return null;
    }

    @Override
    public void deleteById(Long id) {
        try(PreparedStatement preparedStatement = JdbcUtils.getPreparedStatement("DELETE FROM writer WHERE id = ?")) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error: writer (id: " + id + ")has not deleted");
        }
    }
}
