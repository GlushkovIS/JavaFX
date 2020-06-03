package sample.handler;

import sample.config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DatabaseHandler extends Config {

    /**
     * Получаем объект подключения к БД
     * @return Connection
     */
    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(this.DATABASE_ADDRESS);
            return connection;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Инсерт данных клиента в БД
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

    /**
     * Записывает вес в БД
     */
    public void insertWeight(String login, String weight) throws SQLException, ClassNotFoundException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String dateFormat = simpleDateFormat.format(date);



        String insert = "INSERT INTO " + Const.WEIGHT_TABLE + "(" + Const.DATE_WEIGHT + "," +
                Const.WEIGHT_WEIGHT + "," + Const.LOGIN_WEIGHT + ")" + "VALUES(?,?,?)";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
        preparedStatement.setString(1, dateFormat);
        preparedStatement.setString(2, weight);
        preparedStatement.setString(3, login);

        preparedStatement.executeUpdate();
    }

    /**
     * Получает вес в БД
     */
    public ResultSet getUserWeight(String login, int numberOfWrite) throws SQLException, ClassNotFoundException {

        String select = "SELECT * FROM (SELECT * FROM " + Const.WEIGHT_TABLE + " WHERE " + Const.LOGIN_WEIGHT + " = ? " + "ORDER BY" + " id " + "DESC LIMIT " + numberOfWrite + ") " + "sub " +
                "ORDER BY id";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
        preparedStatement.setString(1, login);

        return preparedStatement.executeQuery();
    }
}
