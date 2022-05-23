package org.crud.repository.impl;

import org.crud.model.Label;
import org.crud.model.Writer;
import org.crud.repository.WriterRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlWriterRepositoryImpl extends MysqlGenericRepository implements WriterRepository {
    @Override
    public Writer getById(Long id) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM writer WHERE id = ?")) {

            Class.forName(JDBC_DRIVER);

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Writer writer = new Writer();
                writer.setId(resultSet.getLong(1));
                writer.setFirstName(resultSet.getString(2));
                writer.setLastName(resultSet.getString(3));
                return writer;
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: Writer(id: " + id + ") has not found");
        }

        return null;
    }

    @Override
    public List<Writer> getAll() {
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()) {

            Class.forName(JDBC_DRIVER);

            ResultSet resultSet = statement.executeQuery("SELECT * FROM writer");

            List<Writer> writers = new ArrayList<>();
            while (resultSet.next()) {
                Writer writer = new Writer();
                writer.setId(resultSet.getLong(1));
                writer.setFirstName(resultSet.getString(2));
                writer.setLastName(resultSet.getString(3));
                writers.add(writer);
            }

            return writers;

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: Can't get writers");
        }

        return null;
    }

    @Override
    public Writer save(Writer writer) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO writer(firstName, lastName) VALUES(?, ?)")) {

            Class.forName(JDBC_DRIVER);

            preparedStatement.setString(1, writer.getFirstName());
            preparedStatement.setString(2, writer.getLastName());
            preparedStatement.executeUpdate();

            return writer;

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: new writer has not saved");
        }

        return null;
    }

    @Override
    public Writer update(Writer writer) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE writer SET firstName = ?, lastName = ? WHERE id = ?")) {

            Class.forName(JDBC_DRIVER);

            preparedStatement.setString(1, writer.getFirstName());
            preparedStatement.setString(2, writer.getLastName());
            preparedStatement.setLong(3, writer.getId());
            preparedStatement.executeUpdate();

            return writer;

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: writer(id: " + writer.getId() + ") has not updated");
        }

        return null;
    }

    @Override
    public void deleteById(Long id) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM writer WHERE id = ?")) {

            Class.forName(JDBC_DRIVER);

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: writer (id: " + id + ")has not deleted");
        }
    }
}
