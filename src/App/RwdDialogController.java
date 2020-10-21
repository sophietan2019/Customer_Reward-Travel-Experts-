package App;

import App.model.Datasource;
import App.model.Reward;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

import java.sql.SQLException;
import java.util.function.UnaryOperator;

public class RwdDialogController {
    @FXML
    private TextField tfRwdId;
    @FXML
    private TextField tfRwdName;
    @FXML
    private TextArea taRwdDesc;

    public  void loadNewReward() throws SQLException {
        int rwdId = Datasource.getInstance().queryMaxRewardId();
        tfRwdId.setText(String.valueOf(rwdId+1));
        tfRwdId.setDisable(true);
    }



    public Reward addReward() throws SQLException {

            int rwdId=Integer.parseInt(tfRwdId.getText());
            String rwdName = tfRwdName.getText();
            String rwdDesc = taRwdDesc.getText();
            Reward rwd = new Reward();
            rwd.setRewardId(rwdId);
            rwd.setRwdName(rwdName);
            rwd.setRwdDesc(rwdDesc);
            return rwd;

        }



    public void editReward(Reward rwd){
          tfRwdId.setText(String.valueOf(rwd.getRewardId()));
          tfRwdId.setDisable(true);
          tfRwdName.setText(rwd.getRwdName());
          taRwdDesc.setText(rwd.getRwdDesc());

    }

    public void updateReward(Reward rwd){
        rwd.setRewardId(Integer.parseInt(tfRwdId.getText()));
        rwd.setRwdName(tfRwdName.getText());
        rwd.setRwdDesc(taRwdDesc.getText());
        Datasource.getInstance().updateReward(rwd);
    }





}
