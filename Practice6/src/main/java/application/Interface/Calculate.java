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

public class Calculate {

    @FXML
    private Button backMenu;

    @FXML
    private Button calculate;

    @FXML
    private TextField inputX;

    @FXML
    void initialize() {
        backMenu.setOnAction(actionEvent -> back());
        calculate.setOnAction(actionEvent -> calculateNumber());
    }

    private void calculateNumber() {
        String inputNumber = inputX.getText();

        String request = "calculate" + " " + inputNumber;
        Communicator communicator = new Communicator("localhost", 9876);

        String serverResponse = communicator.sendRequest(request);

        if (serverResponse.startsWith("Ошибка")) {
            showAlert("Ошибка сервера", serverResponse);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Посчитать значение в точке");
            alert.setHeaderText("Значение в заданной точке");
            alert.setContentText(serverResponse);
            alert.showAndWait();

        }

    }

    private void back(){
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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
