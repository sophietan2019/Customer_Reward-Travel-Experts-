package App.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by SophieTan on 10/10/20.
 */

public class Customer_Reward {
     private SimpleIntegerProperty CustomerId;
     private SimpleStringProperty CustomerFirstName;
     private SimpleStringProperty CustomerLastName;
     private SimpleIntegerProperty RewardId;
     private SimpleStringProperty RwdName;
     private SimpleStringProperty RwdDesc;




    public Customer_Reward() {
        this.CustomerId=new SimpleIntegerProperty();
        this.CustomerFirstName=new SimpleStringProperty();
        this.CustomerLastName=new SimpleStringProperty();
        this.RewardId=new SimpleIntegerProperty();
        this.RwdName=new SimpleStringProperty();
        this.RwdDesc=new SimpleStringProperty();
    }

    public int getCustomerId() {
        return CustomerId.get();
    }

    public SimpleIntegerProperty customerIdProperty() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        this.CustomerId.set(customerId);
    }

    public String getCustomerFirstName() {
        return CustomerFirstName.get();
    }

    public SimpleStringProperty customerFirstNameProperty() {
        return CustomerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.CustomerFirstName.set(customerFirstName);
    }

    public String getCustomerLastName() {
        return CustomerLastName.get();
    }

    public SimpleStringProperty customerLastNameProperty() {
        return CustomerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.CustomerLastName.set(customerLastName);
    }

    public int getRewardId() {
        return RewardId.get();
    }

    public SimpleIntegerProperty rewardIdProperty() {
        return RewardId;
    }

    public void setRewardId(int rewardId) {
        this.RewardId.set(rewardId);
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
}
