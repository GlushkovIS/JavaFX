package looseweight.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import looseweight.animations.Shake;
import looseweight.handler.DatabaseHandler;
import looseweight.handler.User;
import looseweight.handler.Window;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AutorizationController {

    public static User user = new User();
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
    private ImageView imageLogin;
    @FXML
    private ImageView imagePassword;


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
                Shake userLoginWrongAnim = new Shake(loginField);
                Shake userPasswordWrongAnim = new Shake(passwordField);
                Shake imageLogin = new Shake(this.imageLogin);
                Shake imagePassword = new Shake(this.imagePassword);
                imageLogin.PlayAnim();
                imagePassword.PlayAnim();
                userLoginWrongAnim.PlayAnim();
                userPasswordWrongAnim.PlayAnim();
            }

        });
        singUpButton.setOnAction(actionEvent -> {
            Stage currentStage = (Stage) singUpButton.getScene().getWindow();
            currentStage.close();
            window.goToScene("SingUpWindow.fxml");
        });

    }

    /**
     * Авторизует пользователя, в случае некорректных данных воспроизводит анимацию
     */
    private void loginUser(String login, String password) throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        user.setLogin(login);
        user.setPassword(password);

        ResultSet resultSet = databaseHandler.getUser(user);

        int counter = 0;
        while (resultSet.next()) {
            counter++;
        }

        if (counter == 1) {

            ResultSet result = databaseHandler.getUser(user);
            // записывает имя клиента в объект (для привествия в основном окне)
            user.setName(result.getString("name"));
            result.close();

            // закрыть текущее окно
            Stage currentStage = (Stage) singUpButton.getScene().getWindow();
            currentStage.close();
            // открыть новое окно
            window.goToScene("MainWindow.fxml");

        } else {
            Shake userLoginWrongAnim = new Shake(loginField);
            Shake userPasswordWrongAnim = new Shake(passwordField);
            Shake imageLogin = new Shake(this.imageLogin);
            Shake imagePassword = new Shake(this.imagePassword);
            imageLogin.PlayAnim();
            imagePassword.PlayAnim();
            userLoginWrongAnim.PlayAnim();
            userPasswordWrongAnim.PlayAnim();
        }
    }


}
