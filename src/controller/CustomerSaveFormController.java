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

public class CustomerSaveFormController {
    public TextField txtName;
    public TextField txtId;
    public TextField txtAddress;
    public TextField txtSalary;
    public AnchorPane saveFormContext;

    public void saveCustomerOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());

//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/ThogaKade",
//                    "root",
//                    "1234"
//            );
//            Statement stm = con.createStatement();
//            String query = "Insert Into Customer VALUES ("+"'"+id+"'"+", "+"'"+name+"'"+", "+"'"+address+"'"+", "+"'"+salary+"'"+")";
//            int i = stm.executeUpdate(query);
//            if (i>0){
//                System.out.println("Saved");
//            }else{
//                System.out.println("Try Again");
//            }
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
       /* Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ThogaKade",
                "root",
                "1234"
        );*/
        Customer c1 = new Customer(
                txtId.getText(),txtName.getText(),
                txtAddress.getText(),Double.parseDouble(txtSalary.getText())
        );

        if(saveCustomer(c1))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
    }

    boolean saveCustomer(Customer c) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();

        String query="INSERT INTO Customer VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,c.getId());
        stm.setObject(2,c.getName());
        stm.setObject(3,c.getAddress());
        stm.setObject(4,c.getSalary());

        return stm.executeUpdate()>0;
    }

    public void openDashBoardForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) saveFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
