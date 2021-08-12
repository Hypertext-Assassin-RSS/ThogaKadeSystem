package controller;

import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import views.tm.CustomerTm;
import views.tm.CustomerTm;

import java.sql.*;
import java.util.ArrayList;

public class CustomerSelectAllFormController {
    public TableView<CustomerTm> tblCustomer;
    public TableColumn colCustomerId;
    public TableColumn colCustomerName;
    public TableColumn colCustomerAddress;
    public TableColumn colCustomerSalary;

    public void initialize() {
        try {

            colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colCustomerSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

            loadAllCustomers();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void loadAllCustomers() throws ClassNotFoundException, SQLException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer");
        ResultSet rst = stm.executeQuery();
        ArrayList<Customer> customers = new ArrayList<>();
        while (rst.next()) {
            customers.add(new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4)
            ));
        }
        setCustomersToTable(customers);
    }

    private void setCustomersToTable(ArrayList<Customer> customers) {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();
        customers.forEach(e->{
            obList.add(
                    new CustomerTm(e.getId(),e.getName(),e.getAddress(),e.getSalary()));
        });
        tblCustomer.setItems(obList);
    }

}
