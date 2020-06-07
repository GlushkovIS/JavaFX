package sample.handler;

import sample.config.Config;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DatabaseHandler extends Config {

    /**
     * Получаем объект подключения к БД
     *
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("y-MM-dd");
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
    public ResultSet getUserWeightForPeriod(String login, String timeFrame) throws SQLException, ClassNotFoundException {

        String select = "SELECT * FROM (SELECT * FROM " + Const.WEIGHT_TABLE + " WHERE " + Const.LOGIN_WEIGHT + " = ? AND " + Const.DATE_WEIGHT + " between ?" + " AND ?" + " ORDER BY" + " id " + ") sub " +
                " ORDER BY id";

        DateHandler dateHandler = new DateHandler();
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);

        switch (timeFrame) {
            case "threeDayAgo":
                preparedStatement.setString(2, dateHandler.getThreeDayAgo());
                break;
            case "weekAgo":
                preparedStatement.setString(2, dateHandler.getWeekAgo());
                break;
            case "monthAgo":
                preparedStatement.setString(2, dateHandler.getMonthAgo());
                break;
            case "threeMonthAgo":
                preparedStatement.setString(2, dateHandler.getThreeMonthAgo());
                break;
            case "sixMonthAgo":
                preparedStatement.setString(2, dateHandler.getSixMountAgo());
                break;
            case "yearAgo":
                preparedStatement.setString(2, dateHandler.getYearAgo());
                break;
            case "allTime":
                preparedStatement.setString(2, dateHandler.getUnixDate());
                break;
        }

        preparedStatement.setString(1, login);
        preparedStatement.setString(3, dateHandler.getNowFullDate());

        return preparedStatement.executeQuery();
    }

    /**
     * Обновляет вес в БД
     */
    public void updateWeight(TableData tableData, String weight, String login) throws SQLException, ClassNotFoundException {
        String update = "UPDATE " + Const.WEIGHT_TABLE + " SET " + Const.WEIGHT_WEIGHT + "=?" + " WHERE " + Const.DATE_WEIGHT + "=? AND " + Const.WEIGHT_WEIGHT + "=? AND " + Const.LOGIN_WEIGHT + "=?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);
        preparedStatement.setString(1, weight);
        preparedStatement.setString(2, tableData.getDate());
        preparedStatement.setString(3, tableData.getWeight());
        preparedStatement.setString(4, login);

        preparedStatement.executeUpdate();
    }

}