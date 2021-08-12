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

public class CustomerDeleteFormController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;
    public AnchorPane cusDelForContext;

    public void customerDeleteOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        /*Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ThogaKade", "root", "1234");*/
        Statement stm = DbConnection.getInstance().getConnection().createStatement();
        String query = "DELETE FROM Customer WHERE id='" + txtId.getText() + "'";
        /*int i = stm.executeUpdate(query);*/
//        boolean isSaved = stm.executeUpdate(query)>0;
        if (delete(txtId.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure You Want To Delete This Customer").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

    }

    public void searchCustomerOnAction(ActionEvent actionEvent) {
        try {
            /*Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/ThogaKade",
                    "root",
                    "1234"*/

            Statement stm= DbConnection.getInstance().getConnection().createStatement();
            String query = "SELECT * FROM Customer WHERE id='"+txtId.getText()+"'";
            ResultSet rst = stm.executeQuery(query);
            if (rst.next()){
                Customer c1 = new Customer(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getDouble(4)
                );

                setData(c1);
            }else{
                new Alert(Alert.AlertType.WARNING,"Empty Result").show();
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public void openDashBoardForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) cusDelForContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    void  setData (Customer c){
        txtId.setText(c.getId());
        txtName.setText(c.getName());
        txtAddress.setText(c.getAddress());
        txtSalary.setText(String.valueOf(c.getSalary()));

    }
    boolean delete(String id) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Customer WHERE id='"+id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }


}
