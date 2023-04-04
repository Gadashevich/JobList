package job.list.jdbc.service.impl;

import job.list.domen.Position;
import job.list.jdbc.service.PositionService;
import job.list.jdbc.util.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class PositionJDBCService implements PositionService {

    @Override
    public Position createPosition(String name) {
        Position position = null;
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO positions (name) VALUES (?)");
            preparedStatement.setString(1, name);
            int rowUpdate = preparedStatement.executeUpdate();
            System.out.println("Кол-во добавленных строк " + rowUpdate);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name FROM positions WHERE name='" + name + "'  ");
            resultSet.next();
            int id = resultSet.getInt("id");
            String positionName = resultSet.getString("name");
            System.out.println("Должность id = " + id + " имя = " + name);
            position = new Position(id, positionName);

        } catch (SQLException e) {

        }
        return position;
    }

    @Override
    public void deletePositionById(int id) {
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM position WHERE id = ?");
            preparedStatement.setInt(1, id);
            int i = preparedStatement.executeUpdate();
            System.out.println("Удалено строк -" + i);
        } catch (SQLException e) {

        }
    }

    @Override
    public void deletePositionByName(String name) {
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM position WHERE name = ?");
            preparedStatement.setString(1, name);
            int i = preparedStatement.executeUpdate();
            System.out.println("Удалено строк -" + i);
        } catch (SQLException e) {

        }
    }

    @Override
    public Collection<Position> findAllPosition() {
        Collection<Position> collection = null;
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("Select id,name FROM position");
            ResultSet resultSet = preparedStatement.executeQuery();
            collection = extractPosition(resultSet);
        } catch (SQLException e) {

        }
        return collection;
    }

    @Override
    public Collection<Position> findAllPositionWithNameLike(String name) {
        Collection<Position> collection = null;
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("Select id,name FROM position WHERE name like ?");
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            collection = extractPosition(resultSet);
        } catch (SQLException e) {

        }
        return collection;
    }

    @Override
    public Collection<Position> findAllPositionById(int id) {
        Collection<Position> collection = null;
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("Select id,name FROM position WHERE id= ?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            collection = extractPosition(resultSet);
        } catch (SQLException e) {

        }
        return collection;
    }

    @Override
    public Collection<Position> findPositionByName(String name) {
        Collection<Position> collection = null;
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("Select id,name FROM position WHERE name= ?");
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            collection = extractPosition(resultSet);
        } catch (SQLException e) {

        }
        return collection;
    }

    private Collection<Position> extractPosition(ResultSet resultSet) throws SQLException {
        Collection<Position> collection = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            collection.add(new Position(id, name));
        }
        return collection;

    }
}
