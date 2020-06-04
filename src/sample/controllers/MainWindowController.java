package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.animations.Shake;
import sample.handler.DatabaseHandler;
import sample.handler.Graph;
import sample.handler.InputData;
import sample.handler.Window;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainWindowController {

    Window window = new Window();
    Graph graph = new Graph();

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
    private LineChart<?, ?> chart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
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

            try {
                graph.buildWeightGraph(chart, "allTime", getUserLogin());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });

        graph.buildWeightGraph(chart, "allTime", getUserLogin());

    }

    /**
     * Получает имя пользователя из класса авторизации или регистрации
     *
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
     *
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

        if (checkInput.isFloat(weight)) {
            databaseHandler.insertWeight(getUserLogin(), weight);
        } else {
            Shake weightAnim = new Shake(weightInput);
            weightAnim.PlayAnim();
        }

    }
}
