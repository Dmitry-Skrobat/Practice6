package application.Interface;

import application.Communicator;
import application.Server;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import application.Number;

import java.io.IOException;

public class ChangeRoot {

    @FXML
    private Button backMenu;

    @FXML
    private TextField index;

    @FXML
    private TextField newRoot;


    @FXML
    void initialize() {
        backMenu.setOnAction(actionEvent -> back());
    }

    private boolean handleSubmit1() {
        try {
            String inputIndex = index.getText();
            String inputRoot = newRoot.getText();
            int index = Integer.parseInt(inputIndex);

            if(index<0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка ввода");
                alert.setHeaderText("Некорректные данные");
                alert.setContentText("Пожалуйста, убедитесь, что индекс введен корректно.");
                alert.showAndWait();
                return false;
            }

            String request = "changeRoot" + " " + inputIndex + " " + inputRoot;
            Communicator communicator = new Communicator("localhost", 9876);
            String serverResponse = communicator.sendRequest(request);
            if (serverResponse.startsWith("Ошибка")) {
                showAlert("Ошибка сервера", serverResponse);
                return false;
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Успех");
                alert.setContentText(serverResponse);
                alert.showAndWait();
                return true;
            }

        } catch (NumberFormatException e) {
            showAlert("Ошибка ввода", "Проверьте правильность ввода данных.");
            return false;
        }
    }

    private void back(){
        if(handleSubmit1()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/menu.fxml"));

                Scene scene = new Scene(loader.load());

                Stage stage = (Stage) backMenu.getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
