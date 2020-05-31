package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.handler.DatabaseHandler;
import sample.handler.User;
import sample.handler.Window;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SingUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField singUpSurname;

    @FXML
    private PasswordField singUpPasswordField;

    @FXML
    private Button singUpFinishButton;

    @FXML
    private TextField singUpLoginField;

    @FXML
    private TextField singUpName;

    @FXML
    private CheckBox manCheckBox;

    @FXML
    private CheckBox womenCheckBox;

    @FXML
    private TextField singUpCountry;

    @FXML
    private CheckBox noCheckBox;

    private final Window window = new Window();

    @FXML
    void initialize() {
        singUpFinishButton.setOnAction(actionEvent -> {
            singUpNewUser();

            singUpFinishButton.getScene().getWindow().hide();
            window.goToScene("MainWindow.fxml");
        });

    }

    /**
     * Зарегистрировать нового пользователя
     */
    private void singUpNewUser() {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        String name = singUpName.getText();
        String surname = singUpSurname.getText();
        String login = singUpLoginField.getText();
        String password = singUpPasswordField.getText();
        String country = singUpCountry.getText();
        String sex = "";

        if (manCheckBox.isSelected()) {
            sex = "Мужчина";
        } else if (womenCheckBox.isSelected()) {
            sex = "Женщина";
        } else {
            sex = "Не указано";
        }

        User user = new User(name, surname, sex, country, login, password);

        try {
            databaseHandler.signUpUser(user);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

}
