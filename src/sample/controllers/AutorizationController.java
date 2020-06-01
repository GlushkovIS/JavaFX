package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.animations.Shake;
import sample.handler.DatabaseHandler;
import sample.handler.User;
import sample.handler.Window;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AutorizationController {

    //хранить объект клиента
    public static User user = new User();
    //хэлпер перехода по окнам
    private final Window window = new Window();

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button authButton;
    @FXML
    private Button singUpButton;

    @FXML
    void initialize() {
        authButton.setOnAction(actionEvent -> {
            String loginText = loginField.getText().trim();
            String passwordText = passwordField.getText().trim();

            if (!loginText.equals("") && !passwordText.equals("")) {
                try {
                    loginUser(loginText, passwordText);
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }
            } else {
                System.out.println("Login and password is empty");
            }

        });

        // переход на окно регистрации
        singUpButton.setOnAction(actionEvent -> {
            singUpButton.getScene().getWindow().hide();
            window.goToScene("SingUpWindow.fxml");
        });
    }

    /**
     * Авторизует пользователя, в случае пустых полей воспроизводит анимацию
     */
    private void loginUser(String login, String password) throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        user.setLogin(login);
        user.setPassword(password);

        ResultSet resultSet = databaseHandler.getUser(user);

        // записывает имя клиента в объект (для привествия в основном окне)
        user.setName(resultSet.getString("name"));

        int counter = 0;
        while (resultSet.next()) {
            counter++;
        }

        if (counter == 1) {
            singUpButton.getScene().getWindow().hide();
            window.goToScene("MainWindow.fxml");
        } else {
            Shake userLoginWrongAnim = new Shake(loginField);
            Shake userPasswordWrongAnim = new Shake(passwordField);
            userLoginWrongAnim.PlayAnim();
            userPasswordWrongAnim.PlayAnim();
        }
    }


}
