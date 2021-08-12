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

public class CustomerSearchFormController {
    public TextField txtCustomerId;
    public TextField txtCustomerName;
    public TextField txtCustomerAddress;
    public TextField txtCustomerSalary;
    public AnchorPane sarFormContext;

    public void btnSearchOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {

        String customerId = txtCustomerId.getText();

       /*try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection con = DriverManager.getConnection(
                   "jdbc:mysql://localhost:3306/ThogaKade",
                   "root",
                   "1234"
           );
           Statement stm = con.createStatement();
           String query = "SELECT * FROM Customer WHERE id='"+customerId+"'";

           ResultSet set = stm.executeQuery(query);

           if (set.next()){
               // if data available
               *//*System.out.println(set);
               System.out.println(set.getString(1));
               System.out.println(set.getString("id"));*//*
               String tempId = set.getString(1);
               String tempName = set.getString(2);
               String tempAddress = set.getString(3);
               double tempSalary = set.getDouble(4);

               txtCustomerId.setText(tempId);
               txtCustomerName.setText(tempName);
               txtCustomerAddress.setText(tempAddress);
               *//*txtCustomerSalary.setText(tempSalary+"");*//*
               txtCustomerSalary.setText(String.valueOf(tempSalary));

           }else{
              new Alert(Alert.AlertType.WARNING,"Empty Result Set").show();
           }

       } catch (ClassNotFoundException | SQLException e) {
           e.printStackTrace();
       }*/


        /*Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ThogaKade",
                "root",
                "1234"
        );*/
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer WHERE id=?");
        stm.setObject(1, txtCustomerId.getText());

        ResultSet rst = stm.executeQuery();

        if (rst.next()) {
            /*txtCustomerId.setText();
            txtCustomerName.setText(rst.getString(2));
            txtCustomerAddress.setText(rst.getString(3));
            txtCustomerSalary.setText(String.valueOf(rst.getDouble(4)));
            */
            Customer c1 = new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4)
            );

            setData(c1);

        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        }


    }


    public void openDashBoardForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) sarFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    void  setData (Customer c){
        txtCustomerId.setText(c.getId());
        txtCustomerName.setText(c.getName());
        txtCustomerAddress.setText(c.getAddress());
        txtCustomerSalary.setText(String.valueOf(c.getSalary()));

    }
}
