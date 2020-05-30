package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    void initialize() {


    }
}
