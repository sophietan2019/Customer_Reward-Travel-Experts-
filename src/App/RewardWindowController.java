package App;

import App.model.Customer_Reward;
import App.model.Datasource;
import App.model.Reward;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RewardWindowController {

    @FXML
    private SplitPane spReward;
    @FXML
    private TableView<Reward> tvReward;
    @FXML
    private TableColumn<Reward,Integer> colRewardID;
    @FXML
    private TableColumn<Reward,String> colRwdName;
    @FXML
    private TableColumn<Reward,String> colRwdDesc;
    @FXML
    private Button btnAddRwd;
    @FXML
    private Button btnEditRwd;
    @FXML
    private Button btnDelRwd;
    @FXML
    private Button btnExitRwd;

    ObservableList<Reward> rewards=Datasource.getInstance().queryAllRewards();


    public void initialize(){


        colRewardID.setCellValueFactory(c -> c.getValue().rewardIdProperty().asObject());
        colRwdName.setCellValueFactory(c -> c.getValue().rwdNameProperty());
        colRwdDesc.setCellValueFactory(c -> c.getValue().rwdDescProperty());

        tvReward.setItems(rewards);

    }

    @FXML
    public void showAddRwdDialog() throws SQLException {
        Dialog<ButtonType> dialog=new Dialog<>();
        dialog.initOwner(spReward.getScene().getWindow());
        dialog.setTitle("Add New Reward");
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("RwdDialog.fxml"));
        try{
             dialog.getDialogPane().setContent(fxmlLoader.load());
        }
        catch(IOException ioE) {
            ioE.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        RwdDialogController rwdController=fxmlLoader.getController();
        rwdController.loadNewReward();

        Optional<ButtonType> result=dialog.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            Reward newRwd=rwdController.addReward();
            Datasource.getInstance().insertReward(newRwd);
            rewards.add(newRwd);

        }


    }

    @FXML
    public void showEditReward(){
        Reward selectedRwd=tvReward.getSelectionModel().getSelectedItem();

        if(selectedRwd==null){
             Alert alert=new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("No Reward selected!");
             alert.setHeaderText(null);
             alert.setContentText("Please select the reward you want to edit");
             alert.showAndWait();
             return;
        }

        Dialog<ButtonType> dialog=new Dialog<>();
        dialog.initOwner(spReward.getScene().getWindow());
        dialog.setTitle("Edit Reward");
        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("RwdDialog.fxml"));

        try{
             dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        RwdDialogController rwdController=fxmlLoader.getController();
        rwdController.editReward(selectedRwd);

        Optional<ButtonType> result= dialog.showAndWait();
        if(result.isPresent() && result.get()==ButtonType.OK){
            rwdController.updateReward(selectedRwd);
        }
    }

    @FXML
    public void deleteReward() {
        Reward selectedRwd=tvReward.getSelectionModel().getSelectedItem();
        if(selectedRwd==null){
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
                selectedRwd.getRewardId()+" "+selectedRwd.getRwdName());

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            List<Customer_Reward> crs=Datasource.getInstance().querryAllCus_Rwds();
            boolean isInCRTable=false;
            for(Customer_Reward cr: crs){
                if(cr.getRewardId()==selectedRwd.getRewardId()){
                    isInCRTable=true;
                }
            }
            if(isInCRTable){
                Datasource.getInstance().deleteCustomers_RewardsByRewardId(selectedRwd.getRewardId());
            }
            Datasource.getInstance().deleteReward(selectedRwd);
            rewards.remove(selectedRwd);

        }

    }

    public void closeRewardWindow(){
        Stage stage=(Stage) btnExitRwd.getScene().getWindow();
        stage.close();
    }


}
