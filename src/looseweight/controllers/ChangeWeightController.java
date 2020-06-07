package looseweight.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import looseweight.handler.Const;
import looseweight.handler.DatabaseHandler;
import looseweight.handler.InputData;
import looseweight.handler.TableData;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ChangeWeightController {

    private final DatabaseHandler db = new DatabaseHandler();
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button backButton;
    @FXML
    private TableView<TableData> tableWeight;
    @FXML
    private TableColumn<TableData, String> dateCol;
    @FXML
    private TableColumn<TableData, String> weightCol;

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        backButton.setOnAction(actionEvent -> {
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();
        });

        ResultSet resultSet = db.getUserWeightForPeriod(MainWindowController.getUserLogin(), "allTime");

        // создаем список объектов
        ObservableList<TableData> weightData = FXCollections.observableArrayList();
        while (resultSet.next()) {
            weightData.add(new TableData(resultSet.getString(Const.DATE_WEIGHT), resultSet.getString(Const.WEIGHT_WEIGHT)));
        }

        // определяем фабрику для столбца с привязкой к свойству date
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        // столбец для вывода веса
        weightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        tableWeight.setItems(weightData);

        //логика обработки изменения веса в таблице
        weightCol.setCellFactory(TextFieldTableCell.forTableColumn());
        weightCol.setOnEditCommit(tableDataStringCellEditEvent -> {
            TableData tableData = tableDataStringCellEditEvent.getRowValue();
            String newWeight = tableDataStringCellEditEvent.getNewValue();
            InputData inputData = new InputData();

            String checkedNewWeight;
            if (inputData.isNumeric(newWeight)) {

                checkedNewWeight = inputData.stringWeightProcessing(newWeight);

                try {
                    db.updateWeight(tableData, checkedNewWeight, MainWindowController.getUserLogin());
                } catch (SQLException | ClassNotFoundException throwables) {
                    throwables.printStackTrace();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Некорректно указан вес");
                alert.setHeaderText(null);
                alert.setContentText("Укажите, пожалуйста, целое или дробное число и нажмите \"Enter\"");

                alert.showAndWait();
            }

        });

    }

}
