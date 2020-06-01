package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.animations.Shake;
import sample.handler.DatabaseHandler;
import sample.handler.InputData;
import sample.handler.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainWindowController {

    Window window = new Window();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField weightInput;

    @FXML
    private Button sendWeigthButton;

    @FXML
    private Button chengeDataButton;

    @FXML
    private Button settingButton;

    @FXML
    private Button backButton;

    @FXML
    private Text helloUser;

    @FXML
    void initialize() {
        helloUser.setText("Привет, " + getUserName());
        backButton.setOnAction(actionEvent -> {
            // закрыть текущее окно
            Stage currentStage = (Stage) backButton.getScene().getWindow();
            currentStage.close();
            // открыть новое окно
            window.goToScene("AuthWindow.fxml");
        });
        sendWeigthButton.setOnAction(actionEvent -> {
            try {
                writeWeight();
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    /**
     * Получает имя пользователя из класса авторизации или регистрации
     * @return user.name
     */
    private String getUserName() {
        if (AutorizationController.user.getName() == null) {
            return SingUpController.user.getName();
        }
        return AutorizationController.user.getName();
    }

    /**
     * Полугчает логин пользователя из класса авторизации или регистрации
     * @return user.login
     */
    private String getUserLogin() {
        if (AutorizationController.user.getLogin() == null) {
            return SingUpController.user.getLogin();
        }
        return AutorizationController.user.getLogin();
    }

    /**
     * Проверяет на корректность данные веса и записывает в БД
     */
    private void writeWeight() throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        InputData checkInput = new InputData();
        String weight = weightInput.getText();

        if(checkInput.isFloat(weight)){
            databaseHandler.insertWeight(getUserLogin(), weight);
        } else {
            Shake weightAnim = new Shake(weightInput);
            weightAnim.PlayAnim();
        }

    }
}
