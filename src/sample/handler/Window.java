package sample.handler;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Window {

    /**
     * Переходит на другое окно программы по наименованию вида fxml
     */
    public void goToScene(String window) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/views/" + window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Loose Weight 1.1");
        stage.show();
    }

    /**
     * Переходит на другое окно программы по наименованию вида fxml c модальностью
     */
    public void goToSceneModal(String window) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/views/" + window));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Loose Weight 1.1");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
