package App;

import App.model.Customer;
import App.model.Customer_Reward;
import App.model.Datasource;
import App.model.Reward;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Cus_RwdWindowController {
    @FXML
    private TableView tv_Cus_Rwd_Reward;
    @FXML
    private TableColumn<Customer_Reward,String> col_Cus_Rwd_RwdName;
    @FXML
    private TableColumn<Customer_Reward,String> col_Cus_Rwd_RwdDesc;
    @FXML
    private Label lbCustomer;
    @FXML
    private Label lbCustomerId;
    @FXML
    private SplitPane spCus_Rwd;
    @FXML
    private Button btnExitCus_Rwd;






    public void loadCus_Rwd(Customer cus){
        ObservableList<Customer_Reward> crs= Datasource.getInstance().queryRewardsByCustomerID(cus.getCustomerId());
        lbCustomer.setText("Client "+cus.getCustomerId()+"\n"+cus.getCustFirstName()+" "+cus.getCustLastName());
        lbCustomerId.setText(String.valueOf(cus.getCustomerId()));

        col_Cus_Rwd_RwdName.setCellValueFactory(c->c.getValue().rwdNameProperty());
        col_Cus_Rwd_RwdDesc.setCellValueFactory(c->c.getValue().rwdDescProperty());

        tv_Cus_Rwd_Reward.setItems(crs);

    }

    @FXML
    public void showAddCus_RwdDialog() throws SQLException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(spCus_Rwd.getScene().getWindow());
        dialog.setTitle("Choose a new reward");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Cus_RwdDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException ioE) {
            ioE.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Cus_RwdDialogController cus_rwdDialogController = fxmlLoader.getController();
        cus_rwdDialogController.loadRewards();

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            int rwdid = cus_rwdDialogController.getRewardId();
            int cusid = Integer.parseInt(lbCustomerId.getText());

            Datasource.getInstance().insertCustomer_Reward(cusid, rwdid);
            ObservableList<Customer_Reward> newcrs = Datasource.getInstance().queryRewardsByCustomerID(cusid);
            tv_Cus_Rwd_Reward.setItems(newcrs);

        }
    }

        @FXML
        public void delCus_Rwd(){
        Customer_Reward selectedCR= (Customer_Reward) tv_Cus_Rwd_Reward.getSelectionModel().getSelectedItem();
            if(selectedCR==null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("No Reward Selected");
                alert.setHeaderText(null);
                alert.setContentText("PLease select the reward you want to delete.");
                alert.showAndWait();
                return;
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Reward");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete the selected reward: " +
                    selectedCR.getRewardId()+" "+selectedCR.getRwdName());

            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK) {

                Datasource.getInstance().deleteCustomers_RewardsByRewardId(selectedCR.getRewardId());
                int cusid = Integer.parseInt(lbCustomerId.getText());
                ObservableList<Customer_Reward> newcrs = Datasource.getInstance().queryRewardsByCustomerID(cusid);
                tv_Cus_Rwd_Reward.setItems(newcrs);

            }
        }

        @FXML
        public  void exitCus_Rwd(){
            Stage stage=(Stage) btnExitCus_Rwd.getScene().getWindow();
            stage.close();

        }
    }

