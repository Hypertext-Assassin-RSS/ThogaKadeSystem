package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DashBoardFormController {
    public AnchorPane dbfController;

    public void openCustomerSaveForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/CustomerSaveForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) dbfController.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void openCustomerSearchForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/CustomerSearchForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) dbfController.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void openCustomerDeleteForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/CustomerDeleteForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) dbfController.getScene().getWindow();
        window.setScene(new Scene(load));
    }

//    public void openCustomerUpdateForm(ActionEvent actionEvent) throws IOException {
//        URL resource = getClass().getResource("../views/CustomerUpdateForm.fxml");
//        Parent load = FXMLLoader.load(resource);
//        Stage window = (Stage) dbfController.getScene().getWindow();
//        window.setScene(new Scene(load));
//    }

    public void openSelectAllForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/CustomerSelectAllForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) dbfController.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void openCustomerUpdateForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/CustomerUpdateForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) dbfController.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
