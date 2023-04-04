package job.list.jdbc.service.impl;

import job.list.domen.User;
import job.list.jdbc.service.UserService;
import job.list.jdbc.util.JdbcUtils;

import java.sql.*;
import java.util.Collection;

public class UserJDBCService implements UserService {

    @Override
    public User createUser(String name, String lastName, String passport) {
        User user = null;
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users  (name,last_name,passport) VALUES (?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, passport);
            int i = preparedStatement.executeUpdate();
            System.out.println("Добавлено " + i + " строк");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id,name,last_name,passport FROM users WHERE passport ='" + passport + "' ");
            resultSet.next();
            int id = resultSet.getInt("id");
            String new_name = resultSet.getString("name");
            String new_last_name = resultSet.getString("last_name");
            String new_passport = resultSet.getString("passport");
            user = new User(id, new_name, new_last_name, new_passport);

        } catch (SQLException e) {

        }
        return user;
    }

    @Override
    public Collection<User> findUserByPassport(String passport) {
        Collection<User> collection = null;
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id,name,last_name,passport FROM users WHERE passport = '" + passport + "' ");
            preparedStatement.setString(1, passport);
            ResultSet resultSet = preparedStatement.executeQuery();
            collection = extractUser(resultSet);
        } catch (SQLException e) {

        }
        return collection;
    }

    @Override
    public Collection<User> findUserByNameAndLastName(String name, String lastName) {
        Collection<User> collection = null;
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id,name,last_name,passport FROM users WHERE name='" + name + "' and last_name = '" + lastName + "'");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            ResultSet resultSet = preparedStatement.executeQuery();
            collection = extractUser(resultSet);
        } catch (SQLException e) {
        }
        return collection;
    }

    @Override
    public Collection<User> findUserByLastName(String lastName) {
        Collection<User> collection = null;
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id,name,last_name,passport FROM users WHERE  last_name = '" + lastName + "'");
            preparedStatement.setString(1, lastName);
            ResultSet resultSet = preparedStatement.executeQuery();
            collection = extractUser(resultSet);
        } catch (SQLException e) {
        }
        return collection;
    }

    @Override
    public void deleteUserByPassport(String passport) {
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE passport = ?");
            preparedStatement.setString(1, passport);
            int i = preparedStatement.executeUpdate();
            System.out.println("Удалено пользователей с таким паспортом: " + i);
        } catch (SQLException e) {
        }
    }

    @Override
    public User updateUser(String name, String lastName, String passport) {
        User user = null;
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users set name ='" + name + "' and name ='" + lastName + "' and name ='" + passport + "' WHERE id = ?");
            preparedStatement.setInt(1,1);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt("id");
            String new_name = resultSet.getString("name");
            String new_last_name = resultSet.getString("last_name");
            String new_passport = resultSet.getString("passport");
            user = new User(id,new_name,new_last_name,new_passport);
        }catch (SQLException e){

        }
        return user;
    }


    private Collection<User> extractUser(ResultSet resultSet) throws SQLException {

        Collection<User> collection = null;

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String last_name = resultSet.getString("last_name");
            String passport = resultSet.getString("passport");
            collection.add(new User(id, name, last_name, passport));
        }
        return collection;
    }


}
