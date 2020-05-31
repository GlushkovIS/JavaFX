package sample.handler;

import sample.config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class DatabaseHandler extends Config {

    /**
     * Получаем объект подключения к БД
     * @return Connection
     */
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(this.DATABASE_ADDRESS);

            System.out.println("Connection to SQLite has been established.");
            return connection;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Инсерт на данных клиента в БД
     */
    public void signUpUser(User user) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.NAME_USER + "," +
                Const.SURNAME_USER + "," + Const.SEX_USER + "," + Const.COUNTRY_USER + "," +
                Const.LOGIN_USER + "," + Const.PASSWORD_USER + ")" + "VALUES(?,?,?,?,?,?)";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getSex());
        preparedStatement.setString(4, user.getCountry());
        preparedStatement.setString(5, user.getLogin());
        preparedStatement.setString(6, user.getPassword());

        preparedStatement.executeUpdate();
    }

    /**
     * Получает пользователе из БД по логину / паролю
     */
    public ResultSet getUser(User user) throws SQLException, ClassNotFoundException {

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.LOGIN_USER
                + " = ? AND " + Const.PASSWORD_USER + " = ?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());

        return preparedStatement.executeQuery();

    }
}
