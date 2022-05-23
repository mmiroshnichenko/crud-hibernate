package org.crud.repository.impl;

import org.crud.model.Label;
import org.crud.repository.LabelRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MysqlLabelRepositoryImpl extends MysqlGenericRepository implements LabelRepository {
    @Override
    public Label getById(Long id) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM label WHERE id = ?")) {

            Class.forName(JDBC_DRIVER);

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Label label = new Label();
                label.setId(resultSet.getLong(1));
                label.setName(resultSet.getString(2));
                return label;
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: Label(id: " + id + ") has not found");
        }

        return null;
    }

    @Override
    public List<Label> getAll() {
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()) {

            Class.forName(JDBC_DRIVER);

            ResultSet resultSet = statement.executeQuery("SELECT * FROM label");

            List<Label> labels = new ArrayList<>();
            while (resultSet.next()) {
                Label label = new Label();
                label.setId(resultSet.getLong(1));
                label.setName(resultSet.getString(2));
                labels.add(label);
            }

            return labels;

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: Can't get labels");
        }

        return null;
    }

    @Override
    public Label save(Label label) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO label(name) VALUES(?)")) {

            Class.forName(JDBC_DRIVER);

            preparedStatement.setString(1, label.getName());
            preparedStatement.executeUpdate();

            return label;

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: new label has not saved");
        }

        return null;
    }

    @Override
    public Label update(Label label) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE label SET name = ? WHERE id = ?")) {

            Class.forName(JDBC_DRIVER);

            preparedStatement.setString(1, label.getName());
            preparedStatement.setLong(2, label.getId());
            preparedStatement.executeUpdate();

            return label;

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: new label has not updated");
        }

        return null;
    }

    @Override
    public void deleteById(Long id) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM label WHERE id = ?")) {

            Class.forName(JDBC_DRIVER);

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: new label has not updated");
        }
    }

    @Override
    public List<Label> getByPostId(Long postId) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT l.id, l.name " +
                            "FROM label l " +
                            "INNER JOIN post_label pl ON pl.labelId = l.id " +
                            "WHERE pl.postId = ?")) {

            Class.forName(JDBC_DRIVER);

            preparedStatement.setLong(1, postId);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Label> labels = new ArrayList<>();
            while (resultSet.next()) {
                Label label = new Label();
                label.setId(resultSet.getLong(1));
                label.setName(resultSet.getString(2));
                labels.add(label);
            }

            return labels;

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: Can't get labels for post(id:" + postId);
        }

        return null;
    }

    @Override
    public List<Label> getByIds(List<Long> ids) {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM label WHERE id IN (" + ids.stream().map(String::valueOf)
                            .collect(Collectors.joining(",")) + ")")) {

            Class.forName(JDBC_DRIVER);

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Label> labels = new ArrayList<>();
            while (resultSet.next()) {
                Label label = new Label();
                label.setId(resultSet.getLong(1));
                label.setName(resultSet.getString(2));

                labels.add(label);
            }

            return labels;

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: Label has not found");
        }

        return null;
    }
}
