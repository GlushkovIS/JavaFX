package sample.handler;

import sample.config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandler extends Config {

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
}
