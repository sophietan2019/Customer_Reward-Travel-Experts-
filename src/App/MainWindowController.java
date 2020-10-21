package App;


import App.model.Customer;
import App.model.Customer_Reward;
import App.model.Datasource;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainWindowController {

     @FXML
     private BorderPane bpMain;

     @FXML
     private TableView<Customer> tvCustomers;

     @FXML
     private TableColumn<Customer,Integer> colCustomerId;

    @FXML
    private TableColumn<Customer,Integer>  colAgentId;


     @FXML
     private TableColumn<Customer,String> colCustFirstName;

    @FXML
    private TableColumn<Customer,String>  colCustLastName;

    @FXML
    private TableColumn<Customer,String>  colCustBusPhone;

    @FXML
    private TableColumn<Customer,String>  colCustHomePhone;

    @FXML
    private TableColumn<Customer,String>  colCustEmail;

    @FXML
    private TableColumn<Customer,String>  colCustAddress;

    @FXML
    private TableColumn<Customer,String>  colCustCity;

    @FXML
    private TableColumn<Customer,String>  colCustProv;

    @FXML
    private TableColumn<Customer,String> colCustCountry;

    @FXML
    private TableColumn<Customer,String>  colCustPostal;



    public void initialize() {

        ObservableList<Customer>  customers= Datasource.getInstance().queryAllCustomers();


       colCustomerId.setCellValueFactory(c -> c.getValue().customerIdProperty().asObject());
        colAgentId.setCellValueFactory(c -> c.getValue().agentIdProperty().asObject());
        colCustFirstName.setCellValueFactory(c ->c.getValue().custFirstNameProperty());
        colCustLastName.setCellValueFactory(c ->c.getValue().custLastNameProperty());
        colCustBusPhone.setCellValueFactory(c ->c.getValue().custBusPhoneProperty());
        colCustHomePhone.setCellValueFactory(c ->c.getValue().custHomePhoneProperty());
        colCustEmail.setCellValueFactory(c ->c.getValue().custEmailProperty());
        colCustAddress.setCellValueFactory(c ->c.getValue().custAddressProperty());
        colCustCity.setCellValueFactory(c ->c.getValue().custCityProperty());
        colCustProv.setCellValueFactory(c ->c.getValue().custProvProperty());
        colCustCountry.setCellValueFactory(c ->c.getValue().custCountryProperty());
        colCustPostal.setCellValueFactory(c ->c.getValue().custPostalProperty());

        tvCustomers.setItems(customers);
    }

    @FXML
    public void showRewardWindow(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("RewardWindow.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Reward Setting");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }

    }

    @FXML

    public void showCustomer_RewardWindow(){

        Customer selectedCus=tvCustomers.getSelectionModel().getSelectedItem();
        if(selectedCus == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Customer Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the client you want to manage rewards");
            alert.showAndWait();
            return;
        }

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Cus_RwdWindow.fxml"));

            Scene scene = new Scene(loader.load(), 600, 400);

            Cus_RwdWindowController controller = loader.getController();
            controller.loadCus_Rwd(selectedCus);

            Stage stage = new Stage();
            stage.setTitle("Client's Rewards detail");
            stage.setScene(scene);
            stage.show();
        }

             catch (IOException ioException) {
                ioException.printStackTrace();
            }
    }
    @FXML
    public void ExitProgram(){
        Platform.exit();

    }
}







