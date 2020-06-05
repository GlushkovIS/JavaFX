package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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

    protected String period = "weekAgo";
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
    private Button backButton;
    @FXML
    private Text helloUser;
    @FXML
    private AreaChart<?, ?> chart;
    @FXML
    private CategoryAxis x;
    @FXML
    private NumberAxis y;
    @FXML
    private RadioButton threeDayAgoBtn;
    @FXML
    private RadioButton weekAgoBtn;
    @FXML
    private RadioButton monthAgoBtn;
    @FXML
    private RadioButton threeMonthAgoBtn;
    @FXML
    private RadioButton sixMonthAgoBtn;
    @FXML
    private RadioButton yearAgoBtn;
    @FXML
    private RadioButton allTimeAgoBtn;

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
                graph.buildWeightGraph(chart, this.period, getUserLogin());
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        });

        graph.buildWeightGraph(chart, this.period, getUserLogin());

        // Группа RadioBtn для изменения периода постраения графика
        ToggleGroup group = new ToggleGroup();

        threeDayAgoBtn.setToggleGroup(group);
        weekAgoBtn.setToggleGroup(group);
        monthAgoBtn.setToggleGroup(group);
        threeMonthAgoBtn.setToggleGroup(group);
        sixMonthAgoBtn.setToggleGroup(group);
        yearAgoBtn.setToggleGroup(group);
        allTimeAgoBtn.setToggleGroup(group);
        weekAgoBtn.setSelected(true);

        allTimeAgoBtn.setOnAction(actionEvent -> {
            this.period = "allTime";
            try {
                graph.buildWeightGraph(chart, this.period, getUserLogin());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        });
        yearAgoBtn.setOnAction(actionEvent -> {
            this.period = "yearAgo";
            try {
                graph.buildWeightGraph(chart, this.period, getUserLogin());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        });
        sixMonthAgoBtn.setOnAction(actionEvent -> {
            this.period = "sixMonthAgo";
            try {
                graph.buildWeightGraph(chart, this.period, getUserLogin());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        });
        threeMonthAgoBtn.setOnAction(actionEvent -> {
            this.period = "threeMonthAgo";
            try {
                graph.buildWeightGraph(chart, this.period, getUserLogin());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        });
        monthAgoBtn.setOnAction(actionEvent -> {
            this.period = "monthAgo";
            try {
                graph.buildWeightGraph(chart, this.period, getUserLogin());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        });
        weekAgoBtn.setOnAction(actionEvent -> {
            this.period = "weekAgo";
            try {
                graph.buildWeightGraph(chart, this.period, getUserLogin());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        });
        threeDayAgoBtn.setOnAction(actionEvent -> {
            this.period = "threeDayAgo";
            try {
                graph.buildWeightGraph(chart, this.period, getUserLogin());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        });

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
     * Получает логин пользователя из класса авторизации или регистрации
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
