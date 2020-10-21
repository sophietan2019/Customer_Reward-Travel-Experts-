package App;

import App.model.Customer;
import App.model.Customer_Reward;
import App.model.Datasource;
import App.model.Reward;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Cus_RwdDialogController {
    @FXML
    private ComboBox cbCus_Rwd_RwdId;
    @FXML
    private TextField tfCus_Rwd_RwdName;
    @FXML
    private TextArea taCus_Rwd_RwdDesc;

    public void loadRewards() {
        ObservableList<Reward> rewards = Datasource.getInstance().queryAllRewards();
        cbCus_Rwd_RwdId.setItems(rewards);


        cbCus_Rwd_RwdId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Reward>() {
            @Override
            public void changed(ObservableValue<? extends Reward> observableValue, Reward reward, Reward r1) {
                tfCus_Rwd_RwdName.setText(r1.getRwdName());
                tfCus_Rwd_RwdName.setEditable(false);
                taCus_Rwd_RwdDesc.setText(r1.getRwdDesc());
                taCus_Rwd_RwdDesc.setEditable(false);
            }
        });

    }

    public int getRewardId(){
        int rewardid=Integer.parseInt(cbCus_Rwd_RwdId.getSelectionModel().getSelectedItem().toString());
        return rewardid;

    }

}

