package App.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by SophieTan on 10/10/20.
 */

public class Reward {
    private SimpleIntegerProperty RewardId;
    private SimpleStringProperty RwdName;
    private SimpleStringProperty RwdDesc;

    public  Reward(){
        this.RewardId=new SimpleIntegerProperty();
        this.RwdName=new SimpleStringProperty();
        this.RwdDesc=new SimpleStringProperty();

    }

//    public  Reward(int rwdId,String rwdName,String rwdDesc){
//          this.RewardId.set(rwdId);
//          this.RwdName.set(rwdName);
//          this.RwdDesc.set(rwdDesc);}





    public int getRewardId() {
        return RewardId.get();
    }

    public SimpleIntegerProperty rewardIdProperty() {
        return RewardId;
    }

    public void setRewardId(int rewardId) {
        if(rewardId>=1){
            this.RewardId.set(rewardId);
        }
    }

    public String getRwdName() {
        return RwdName.get();
    }

    public SimpleStringProperty rwdNameProperty() {
        return RwdName;
    }

    public void setRwdName(String rwdName) {
        this.RwdName.set(rwdName);
    }

    public String getRwdDesc() {
        return RwdDesc.get();
    }

    public SimpleStringProperty rwdDescProperty() {
        return RwdDesc;
    }

    public void setRwdDesc(String rwdDesc) {
        this.RwdDesc.set(rwdDesc);
    }

    @Override
    public String toString() {
        return String.valueOf(getRewardId());
    }
}
