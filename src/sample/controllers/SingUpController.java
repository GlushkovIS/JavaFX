package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.animations.Shake;
import sample.handler.DatabaseHandler;
import sample.handler.InputData;
import sample.handler.User;
import sample.handler.Window;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SingUpController {

    public static User user = new User();
    private final Window window = new Window();
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
    @FXML
    private Button singInButton;

    @FXML
    void initialize() {
        singUpFinishButton.setOnAction(actionEvent -> {
            if (singUpNewUser()) {
                Stage currentStage = (Stage)singUpFinishButton.getScene().getWindow();
                currentStage.close();
                window.goToScene("MainWindow.fxml");
            }
        });
        singInButton.setOnAction(actionEvent -> {
            Stage currentStage = (Stage)singInButton.getScene().getWindow();
            currentStage.close();
            window.goToScene("AuthWindow.fxml");
        });
    }

    /**
     * Регистрирует нового пользователя (добавляет в БД)
     * - в случае, если проверка данных завалена воспроизводит анимацию
     *
     * @return bool
     */
    private boolean singUpNewUser() {
        DatabaseHandler databaseHandler = new DatabaseHandler();

        if (checkInputData()) {
            try {
                databaseHandler.signUpUser(user);
                return true;
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        } else {
            Shake nameAnim = new Shake(singUpName);
            Shake surnameAnim = new Shake(singUpSurname);
            Shake countryAnim = new Shake(singUpCountry);
            Shake loginAnim = new Shake(singUpLoginField);
            Shake passwordAnim = new Shake(singUpPasswordField);
            nameAnim.PlayAnim();
            surnameAnim.PlayAnim();
            countryAnim.PlayAnim();
            loginAnim.PlayAnim();
            passwordAnim.PlayAnim();
        }
        return false;
    }

    /**
     * Проверяет данные в полях регистрации
     * - логин - любое, кроме пустого
     * - пароль - любое, кроме пустого
     * - имя - только буквы
     * - фамилия - только буквы
     * - страна - только буквы
     * Заполняет статический объект класса User
     *
     * @return bool
     */
    private boolean checkInputData() {
        InputData inputCheck = new InputData();

        String inputName = singUpName.getText();
        String inputSurname = singUpSurname.getText();
        String inputLogin = singUpLoginField.getText();
        String inputPassword = singUpPasswordField.getText();
        String inputCountry = singUpCountry.getText();
        String inputSex = "";

        if (manCheckBox.isSelected()) {
            inputSex = "Мужчина";
        } else if (womenCheckBox.isSelected()) {
            inputSex = "Женщина";
        } else {
            inputSex = "Не указано";
        }

        int error = 0;
        if (inputCheck.isText(inputName)) {
            error++;
        }
        if (inputCheck.isText(inputSurname)) {
            error++;
        }
        if (inputCheck.isText(inputCountry)) {
            error++;
        }
        if (inputPassword.isEmpty()) {
            error++;
        }
        if (inputLogin.isEmpty()) {
            error++;
        }

        if (error > 0) {
            error = 0;
            return false;
        } else {
            user.setName(inputName);
            user.setSurname(inputSurname);
            user.setLogin(inputLogin);
            user.setPassword(inputPassword);
            user.setCountry(inputCountry);
            user.setSex(inputSex);
            return true;
        }

    }
}
