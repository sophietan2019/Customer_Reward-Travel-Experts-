package App.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by SophieTan on 10/10/20.
 */

public class Customer {
    private final SimpleIntegerProperty CustomerId;
    private final SimpleIntegerProperty AgentId;
    private final SimpleStringProperty CustFirstName;
    private final  SimpleStringProperty CustLastName;
    private final SimpleStringProperty CustBusPhone;
    private final SimpleStringProperty CustHomePhone;
    private final SimpleStringProperty CustEmail;
    private final SimpleStringProperty CustAddress;
    private final SimpleStringProperty CustCity;
    private final SimpleStringProperty CustProv;
    private final SimpleStringProperty CustCountry;
    private final SimpleStringProperty CustPostal;



    public Customer()
    {
        this.CustomerId=new SimpleIntegerProperty();
        this.AgentId=new SimpleIntegerProperty();
        this.CustFirstName=new SimpleStringProperty();
        this.CustLastName=new SimpleStringProperty();
        this.CustBusPhone=new SimpleStringProperty();
        this.CustHomePhone=new SimpleStringProperty();
        this.CustEmail=new SimpleStringProperty();
        this.CustAddress=new SimpleStringProperty();
        this.CustCity=new SimpleStringProperty();
        this.CustProv=new SimpleStringProperty();
        this.CustCountry=new SimpleStringProperty();
        this.CustPostal=new SimpleStringProperty();
    }


    public  int getCustomerId() {
        return CustomerId.get();
    }
    public  SimpleIntegerProperty customerIdProperty() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        this.CustomerId.set(customerId);
    }

    public int getAgentId() {
        return AgentId.get();
    }

    public SimpleIntegerProperty agentIdProperty() {
        return AgentId;
    }

    public void setAgentId(int agentId) {
        this.AgentId.set(agentId);
    }

    public String getCustFirstName() {
        return CustFirstName.get();
    }

    public SimpleStringProperty custFirstNameProperty() {
        return CustFirstName;
    }

    public void setCustFirstName(String custFirstName) {
        this.CustFirstName.set(custFirstName);
    }

    public String getCustLastName() {
        return CustLastName.get();
    }

    public SimpleStringProperty custLastNameProperty() {
        return CustLastName;
    }

    public void setCustLastName(String custLastName) {
        this.CustLastName.set(custLastName);
    }

    public String getCustBusPhone() {
        return CustBusPhone.get();
    }

    public SimpleStringProperty custBusPhoneProperty() {
        return CustBusPhone;
    }

    public void setCustBusPhone(String custBusPhone) {
        this.CustBusPhone.set(custBusPhone);
    }

    public String getCustHomePhone() {
        return CustHomePhone.get();
    }

    public SimpleStringProperty custHomePhoneProperty() {
        return CustHomePhone;
    }

    public void setCustHomePhone(String custHomePhone) {
        this.CustHomePhone.set(custHomePhone);
    }

    public String getCustEmail() {
        return CustEmail.get();
    }

    public SimpleStringProperty custEmailProperty() {
        return CustEmail;
    }

    public void setCustEmail(String custEmail) {
        this.CustEmail.set(custEmail);
    }

    public String getCustAddress() {
        return CustAddress.get();
    }

    public SimpleStringProperty custAddressProperty() {
        return CustAddress;
    }

    public void setCustAddress(String custAddress) {
        this.CustAddress.set(custAddress);
    }

    public String getCustCity() {
        return CustCity.get();
    }

    public SimpleStringProperty custCityProperty() {
        return CustCity;
    }

    public void setCustCity(String custCity) {
        this.CustCity.set(custCity);
    }

    public String getCustProv() {
        return CustProv.get();
    }

    public SimpleStringProperty custProvProperty() {
        return CustProv;
    }

    public void setCustProv(String custProv) {
        this.CustProv.set(custProv);
    }

    public String getCustCountry() {
        return CustCountry.get();
    }

    public SimpleStringProperty custCountryProperty() {
        return CustCountry;
    }

    public void setCustCountry(String custCountry) {
        this.CustCountry.set(custCountry);
    }

    public String getCustPostal() {
        return CustPostal.get();
    }

    public SimpleStringProperty custPostalProperty() {
        return CustPostal;
    }

    public void setCustPostal(String custPostal) {
        this.CustPostal.set(custPostal);
    }

    @Override
    public String toString() {
        return  CustomerId.get()+"";
    }
}
