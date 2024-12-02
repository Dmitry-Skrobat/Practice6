package application.Interface;

import application.Communicator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import application.Number;


import java.io.IOException;

public class ChangeMaxOdd {

    @FXML
    private Button backMenu;

    @FXML
    private TextField changeMax;

    @FXML
    void initialize() {
        backMenu.setOnAction(actionEvent -> back());
    }

    private boolean handleSubmit() {
        try {
            String rootsInput = changeMax.getText();

            String request = "changeMaxOdd" + " " + rootsInput;
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
        if(handleSubmit()) {
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
