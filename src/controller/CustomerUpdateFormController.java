package controller;

import db.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.*;

public class CustomerUpdateFormController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;
    public AnchorPane cusUpdateForm;

    public void selectCustomerOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer WHERE id=?");
        stm.setObject(1, txtId.getText());
        ResultSet rst = stm.executeQuery();
        if (rst.next()){
            Customer c1= new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4)
            );
            setData(c1);
        }else{
            new Alert(Alert.AlertType.WARNING, "Empty Set").show();
        }

    }

    public void updateCustomerOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        Customer c1= new Customer(
                txtId.getText(),txtName.getText(),
                txtAddress.getText(),
                Double.parseDouble(txtSalary.getText())
        );


        if (update(c1))
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
        else
            new Alert(Alert.AlertType.WARNING,"Try Again").show();


    }

    boolean update(Customer c) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Customer SET name=?, address=?, salary=? WHERE id=?");
        stm.setObject(1,c.getName());
        stm.setObject(2,c.getAddress());
        stm.setObject(3,c.getSalary());
        stm.setObject(4,c.getId());
        return stm.executeUpdate()>0;
    }

    void setData(Customer c){
        txtId.setText(c.getId());
        txtName.setText(c.getName());
        txtAddress.setText(c.getAddress());
        txtSalary.setText(String.valueOf(c.getSalary()));
    }

    public void openDashBoardForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) cusUpdateForm.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
